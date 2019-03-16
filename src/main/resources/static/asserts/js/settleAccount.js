(function(w,d,u){
    var products;
    $.ajax({
        url:'/NTES/ShowCart',
        type:'GET',
        datatype:'JSON',
        success:function(data){
            var arr = data.products;
            products = data.products;
            var str = "<tr>" +
                "<th>" + '内容名称'  + "</th>"+
                "<th>" + '数量' + "</th>" +
                "<th>" + '价格' + "</th>" +
                "</tr>";
            for(var i = 0; i < arr.length; i++){
                str = str +
                    "<tr>" +
                    "<td>" + arr[i].goodsname  + "</td>"+
                    "<td>" +
                    "<span class=\"lessNum\" id='plusNum' >"+ "-" + "</span>" +
                    "<span class=\"totalNum\" id=\"allNum\" >" + arr[i].goodsnum + "</span>" +
                    "<span id=\"thisId\">" + arr[i].goodsid + "</span>" +
                    "<span class=\"moreNum\" id='addNum'>"+ "+" + "</span>" + "</td>" +
                    "<td>" + arr[i].price + "</td>" +
                    "</tr>";
            }
            $("#newTable").append(str);
        },
        error:function(err){
            alert('发生了错误：'+err);
            console.log(err);
        }
    })

    window.onload = function(){
        $('#newTable').click(function(e){
            var e = arguments[0] || window.event;
            target = e.srcElement ? e.srcElement : e.target;
            if(target.nodeName == "SPAN" && target.className == "moreNum"){
                var num = target.parentElement.children[1].textContent;
                var id = target.parentElement.children[2].textContent;
                num ++;
                target.parentElement.children[1].textContent = num;
                $.ajax({
                    url:'/NTES/UpdateCart',
                    type:'POST',
                    datatype:'JSON',
                    data:{
                        id:id,
                        num:num,
                    },
                    success:function(data){
                       if(data.code == 1){
                           alert(data.info);
                       }
                    },
                    error:function(err){
                        alert('修改失败!');
                    }
                });
            }else if(target.nodeName == "SPAN" && target.className == "lessNum"){
                var num = target.parentElement.children[1].textContent;
                var id = target.parentElement.children[2].textContent;
                num --;
                if(num < 1){
                    alert("商品数量不能为0");
                }else{
                    target.parentElement.children[1].textContent = num;
                    $.ajax({
                        url:'/NTES/UpdateCart',
                        type:'POST',
                        datatype:'JSON',
                        data:{
                            id:id,
                            num:num,
                        },
                        success:function(data){
                            if(data.code == 1){
                                alert(data.info);
                            }
                        },
                        error:function(err){
                            alert('修改失败!');
                        }
                    })

                }
            }
            return false;
        });
    };

    var loading = new Loading();
    var layer = new Layer();
    
    $("#Account").click(function () {
                layer.reset({
                    content:'确认购买吗？',
                    onconfirm:function(){
                        layer.hide();
                        loading.show();
                        $.ajax({
                            url:'/NTES/AddFinance',
                            type:'POST',
                            datatype:'JSON',
                            success:function(data){
                                if(data.code == 1){
                                    loading.result(data.info);
                                }else {
                                    loading.result(data.info);
                                    window.location.href="/NTES/buyer/finance.html"
                                }
                            }
                        })
                    }.bind(this)
                }).show();
                return;
    })

    $("#back").click(function () {
        window.history.go(-1);
    })

})(window,document)
