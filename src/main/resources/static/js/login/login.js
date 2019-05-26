function login() {
    var salt = $("#salt").text()
    var password = hex_md5(salt.charAt(0) + salt.charAt(5) + salt.charAt(3) + $("#password").val() + salt.charAt(2))
    $("#password").val(password)
    // $.ajax({
    //     type: "POST",
    //     url: "http://localhost:8080/login",
    //     data: {
    //         username: $("#username").val(),
    //         password: password
    //     },
    //     async : false,
    //     datatype: "html",
    //     success: function(data){
    //         console.log(data)
    //         alert(data)
    //     },
    //     error: function(e){
    //         console.log(e)
    //     }
    // })
}