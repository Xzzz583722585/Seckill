function showSeckillGoodsDetails() {
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
    $.ajax({
        type: "POST",
        url: baseUrl + "/seckill/do_seckill",
        data: {
            goodsId: getUrlParam("goodsId")
        },
        datatype: "json",
        success: function(result){
            switch(result.code){
                case 0:
                    window.location.href = "/htm/order/order_detail.html?orderId=" + result.data
                    break
                case 40001:
                    window.location.href = "/htm/seckill/seckill_fail.html?errmsg=" + result.message
                    break
                case 40002:
                    window.location.href = "/htm/seckill/seckill_fail.html?errmsg=" + result.message
                    break
                default:
                    console.log("error code" + result.code)
            }
        },
        error: function(e){
            console.log(e)
        }
    })
}