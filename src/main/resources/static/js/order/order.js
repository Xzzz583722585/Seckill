var order = result.data
console.log(order)

$("#goodsName").text(order.goodsName)
$("#goodsPrice").text(order.goodsPrice)
$("#createDate").text(order.createDate)

var status = ""
switch(order.status){
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