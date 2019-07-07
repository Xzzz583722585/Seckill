$(function () {
    var socket;

    if(typeof(WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    }else{
        console.log("您的浏览器支持WebSocket");

        socket = new WebSocket("ws://localhost:8080/notification");

        socket.onopen = function() {
            console.log("Socket 已打开");
        };

        socket.onmessage = function(msg) {
            console.log(msg.data);
        };

        socket.onclose = function() {
            console.log("Socket已关闭");
        };

        socket.onerror = function() {
            alert("Socket发生了错误");
        }
    }
});