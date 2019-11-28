const io = require('socket.io')(80);
const amqp = require('amqplib/channel_api');
const axios = require('axios');
let liveAPI = 'http://localhost:8080';
let map = new Map();
let gameMap = new Map();
// let amqpUrl = "amqp://rabbitmq:rabbitmq@10.41.0.142";
let amqpUrl = "amqp://guest:guest@localhost";
let q = 'OWL';
let perRequestTime = 1;

async function connect() {
	try {
		conn = await amqp.connect(amqpUrl);
		console.log('rabbit mq connected');
	} catch (err) {
		console.log('not connected');
	}
}

const consumeToQueue = async (queueName) => {
	if (conn === undefined) {
		await connect();
	}
	ch = await conn.createChannel();
	await ch.consume(queueName, (msg) => {
		msgStr = Buffer.from(msg.content).toString();
		msgSplit = msgStr.split(';');
		console.log(msgStr);
		let clientId = msgSplit[0];
		let gameId = msgSplit[1];
		console.log('queue : ' + clientId);
		socket = map.get(clientId);
		if (socket === undefined) {
			console.log('not exists');
			ch.nack(msg, false, true);
			return;
		}
		map.delete(clientId);
		let gameUrl = gameMap.get(gameId);
		socket.emit('url', {url: gameUrl});
		console.log('message consume');
		ch.ack(msg);
	});
	await ch.close();
};

let conn;

(async function init() {
	await connect();
	await request();
})();

async function request() {
	try {
		console.log('request');
		let res = await axios.get(liveAPI + "/play");
		// await consumeToQueue(q);
		let isCongested = res.data.isCongested;
		let plays = res.data.plays;
		for (let play of plays) {
			gameMap.set(play.gameId, play.url);
		}
		if (!isCongested) {
			await consumeToQueue(q);
		}
	} catch (err) {
		console.error(err);
		console.log('error');
	} finally {
		setTimeout(request, perRequestTime * 1000 * 60);
	}
}


io.on('connection', function (socket) {
	socket.on('init', async function (data) {
		var clientId = data.clientId.toString();
		// var gameId = data.gameId.toString();
		console.log('connection ' + clientId);
		map.set(clientId, socket);
	});
});