(function(w,d,u){
    var plist = util.get('plist');
    if(!plist){
        return;
    }

    var layer = new Layer();
    var loading = new Loading();
    var page = {
        init:function(){
            plist.addEventListener('click',function(e){
                var ele = e.target;
                var delId = ele.dataset && ele.classList.contains('del');
                if(delId){
                    var delid = ele.dataset.awardid;
                    this.ondel(delid);
                    return;
                }
            }.bind(this),false);
        },
        ondel:function(id){
            layer.reset({
                content:'确定要删除该内容吗？',
                onconfirm:function(){
                    layer.hide();
                    loading.show();
                    $.ajax({
                        url:'/NTES/del',
                        type:"POST",
                        data:{
                            id:id,
                            _method:"delete",
                        },
                        success:function(json){
                            this.delItemNode(id);
                            loading.result('删除成功');
                        }.bind(this)
                    });
                }.bind(this)
            }).show();
        },
        delItemNode:function(id){
            var item = util.get('p-'+id);
            if(item && item.parentNode){
                item.parentNode.removeChild(item);
            }
        }
    };
    page.init();
})(window,document);