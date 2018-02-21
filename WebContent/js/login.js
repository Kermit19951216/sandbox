$(function() {
    var ideCode; //在全局定义验证码 
    
    // 生成验证码
    function createCode() {
        ideCode = "";
        var ideCodeLength = 4;//验证码的长度  
        var randomNumber = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',  
        'S','T','U','V','W','X','Y','Z'); //随机数

        for(var i = 0; i < ideCodeLength; i++) {
            var index = Math.floor(Math.random()*36); //取得随机数的索引（0~35）

            ideCode += randomNumber[index];//根据索引取得随机数加到code上
        }
        $(".captcha").html(ideCode);
    }

    createCode(); // 调用生成生成验证码
    
    function showFailed(){
        if(flag==1){
            $(".error").text("密码或用户名错误");
            $(".login-msg").css("visibility", "visible");
            createCode(); // 刷新验证码
        }
    }
    
    showFailed();

    // 检验用户名和密码不能为空，验证码是否正确
    function Identify(){
        var username = $(".user-name").val();
        var password = $(".login-pwd").val();
        var enterCode = $(".identify").val();

        // 检验用户名和密码是否为空
        if(username == "" || password == "") {
            if(username == "" && password == "") { // 同时为空
                $(".error").text("请输入用户名和密码");
                $(".user-name").focus();
            } else if(username == "") {
                $(".error").text("请输入用户名");
                $(".user-name").focus();
            } else if(password == "") {
                $(".error").text("请输入密码");
                $(".login-pwd").focus();
            }
            $(".login-msg").css("visibility", "visible");
            createCode(); // 刷新验证码
            $(".identify").text("");
            return false;
        }

        // 检验验证码
        if(enterCode == "") { // 为空
            $(".error").text("请输入验证码");
            $(".login-msg").css("visibility", "visible");
            $(".identify").focus();
            createCode(); // 刷新验证码
            return false;
        } else if(enterCode.toUpperCase() != ideCode){
            $(".error").text("验证码输入错误");
            $(".login-msg").css("visibility", "visible");
            $(".identify").text("");
            $(".identify").focus();
            createCode(); // 刷新验证码
            return false;
        }

        return true;
    }

    // 点击更换验证码
    $(".captcha").click(function() {
        createCode(); 
    });

    // 看不清换一张
    $(".prompt").click(function(){
        createCode(); 
    });

    $("form").submit(function(e) {
        if(!Identify()) {
            e.preventDefault(); // 阻止提交表单
        } 
    });
    
    // 点击submit提交表单
   /* $("#loginForm").submit(function(e){
        e.preventDefault(); // 阻止提交按钮的默认事件
        
        // 检验用户名和密码不能为空，验证码是否正确
        if(Identify()) { // 用户名和密码都不为空，且验证码输入正确
            var name = $(".user-name").val(); // 用户名
            var password = $(".login-pwd").val(); // 密码
            
            // 将用户名和密码传到服务端检验是否存在或匹配
            $.ajax({
                // 请求发送方式
                type: "post",
                // 验证文件
                url: "${PageContext.request.contextPath}/user-Login.action",
                // 用户输入的帐号密码
                data: {"name": name, "password": password},
                // 异步，不写默认为True
                async: true,
                beforeSend: function(){
                    $(".error").text("登录中，请稍候...");
                    $(".login-msg").css("visibility", "visible");
                },
                //请求成功后的回调
                // 假设返回的data： 
                // 0：表示用户名不存在，请前往注册  1: 表示成功匹配 2：表示密码错误(用户名和密码不匹配)
                success: function(data){
                    if (data == 1){
                        alert('登录成功')
                    }else if(data == 2){
                        $(".error").text("用户名和密码不匹配,是否忘记密码");
                        $(".login-msg").css("visibility", "visible");
                        createCode(); // 刷新验证码
                    } else if(data == 0){
                        $(".error").text("用户名不存在，请前往注册");
                        $(".login-msg").css("visibility", "visible");
                        createCode(); // 刷新验证码
                    }
                },
                error: function(){
                    alert('服务端异常');
                }
            })
        }
    });*/
    
    $('input:radio[name="status"]').eq(0).attr("checked",'checked'); // 默认选中
    $(".status-wrap span").click(function() {
    		$('input:radio[name="status"]').each(function() {
    			$(this).attr("checked", false);
    		});
    		$(this).children('input:radio[name="status"]').attr("checked",'checked');
    		console.log($("input[name='status']:checked").val());
    });

    
});



function login(){
	var loc = $("input[name='status'][checked]").val(); 
	 $("#loginForm").attr('action',loc);    //通过jquery为action属性赋值
	 //alert(loc);
     $("#loginForm").submit();    //提交ID为myform的表单
}