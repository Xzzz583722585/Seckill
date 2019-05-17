function login() {
    console.log($("#password").val())
    var salt = "USZdRw"
    var password = hex_md5(salt.charAt(0) + salt.charAt(5) + salt.charAt(3) + $("#password").val() + salt.charAt(2))

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/login",
        data: {
            username: $("#username").val(),
            password: $("#password").val()
        },
        datatype: "html",
        error: function(e){
            console.log(e)
        }
    })
}