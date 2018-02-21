$(function(){
	$("#main").height($(window).height() - $("#header").outerHeight());
	$(".bg-image").height($(window).height() - $("#header").outerHeight());
//	$(".bg-image").width($(window).width() - $(".nav-bar").outerWidth());
    // 通过js实现右边菜单栏的高度
    $("#main .nav-bar").height($(window).height() - $("#header").outerHeight()); 
    //当文档窗口发生改变时 触发  
    $(window).resize(function(){  
//    		$(".bg-image").width($(window).width() - $("#header").outerWidth());
    		$(".bg-image").height($(window).height() - $("#header").outerHeight());
    		$("#main").height($(window).height() - $("#header").outerHeight());  
        $("#main .nav-bar").height($(window).height() - $("#header").outerHeight());  
    });
    
    $(".look").click(function(){
    	var name = $(this).siblings(".user-name").text();
    	$.ajax({
    		type:"post",
    		url:"admin-Check.action",
    		data:{"username":name},
    		success:function(data){
    			if(data=="checked"){
    				location.reload();
    			}
    		},
    		error:function(){
    			alert("网络异常");
    		}
    	})
    });
    
    $(".is-confirm").click(function(){
    	var name = $(this).siblings(".user-name").text();
    	$.ajax({
    		type:"post",
    		url:"admin-ChangeStatus.action",
    		data:{"statuschange":1,"username":name},
    		success:function(data){
    			if(data=="changed"){
    				location.reload();
    			}
    		},
    		error:function(){
    			alert("网络异常");
    		}
    	})
    });
    
    $(".is-lagout").click(function(){
    	//alert("lagout");
    	var name = $(this).siblings(".user-name").text();
    	$.ajax({
    		type:"post",
    		url:"admin-ChangeStatus.action",
    		data:{"statuschange":0,"username":name},
    		success:function(data){
    			if(data=="changed"){
    				location.reload();
    			}
    		},
    		error:function(){
    			alert("网络异常");
    		}
    	})
    });
    
});