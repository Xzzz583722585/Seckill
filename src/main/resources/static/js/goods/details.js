$(function () {
    $.ajax({
        type: "GET",
        url: baseUrl + "/goods/goods_detail",
        data: {
            goodsId: getUrlParam("goodsId")
        },
        datatype: "json",
        success: function(data){
            $("#name").text(data.goods.name)
            $("#startDate").text(data.goods.startDate)
            $("#remainSeconds").val(data.remainSeconds)
            $("#price").text(data.goods.price)
            $("#seckillPrice").text(data.goods.seckillPrice)
            $("#seckillStock").text(data.goods.seckillStock)

            switch(data.seckillStatus){
                case 0:
                    showSeckillGoodsDetails()
                    break
                case 1:
                    $("#seckillContent").html("秒杀进行中")
                    $("#buyButton").show()
                    break
                case 2:
                    $("#seckillContent").html("秒杀已经结束")
                    break
                default:
                    console.log("ERROR SECKILL STATUS")
            }
        },
        error: function(e){
            console.log(e)
        }
    })
})