const app = require('express')();
const server = require('http').Server(app);
const io = require('socket.io')(server);
const axios = require('axios');

let map = new Map();

server.listen(80);
// WARNING: app.listen(80) will NOT work here!

app.get('/', function (req, res) {
    res.sendFile(__dirname + '/index.html');
});


io.on('connection', function (socket) {
    socket.on('init', async function (data) {
        console.log(data);

        var clientId = data.clientId;
        var gameId = data.gameId;

    });
});

function fetchURL(clientId, gameId) {
    axios.get(`http://106.10.34.82/play/${gameId}`)
        .then(function (response) {
            // handle success
            var url = response.data.url;
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        })
        .finally(function () {
            // always executed
        });
}