function showSeckillGoodsDetails() {
    console.log($("#remainSeconds").val())
    var inter = setInterval(function () {
        var sec = $("#remainSeconds").val()

        if(sec > 1){
            $("#remainSeconds").val(sec - 1)
            $("#countDown").text(sec - 1)
        }else{
            $("#seckillContent").html("秒杀进行中")
            $("#buyButton").show()
            clearInterval(inter)
        }
    }, 1000)
}

function doSeckill() {
    alert("doSeckill")
    $.ajax({
        type: "GET",
        url: baseUrl + "/seckill/do_seckill",
        data: {
            goodsId: getUrlParam("goodsId")
        },
        datatype: "json",
        success: function(data){
            console.log(data)
        },
        error: function(e){
            console.log(e)
        }
    })
}