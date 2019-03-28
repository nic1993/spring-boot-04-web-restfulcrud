(function(w,d,u){

    var form = util.get('form');
    if(!form){
        return;
    }


    var layer = new Layer();
    var loading = new Loading();

    var goodsname = form['goodsname'];
    var summary = form['summary'];
    var image = form['location'];
    var info = form['goodsinfo'];
    var price = form['goodsprice'];
    var uploadInput = form['file'];
    var imageUrl='';
    var imageMode = "urlUpload";
    var page = {
        init:function() {

            $(".radioItem").change(
                function() {
                    var value = $("input[name='pic']:checked").val();
                    if (value == "file") {
                        $("#urlUpload").hide();
                        $("#fileUpload").show();
                        imageMode = "fileUpload";
                    } else {
                        $("#urlUpload").show();
                        $("#fileUpload").hide();
                        imageMode= "urlUpload";
                    }
                });


            $("#upload").click(function () {

                layer.reset({
                    content:'是否上传本地图片？',
                    onconfirm:function(){
                        layer.hide();
                        loading.show();
                        var maxAllowedSize = 100000;
                        var file = $("#fileUp").get(0).files[0];

                        if(file == null){
                            loading.show();
                            loading.result('文件为空!');
                        }
                        else {
                            var filename = file.name;
                            var lastname = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

                           if(lastname !="jpg" && lastname !="jpeg"  && lastname !="png"&&lastname !="gif"&&lastname !="bmp"){
                                loading.show();
                                loading.result('请选择图片格式!');
                            }
                            else if(file.size > maxAllowedSize){
                                loading.show();
                                loading.result('超过文件上传大小限制');
                            } else {
                                var formData = new FormData();
                                formData.append("file",file);
                                $.ajax({
                                    url: '/NTES/edit',
                                    type: 'POST',
                                    data: formData,
                                    datatype: 'JSON',
                                    processData: false,
                                    contentType: false,
                                    success: function (data) {

                                        $("#imgpre").attr("src", data.imgpath);
                                        imageUrl = data.imgpath;
                                        loading.result('上传成功');
                                    },
                                    error: function (err) {
                                        loading.result('上传失败');
                                    }
                                });
                            }
                        }
                    }.bind(this)
                }).show();
        })


            $("#savebtn").click(function () {
                var goodsname = $("#goodsname").val();
                var summary = $("#summary").val();
                var info = $("#goodsinfo").val();
                var price = $("#goodsprice").val();
                if (check()) {
                    var loc = "";
                    var value = $("input[name='pic']:checked").val();

                    if (value == "file") {
                        loc = imageUrl;
                    } else {
                        loc = image;
                    }
                    var btn = $("#savebtn").text();
                    console.log("loc:" + loc);
                    console.log("goodsname:" + goodsname);
                    console.log("summary:" + summary);
                    console.log("info:" + info);
                    console.log("price:" + price);
                    if(btn == "发布"){
                        $.ajax({
                          url:"/NTES/add",
                          type:'POST',
                          data:{
                                location:loc,
                                goodsname: goodsname,
                                summary: summary,
                                info: info,
                                price: price,
                            },
                            success:function(data){
                               localStorage.setItem("id",data);
                                localStorage.setItem("info","发布成功");
                                window.location.href="/NTES/success.html";
                            }
                        })
                    }else {
                        $.ajax({
                            url:"/NTES/edit",
                            type:'POST',
                            data:{
                                id:$("#goodsid").val(),
                                location:loc,
                                goodsname: goodsname,
                                summary: summary,
                                info: info,
                                price: price,
                                _method:"PUT",
                            },
                            success:function(data){
                                localStorage.setItem("id",data);
                                localStorage.setItem("info","编辑成功");
                                window.location.href="/NTES/success.html";
                            }
                        })
                    }
                } else {
                    return;
                }
            });
            [goodsname,summary,image,info,price].forEach(function(item){
                item.addEventListener('input',function(e){
                    item.classList.remove('z-err');
                }.bind(this),false);
            }.bind(this));
            function check(){
                var result = true;
                [
                    [goodsname,function(value){return value.length<2 || value.length>80}],
                    [summary,function(value){return value.length<2 || value.length>140}],
                    [image,function(value){return imageMode == "urlUpload" && value == ''}],
                    [info,function(value){return value.length<2 || value.length>1000}],
                    [price,function(value){return value == '' || !Number(value)}]
                ].forEach(function(item){
                    var value = item[0].value.trim();
                    if(item[1](value)){
                        item[0].classList.add('z-err');
                        result = false;
                    }
                    item[0].value = value;
                });
                if(imageMode == "fileUpload" && imageUrl == ''){
                    uploadInput.classList.add('z-err');
                    result = false;
                }
                return result;
            }
        }
    };
    page.init();
})(window,document);