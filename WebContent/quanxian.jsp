<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理</title>
<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
</head>
<body>
<br/>
	<div class="layui-container" width="100%" height="100%" align="center">
    	<div class="layui-row layui-col-space15">
			<div class="layui-col-md8"  style="width: 100%;">
			<input type="hidden" value="0" id="id">
			<div class="layui-form" style="width:425px;">
				<!-- <form id="for"> 
				可以看到，我在 button 标签添加了 id="submin"属性 前台框架会给你提交，这样做会产生新的一次表单点击提交，
				本来form 默认点击 button 时会产生一次提交 ， 
				button  id="submin"(这里是前台框架的功能) 时又会产生新第一次提交，导致 ajax 未执行完毕表单事件发生了改变。
				-->
					<ul class="layui-nav layui-nav-tree" lay-filter="test" id="nvaul" style="width:100%;"></ul>
					<button class="layui-btn layui-btn-sm" onclick="xiu()"><i class="layui-icon">&#xe618;</i> 确定修改</button>
				<!-- </form> -->
			</div>
			</div>
		</div>
	</div>
	<a href="<%=request.getContextPath()%>/powerServlet?i=2">xxxx</a>
</body>
<script src="plugins/layui/layui.js"></script>
<script src="src/js/jquery-2.2.4.min.js"></script>
<script>
function xiu(){
	var veidoo=new Array();
	var zhi="";
    var arrUl = jQuery(".layui-input-inline");
    jQuery.each(arrUl, function(){
        //alert(jQuery(this).find("img").attr("src"));
        //alert(jQuery(this).find("li").attr("class"));
        var zz = jQuery(this).find("#yurl").val();
        //var zz = $(".layui-input-inline").find("#yurl").val();
        var yi = jQuery(this).find("#yid").attr("value");
        //jQuery(this).find("#yid").attr("value");
        var zhi =zz+" "+yi;
        veidoo.push(zhi);
    });
    
   var vediooStr = veidoo.toString();
   //alert(vediooStr);
   
    $.ajax({
        type: "POST",//请求方式,默认GET
        url: "<%=request.getContextPath()%>/powerServlet",
        cache: false,//异步请求
		data:{"zhi":vediooStr,"i":2},
        dataType: "text",
        timeout : 50000, //超时时间：50秒
        success: function(va) {
        	//alert(va+"1");
        },error: function(XMLHttpRequest, textStatus, errorThrown) {
           alert(XMLHttpRequest.status);//200客户端请求已成功
           alert(XMLHttpRequest.readyState);//4 响应内容解析完成，可以在客户端调用了
           alert(errorThrown);//parsererror
        }
     });
}
$(document).ready(function(){
	var i = 0;
	//确认修改
	<%-- $('#for').submit(function(){
        var veidoo=[];
        var arrUl = jQuery(".layui-input-inline");
        jQuery.each(arrUl, function(){
	        //alert(jQuery(this).find("img").attr("src"));
	        //alert(jQuery(this).find("li").attr("class"));
	        var zz = jQuery(this).find("#yurl").attr("value");
	        var yi = jQuery(this).find("#yid").attr("value");
	        //jQuery(this).find("#yid").attr("value");
	        var zhi =zz+" "+yi;
	        veidoo.push(zhi);
        });
        $.ajax({
	        type: "POST",//请求方式,默认GET
	        url: "<%=request.getContextPath()%>/powerServlet?i=2",
	        //data:{"yname":$("#yname").text(),"yid":$("#yid").val(),"yurl":$("#yurl").val(),"ysta":$("#ysta").text()},
			data:{"zhi":veidoo},
	        dataType: "text",
	        success: function(va) {
	        	alert(va);
	        },error: function(XMLHttpRequest, textStatus, errorThrown) {
	           alert(XMLHttpRequest.status);//200客户端请求已成功
	           alert(XMLHttpRequest.readyState);//4 响应内容解析完成，可以在客户端调用了
	           alert(textStatus);//parsererror
	        }
	     });
	}); --%>
	layui.use(['tree','element','form','layer'], function(){
		var element = layui.element;
		$.ajax({     
	        type: "POST",//请求方式,默认GET
	        url: "powerServlet?id="+$('#id').val()+"&i=1",     
	        dataType: "json",
	        success: function(data) {
	        	var dataObj=data;
	        	var fu="";
	        	$.each(dataObj, function(index, item){
	        		zi(item.id,i);//执行添加子节点
        			fu+="<li class='layui-nav-item'>"
   	            	+"<a href='javascript:;''>"+item.name+"</a>"		
   	            	+"<dl class='layui-nav-child'></dl></li>";
   	            	i++;
	        	});
	        	$('#nvaul').append(fu);
	        	element.render('nav');
	        },error: function(XMLHttpRequest, textStatus, errorThrown) {
	     	   alert("失败");
	           alert(XMLHttpRequest.status);//200客户端请求已成功
	           alert(XMLHttpRequest.readyState);//4 响应内容解析完成，可以在客户端调用了
	           alert(textStatus);//parsererror
	        }
	     });	
		function zi(id,i){
			$.ajax({     
		        type: "POST",//请求方式,默认GET
		        url: "powerServlet?id="+id+"&i=1",     
		        dataType: "json",
		        success: function(data) {
		        	var dataObj=data;
		        	var zi="";
		        	$.each(dataObj, function(index, item){
	        			zi+="<dd><label class='layui-form-label' style='width:50px;' id='yname' name='yname'>"+item.name+"</label>"
    	            	+"<div class='layui-input-inline' style='padding-left:70px;'>"
    	            	+"<input type='hidden' id='yid' name='yname' value='"+item.id+"'>"
    	            	+"<input class='layui-input' id='yurl' name='yurl' style='width:198px;background-color:rgba(255, 255, 255, 0.64);' value='"+item.url+"'></div>"
    	            	+"<span class='layui-badge-rim' style='line-height:35px;height:35px;color:#0c9b8e;' id='ysta' name='ysta'>"+item.staStr+"</span>"
    	            	+"</dd>";
		        	});

		        	$('.layui-nav-child:eq('+i+')').append(zi);
		        	element.render('nav');
		        },error: function(XMLHttpRequest, textStatus, errorThrown) {
		     	   alert("失败");
		           alert(XMLHttpRequest.status);//200客户端请求已成功
		           alert(XMLHttpRequest.readyState);//4 响应内容解析完成，可以在客户端调用了
		           alert(textStatus);//parsererror
		        }
		     });
		}
	});	
});
</script>
</html>