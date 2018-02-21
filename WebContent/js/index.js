$(function(){
	var scrollwidth = $("#main .menu-bar").offsetWidth - $("#main .menu-bar").scrollWidth;
	$("#main").height($(window).height() - $("#header").outerHeight());
	$(".bg-image").height($(window).height() - $("#header").outerHeight());
//	$(".bg-image").width($("#main").width()- $(".menu-bar").outerWidth() - 18);
    // 通过js实现右边菜单栏的高度
    $("#main .menu-bar").height($(window).height() - $("#header").outerHeight()); 
    //当文档窗口发生改变时 触发  
    $(window).resize(function(){  
//    	$(".bg-image").width($("#main").width()- $(".menu-bar").outerWidth());
    		$(".bg-image").height($(window).height() - $("#header").outerHeight());
    		$("#main").height($(window).height() - $("#header").outerHeight());  
        $("#main .menu-bar").height($(window).height() - $("#header").outerHeight());  
    });
    
    var menuBarPadding = 15;
    // 鼠标按下时
    $(".menu-bar").on("mousedown", ".menu-item", function(ev) {
        console.log(".menu-item mousedown");
        var oEvent = ev || event; // 兼容
        oEvent.preventDefault(); // 阻止冒泡
        var currentObj = $(this); // 保存当前.menu-item对象
        var marginTop = 4; // div的margin-top,防止每按一次，下滑一次

        // 获得元素按下鼠标时在页面的坐标
        var startX = currentObj.offset().left;
        var startY = currentObj.offset().top;

        currentObj.addClass("active");
        currentObj.css({"left": startX + "px", "top": startY - $("#header").outerHeight() + "px", "margin-top": 0});

        var disX = 0; // 鼠标指针到菜单左边的距离
        var disY = 0; // 鼠标指针到菜单顶部的距离
        disX = oEvent.pageX - startX; // 获得鼠标到菜单左边的距离
        disY = oEvent.pageY - startY; // 获得鼠标到菜单顶部的距离

        // 鼠标移动
        $(document).mousemove(function(ev) { // 用#main而没有用.menu,防止鼠标移动太快脱离menu
            // console.log(".menu-item mousedown mousemove");
            var oEvent = ev || event;
            oEvent.preventDefault(); // 阻止冒泡

            var l = oEvent.pageX - disX; // 计算得到此时menu的left
            var t = oEvent.pageY - disY -  $("#header").outerHeight(); // 计算得到此时menu的top
            
            if(l < 0) { // 让div从左边出不去
                l = 0;
            }
            if(l > $("#main").width() - currentObj.width()) {
                l = $("#main").width() - currentObj.width();
            }
            if(t < 0) { // 顶部限制不能出去
                t = 0;
            }
            //console.log($(window).height() - currentObj.outerHeight() - $("#header").outerHeight());
            if(t > $(window).height() - currentObj.outerHeight() - $("#header").outerHeight() - 2) { // 减2阻止出现鬼畜事件
                // div的顶部的坐标位置 = 父元素的height减去div的height
                t = $(window).height() - currentObj.outerHeight() - $("#header").outerHeight() - 2;
                console.log(t);
            }

            currentObj.css({"left": l + "px", "top": t + "px"});
        });

        // 鼠标放松时
        $(document).mouseup(function(ev) {
            var oEvent = ev || event; 
            $(document).off('mousemove'); //移除鼠标移动事件
            $(document).off('mouseup');

            var nowLeft = oEvent.pageX - disX; // 计算松开鼠标时menu的left
            var nowTop = oEvent.pageY - disY - $("#header").outerHeight(); // 计算松开鼠标时menu的top

            // console.log("nowLeft:" + nowLeft);
            // console.log($(window).width() - $(".menu-bar").outerWidth() - currentObj.width() - menuBarPadding);
            if(nowLeft > $(window).width() - $(".menu-bar").outerWidth() - currentObj.width() - menuBarPadding) {
                currentObj.css({"left": startX + "px", "top": startY - $("#header").outerHeight() + "px", "margin-top": marginTop});
                currentObj.removeClass("active");
                return;
            }
            // 保存移动的div此时的left, top, outerWidth, outerHeight
            var currentLi = {
                top: nowTop,
                left: nowLeft,
                width: currentObj.outerWidth(),
                height: currentObj.outerHeight()
            }

            var isCover = false; // 表示不覆盖
            // 遍历user-item的每个menu-item
            $(".user-item").each(function() {
                var oLi = {
                    top: $(this).offset().top - $("#header").outerHeight(),
                    left: $(this).offset().left,
                    width: $(this).outerWidth(),
                    height: $(this).outerHeight()
                }

                /*if(isContain(currentLi, oLi)) { // 如果覆盖，则不克隆
                    isCover = true;
                    // 返回原位置
                    currentObj.css({"left": startX + "px", "top": startY - $("#header").outerHeight() + "px", "margin-top": marginTop});
                    currentObj.removeClass("active");
                } */
            }); // each()结束

            if(!isCover) { // 没有覆盖
                console.log("没有覆盖！");
                currentObj.addClass("user-item");
                currentObj.children(".cancel-icon").css("display", "block");
                currentObj.removeClass("menu-item");
                $("#main").prepend(currentObj.clone());
                currentObj.remove();
            }

        }); // mouseup结束

        // 判断该li是否和已放置的li重叠
       function isContain(currentLi, oLi) {
            var lh = Math.max(currentLi.top + currentLi.height - oLi.top, oLi.top + oLi.height - currentLi.top);
            var lw = Math.max(currentLi.left + currentLi.width - oLi.left, oLi.left + oLi.width - currentLi.left);
            // console.log(lh + ":" + lw);
            if (lh < (currentLi.height + oLi.height) && lw < (currentLi.width + oLi.width)) { // 覆盖
                return true;
            }
            return false;
        };
        
    }); // .menu-item的mousedown结束

     // 解决未来动态添加的元素不能响应事件
     $("#main").on("mousedown", ".user-item", function(ev) { 
        console.log("user-item on mousedown");
        var oEvent = ev || event; // 兼容
        oEvent.preventDefault(); // 阻止冒泡
        var currentObj = $(this); // 保存当前.menu-item对象

        // 获得元素按下鼠标时在页面的坐标
        var startX = currentObj.offset().left;
        var startY = currentObj.offset().top;

        var disX = 0; // 鼠标指针到菜单左边的距离
        var disY = 0; // 鼠标指针到菜单顶部的距离
        disX = oEvent.pageX - startX; // 获得鼠标到菜单左边的距离
        disY = oEvent.pageY - startY; // 获得鼠标到菜单顶部的距离
        
        // 鼠标移动
        $(document).mousemove(function(ev) { // 用#main而没有用.menu,防止鼠标移动太快脱离menu
            // console.log("user-item mousedown mousemove");
            var oEvent = ev || event;
            oEvent.preventDefault(); // 阻止冒泡

            var l = oEvent.pageX - disX; // 计算得到此时menu的left
            var t = oEvent.pageY - disY -  $("#header").outerHeight(); // 计算得到此时menu的top
            
            if(l < 0) { // 让div从左边出不去
                l = 0;
            } 
            if(l > $("#main").width() - currentObj.outerWidth() - $(".menu-bar").outerWidth()) {
                l = $("#main").width() - currentObj.outerWidth() - $(".menu-bar").outerWidth();
            }
            if(t < 0) { // 顶部限制不能出去
                t = 0;
            } 
            console.log($(window).height() - currentObj.outerHeight() - $("#header").outerHeight());
            if(t > $(window).height() - currentObj.outerHeight() - $("#header").outerHeight()) {
                // div的顶部的坐标位置 = 父元素的height减去div的height
                t = $(window).height() - currentObj.outerHeight() - $("#header").outerHeight();
            }

            currentObj.css({"left": l + "px", "top": t + "px"});
        });

        // 鼠标放松时
        $(document).mouseup(function(ev) {
            console.log("user-item mousedown mouseup");
            var oEvent = ev || event; 
            $(document).off('mousemove'); //移除鼠标移动事件
            $(document).off('mouseup');

            var nowLeft = oEvent.pageX - disX; // 计算松开鼠标时menu的left
            var nowTop = oEvent.pageY - disY - $("#header").outerHeight(); // 计算松开鼠标时menu的top

            // 保存移动的li此时的left, top, outerWidth, outerHeight
            var currentLi = {
                top: nowTop,
                left: nowLeft,
                width: currentObj.outerWidth(),
                height: currentObj.outerHeight()
            }

            var isCover = false; // 表示不覆盖
            // 遍历user-item的每个menu-item
            $(".user-item").each(function() {
                // console.log("user-item");
                if(!$(this).is(currentObj)) { // 不是自身
                    var oLi = {
                        top: $(this).offset().top - $("#header").outerHeight(),
                        left: $(this).offset().left,
                        width: $(this).outerWidth(),
                        height: $(this).outerHeight()
                    }

                    /*if(isContain(currentLi, oLi)) { // 如果覆盖返回原位置
                        currentObj.css({"left": startX + "px", "top": startY - $("#header").outerHeight() + "px"});
                    } */
                    return;
                }
            }); // each()结束

        }); // mouseup结束

         // 判断该li是否和已放置的li重叠
         function isContain(currentLi, oLi) {
            var lh = Math.max(currentLi.top + currentLi.height - oLi.top, oLi.top + oLi.height - currentLi.top);
            var lw = Math.max(currentLi.left + currentLi.width - oLi.left, oLi.left + oLi.width - currentLi.left);
            // console.log(lh + ":" + lw);
            if (lh < (currentLi.height + oLi.height) && lw < (currentLi.width + oLi.width)) { // 覆盖
                return true;
            }
            return false;
        };
    });

    // 点击右上角取消
    $("#main").on("click", ".user-item .cancel-icon", function() {
        $(this).css("display", "none");
        $(this).parent(".user-item").addClass("menu-item");
        $(this).parent(".user-item").removeClass("active");
        $(this).parent(".menu-item").removeClass("user-item");
        $(this).parent(".menu-item").css("margin-top", 4 + "px");
        $(".menu-bar").prepend($(this).parent(".menu-item").clone()); // 克隆
        $(this).parent(".menu-item").remove();
    });

    $("#saveData").click(function() {
        // 遍历用户已放置的menu
        var array = []; //定义一个json对象
        
        $(".user-item").each(function() {
            var lt = $(this).offset().left;
            var tp = $(this).offset().top - $("#header").outerHeight();
            var picname = $(this).children("img").attr("src");
            var name = $(this).attr("name");
            var item = {}
            item["xloc"] = lt;
            item["yloc"] = tp;
            item["picname"] = picname;
            item["name"] = name;
            console.log(item);
            array.push(item);
        });

        var jsonStr = JSON.stringify(array); //可以将json对象转换成json对符串 
        
        $.ajax({
        	type:"get",
        	url:"saveUserItem.action",
        	data:{"jsonstr":jsonStr},
        	success:function(data){
        		if(data!=""){
        			console.log(data);
        			window.location.href = data;
        		}
        	},
        	error:function(){
        		alert("保存失败，网络错误");
        	}
        })
        
        console.log(jsonStr);
    });
    
    
 // 分析结果显示
    function showAnalysis(str) {
    		$(".mask-layer").css("display", "block");
    		$(".analysis-dialog").css("display", "block");
    		$(".analysis-result").text(str);
    }
    
    // 点击“确定按钮”,就隐藏遮罩层和对话框
    $(".confirm-btn").click(function() {
    		$(".analysis-result").text(""); // 清空对话框分析结果内容
    		$(".mask-layer").css("display", "none");
		$(".analysis-dialog").css("display", "none");
    });
    
    $("#analyse").click(function() {
        // 遍历用户已放置的menu
        var array = []; //定义一个json对象
        
        $(".user-item").each(function() {
            var lt = $(this).offset().left;
            var tp = $(this).offset().top - $("#header").outerHeight();
            var name = $(this).attr("name");
            var item = {}
            item["xloc"] = lt;
            item["yloc"] = tp;
            item["name"] = name;
            console.log(item);
            array.push(item);
        });

        var jsonStr = JSON.stringify(array); //可以将json对象转换成json对符串 
        console.log(jsonStr);
        $.ajax({
        	type:"get",
        	url:"Analyse.action",
        	data:{"jsonstr":jsonStr},
        	success:function(data){
        		if(data!=null){
        			showAnalysis(data);
        		}
        	},
        	error:function(){
        		alert("网络错误");
        	}
        })
        
    });
});
