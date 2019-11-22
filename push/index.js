const io = require('socket.io')(80);
const amqp = require('amqplib/channel_api');

let map = new Map();
let gameMap = new Map();
gameMap.set("0", "https://nlive-hls.pstatic.net/ch100/ch100_2000.stream/playlist.m3u8?_lsu_sa_=6fa5b8e0433b3842b1139ee56f956bfa8a1961e37456812765a1ee224c54632119dd30036d85f5b216453d41d705619c2e9493d9c57bfd4d6be2f747307c849d010b8906285f19fe34f0adcb66ac08d9101789c4ffc6168c129c877f563087d9");
gameMap.set("1", "https://nlive-hls.pstatic.net/ch21/ch21_2000.stream/playlist.m3u8?_lsu_sa_=64e510eac3fc31224d123e8c66e558f4ca3667433c57e1ef6241a520dc976d3141d3902c6135a5b266f73b41d109a178f67411ce8895da88325c5353c96bd0eea1d9f824ca793f7645a93d2c145d831cb6eb0c89d92acac5125a5eb909a09e8c");
gameMap.set("2", "https://nlive-hls.pstatic.net/ch200/ch200_2000.stream/playlist.m3u8?_lsu_sa_=63a5f0e8d36634b2911b3e786b4525f50a856803835fe1946fb1c12a4c336fc1e8d1b0466e55d0bf466d3ca15d0b912d87651c7363af2b570dc4de43fac79703c9e5107fb986fc87543ed0caec606c5486448adcd0c372e7ffe6a4f730c4434a");
let amqpUrl = "amqp://rabbitmq:rabbitmq@10.41.0.142";
let q = 'OWL';
let isRun = false;
const consumeToQueue = async (ch, queueName) => {
    await ch.consume(queueName, (msg) => {
        msgStr = Buffer.from(msg.content).toString();
        msgSplit = msgStr.split(';');
        console.log(msgStr);
        let clientId = msgSplit[0];
        let gameId = msgSplit[1];
        console.log(clientId);
        console.log(map);
        let socket = map.get(clientId);
        let gameUrl = gameMap.get(gameId);
        console.log(gameUrl);
        socket.emit('url', {url: gameUrl});
        ch.ack(msg);
    });
};

async function runMQ() {
    conn = await amqp.connect(amqpUrl);
    ch = await conn.createChannel();
    await consumeToQueue(ch, q);
}

// WARNING: app.listen(80) will NOT work here!

io.on('connection', function (socket) {
    socket.on('init', async function (data) {
        var clientId = data.clientId.toString();
        var gameId = data.gameId.toString();
        map.set(clientId, socket);
        if(!isRun){
            runMQ();
            isRun=true;
        }
    });
});