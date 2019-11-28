const io = require('socket.io')(80);
const amqp = require('amqplib/channel_api');
const axios = require('axios');
const config = require('./config.json');
const map = new Map();
const gameMap = new Map();
const environment = process.env.NODE_ENV || 'development';
const environmentConfig = config[environment];
const liveAPI = `${environmentConfig.liveapi_url}/play`;
const amqpUrl = environmentConfig.amqp_url;
const perRequestTime = config.per_sec_request_time;
const q = config.queue_name;
const log = require('./config/winston');

let conn;

io.on('connection', (socket) => {
	log.info(`socket connection is established`);
	socket.on('init', async function (data) {
		var clientId = data.clientId.toString();
		log.info(`${clientId} is connected`);
		map.set(clientId, socket);
	});
});


const connect = async (amqpUrl) => {
	let conn;
	try {
		conn = await amqp.connect(amqpUrl);
		log.info(`rabbitmq ${amqpUrl} connected`);
	} catch (err) {
		log.error(`rabbitmq ${amqpUrl} not connected ${err}`);
	}
	return conn;
};

const consumeToQueue = async (queueName) => {
	if (conn === undefined) {
		conn = await connect();
	}
	const ch = await conn.createChannel();
	await ch.consume(queueName, (msg) => {
		const msgStr = Buffer.from(msg.content).toString();
		const msgSplit = msgStr.split(';');
		const clientId = msgSplit[0];
		const gameId = msgSplit[1];
		const socket = map.get(clientId);
		const gameUrl = gameMap.get(gameId);

		log.info(`message "${msgStr}" is received from mq ${q}`);
		if (socket === undefined) {
			log.warn(`${clientId} is not connected`);
			ch.nack(msg, false, true);
			return;
		}
		map.delete(clientId);
		socket.emit('url', {url: gameUrl});
		log.info(`${gameUrl} is sent to ${clientId}`);
		ch.ack(msg);
	});
	await ch.close();
};

async function request(url, callback) {
	try {
		const res = await axios.get(url);
		log.info(`fetch request ${url}`);
		callback(res.data);
	} catch (err) {
		log.error(`server ${url} is down ${err}`);
	} finally {
		setTimeout(request, perRequestTime * 1000 * 60, url, callback);
	}
}

async function liveApiProcess(data) {
	const isCongested = data.congested;
	log.info(`now congestion is ${isCongested}`);
	const plays = data.plays;
	log.info(`url is received ${plays}`);
	for (let play of plays) {
		gameMap.set(play.gameId, play.url);
	}
	if (!isCongested) {
		await consumeToQueue(q);
	}
}

(async () => {
	conn = await connect(amqpUrl);
	await request(liveAPI, liveApiProcess);
})();
