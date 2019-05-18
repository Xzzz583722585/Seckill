function login() {
    var salt = $("#salt").text()
    var password = hex_md5(salt.charAt(0) + salt.charAt(5) + salt.charAt(3) + $("#password").val() + salt.charAt(2))

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/login",
        data: {
            username: $("#username").val(),
            password: password
        },
        datatype: "html",
        success: function(data){
            $("html").html(data)
        },
        error: function(e){
            console.log(e)
        }
    })
}