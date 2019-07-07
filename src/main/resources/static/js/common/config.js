var host = "localhost"
var port = 8080
var baseUrl = "http://" + host + ":" + port

var wsBaseUrl = "ws://" + host + ":" + port

var csrfToken = window.sessionStorage.getItem("csrfToken");
var csrfHeader = window.sessionStorage.getItem("csrfHeader");