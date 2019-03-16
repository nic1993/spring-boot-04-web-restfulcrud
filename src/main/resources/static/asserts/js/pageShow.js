
var from = function(id){
    return document.getElementById(id);
}

from('plusNum').onclick = function(e){
    e = window.event || e;
    o = e.srcElement || e.target;
    var num = from('allNum').textContent;
    if(num > 1){
        num --;
        from('allNum').innerHTML = num;
    }else{
        alert("您没有购买任何商品");
    }
};

from('addNum').onclick = function(e){
    e = window.event || e;
    o = e.srcElement || e.target;
    var num = from('allNum').textContent;
    num ++;
    from('allNum').innerHTML = num;
};

var loading = new Loading();
var layer = new Layer();

from('add').onclick = function(e){
    var id = from('input').value;
    var num = from('allNum').innerHTML;

    console.log(id);
    console.log(num);

    e == window.event || e;
    layer.reset({
        content:'确认加入购物车吗？',
        onconfirm:function(){
            layer.hide();
            loading.show();
            $.ajax({
                url:'/NTES/addCart',
                type:'POST',
                datatype:'JSON',
                data:{
                    id:id,
                    num:num,
                },
                success:function(data){
                    loading.result('添加购物车成功');
                    return;
                }
            })
        }.bind(this)
    }).show();
    return;
};




