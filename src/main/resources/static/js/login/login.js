function login() {
//    var token = $("meta[name='_csrf']").attr("content");
//    var header = $("meta[name='_csrf_header']").attr("content");

    var salt = "USZdRw"
    var password = hex_md5(salt.charAt(0) + salt.charAt(5) + salt.charAt(3) + $("#password").val() + salt.charAt(2))
//    $("#password").val(password)

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/login",
        data: {
//            _csrf: token,
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