(function(w,d,u){
    var page = {
        init:function(){
            var flagBuy = false,flagSell = true;
            $('#buyer').click(function () {
                $(this).addClass('choose_txtChange').siblings('h1').removeClass('choose_txtChange');
                if(flagBuy){
                    $('#loginForm').removeClass('login_out').addClass('login_go');
                    $('#loginbtn').text("买家登陆");
                    flagSell = true;
                }
            });
            $('#seller').click(function () {
                $(this).addClass('choose_txtChange').siblings('h1').removeClass('choose_txtChange');
                if(flagSell){
                    $('#loginForm').removeClass('login_go').addClass('login_out');
                    $('#loginbtn').text("卖家登陆");
                    flagBuy = true;
                }
            })
            $('#loginbtn').click(function () {
                var value =  $('#loginbtn').text();
                var username = $('#nameInput').val();
                var password=md5($('#pwdInput').val());
                if(value == "卖家登陆"){
                    $.ajax({
                        url:'/NTES/seller/login',
                        type:'POST',
                        datatype:'JSON',
                        data:{
                            username:username,
                            password:password,
                        },
                        success:function (data) {
                            if(data.seller != null){
                                // sessionStorage.setItem('seller',data.seller);
                                window.location.href="/NTES/seller.html";
                            } else if(data.loginStatusCode==3){
                                $("#errormsg").html("用户名不存在！");
                            }else {
                                $("#errormsg").html("密码错误！");
                            }
                        },
                        error:function(err){
                            alert('发生了错误：'+err.status);
                            console.log(err);
                        }
                    });
                }else {
                    var username = $('#nameInput').val();
                    var password=md5($('#pwdInput').val());
                    $.ajax({
                        url:'/NTES/buyer/login',
                        type:'POST',
                        datatype:'JSON',
                        data:{
                            username:username,
                            password:password,
                        },
                        success:function (data) {
                            if(data.buyer != null){
                                sessionStorage.setItem('buyer',data.buyer);
                                window.location.href="/NTES/buyer.html";
                            } else if(data.loginStatusCode==3){
                                $("#errormsg").html("用户名不存在！");
                            }else {
                                $("#errormsg").html("密码错误！");
                            }
                        },
                        error:function(err){
                            alert('发生了错误：'+err.status);
                            console.log(err);
                        }
                    });
                }
            })
    },
    };
    page.init();
})(window,document);