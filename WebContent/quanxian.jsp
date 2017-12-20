<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
</head>
<body>
<input type="hidden" value="0" id="id">
<div class="layui-form">
<ul class="layui-nav layui-nav-tree" lay-filter="test" id="nvaul" style="width:465px;"></ul>
</div>
</body>
<script src="plugins/layui/layui.js"></script>
<script src="src/js/jquery-2.2.4.min.js"></script>
<script>
$(document).ready(function(){
	layui.use(['tree','element'], function(){
		var element = layui.element;
		$.ajax({     
	        type: "POST",//请求方式,默认GET
	        url: "powerServlet?id="+$('#id').val(),     
	        dataType: "json",
	        success: function(data) {
	        	var dataObj=data;
	        	var fu="";
	        	var i=0;
	        	$.each(dataObj, function(index, item){
	        		zi(item.id,i);//执行添加子节点
        			fu+="<li class='layui-nav-item'>"
   	            	+"<a href='javascript:;''>"+item.fatherName+"</a>"		
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
		        url: "powerServlet?id="+id,     
		        dataType: "json",
		        success: function(data) {
		        	var dataObj=data;
		        	var zi="";
		        	$.each(dataObj, function(index, item){
		        		var state="";
		        		if(item.state=="0"){
		        			state="使用中";
		        		}else if(item.state=="1"){
		        			state="已停用";
		        		}
	        			zi+="<dd><label class='layui-form-label' style='width:50px;'>"+item.fatherName+"</label>"
    	            	+"<div class='layui-input-inline' style='padding-left:70px;'>"
    	            	+"<input class='layui-input' style='width:198px;background-color:rgba(255, 255, 255, 0.64);' value='空'></div>"
    	            	+"<span class='layui-badge-rim' style='line-height:35px;height:36px;'>状态:"+state+"</span>"
    	            	+"<input type='checkbox' name='like1[write]' lay-skin='primary' checked=''>"
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