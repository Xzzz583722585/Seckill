$(function () {
    $.ajax({
        type: "GET",
        url: baseUrl + "/order/order_details",
        data: {
            orderId: getUrlParam("orderId")
        },
        datatype: "json",
        success: function(data){
            $("#goodsName").text(data.goodsName)
            $("#goodsPrice").text(data.goodsPrice)
            $("#createDate").text(data.createDate)

            var status = ""
            switch(data.status){
                case 0:
                    status = "未支付"
                    break
                case 1:
                    status = "待发货"
                    break
                case 2:
                    status = "已发货"
                    break
                case 3:
                    status = "已收货"
                    break
                case 4:
                    status = "已退款"
                    break
                case 5:
                    status = "已完成"
                    break
                default:
                    console.log("ERROR ORDER STATUS")
            }
            $("#status").text(status)
        },
        error: function(e){
            console.log(e)
        }
    })
})