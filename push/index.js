const io = require('socket.io')(80);
const amqp = require('amqplib/channel_api');
const axios = require('axios');
const config = require('./config.json');
const map = new Map();
const gameMap = new Map();
const environment = process.env.NODE_ENV || 'development';
const environmentConfig = config[environment];
const liveAPI = environmentConfig.liveapi_url;
const amqpUrl = environmentConfig.amqp_url;
const perRequestTime = config.per_sec_request_time;
const q = config.queue_name;
const log = require('./config/winston');


let conn;

io.on('connection', function (socket) {
	log.info(`socket connection is established`);
	socket.on('init', async function (data) {
		var clientId = data.clientId.toString();
		// var gameId = data.gameId.toString();
		log.info(`${clientId} is connected`);
		map.set(clientId, socket);
	});
});

async function connect() {
	try {
		conn = await amqp.connect(amqpUrl);
		log.info(`rabbitmq ${amqpUrl} connected`);
	} catch (err) {
		log.error(`rabbitmq ${amqpUrl} not connected ${err}`);
	}
}

const consumeToQueue = async (queueName) => {
	if (conn === undefined) {
		await connect();
	}
	const ch = await conn.createChannel();
	await ch.consume(queueName, (msg) => {
		const msgStr = Buffer.from(msg.content).toString();
		const msgSplit = msgStr.split(';');
		log.info(`message "${msgStr}" is received from mq ${q}`);
		const clientId = msgSplit[0];
		const gameId = msgSplit[1];
		const socket = map.get(clientId);
		if (socket === undefined) {
			log.warn(`${clientId} is not connected`);
			ch.nack(msg, false, true);
			return;
		}
		map.delete(clientId);
		const gameUrl = gameMap.get(gameId);
		socket.emit('url', {url: gameUrl});
		log.info(`${gameUrl} is sent to ${clientId}`);
		ch.ack(msg);
	});
	await ch.close();
};

(async function init() {
	await connect();
	await request();
})();

async function request() {
	try {
		log.info(`fetch request ${liveAPI}/play`);
		const res = await axios.get(liveAPI + "/play");
		const isCongested = res.data.congested;
		log.info(`now congestion is ${isCongested}`);
		const plays = res.data.plays;
		log.info(`url is received ${plays}`);
		for (let play of plays) {
			gameMap.set(play.gameId, play.url);
		}
		if (!isCongested) {
			await consumeToQueue(q);
		}
	} catch (err) {
		log.error(`server ${liveAPI} is down ${err}`);
	} finally {
		setTimeout(request, perRequestTime * 1000 * 60);
	}
}

