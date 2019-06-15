$(function () {
    $.ajax({
        type: "GET",
        url: baseUrl + "/goods/seckill_list",
        datatype: "json",
        success: function(data){
            var html = $("#goodslist").html()
            for(i in data){
                var goods = data[i]
                html += "<tr>\n" +
                    "       <td>" + goods.name + "</td>\n" +
                    "       <td>" + goods.price + "</td>\n" +
                    "       <td>" + goods.seckillPrice + "</td>\n" +
                    "       <td>" + goods.seckillStock + "</td>\n" +
                    "       <td><a href='/htm/goods/goods_detail.html?goodsId=" + goods.id + "'>详情</a></td>\n" +
                    "   </tr>"
            }
            $("#goodslist").html(html)
        },
        error: function(e){
            console.log(e)
        }
    })
})