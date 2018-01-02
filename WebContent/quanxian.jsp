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
				<form action="<%=request.getContextPath() %>/powerServlet?i=2" method="GET" id="for">
					<ul class="layui-nav layui-nav-tree" lay-filter="test" id="nvaul" style="width:100%;"></ul>
					<button class="layui-btn layui-btn-sm" id="submit"><i class="layui-icon">&#xe618;</i> 确定修改</button>
				</form>
			</div>
			</div>
		</div>
	</div>
</body>
<script src="plugins/layui/layui.js"></script>
<script src="src/js/jquery-2.2.4.min.js"></script>
<script>
$(document).ready(function(){
	var i=0;
	$('#submit').click(function(){
		var d = {};
	    var t = $('#for').serializeArray();
	    $.each(t, function() {
	      d[this.name] = this.value;
	    });
	    alert(JSON.stringify(d));
	});
	layui.use(['tree','element','form','layer'], function(){
		var element = layui.element;
		$.ajax({     
	        type: "POST",//请求方式,默认GET
	        url: "powerServlet?id="+$('#id').val(),     
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
		        url: "powerServlet?id="+id,     
		        dataType: "json",
		        success: function(data) {
		        	var dataObj=data;
		        	var zi="";
		        	$.each(dataObj, function(index, item){
	        			zi+="<dd><label class='layui-form-label' style='width:50px;'>"+item.name+"</label>"
    	            	+"<div class='layui-input-inline' style='padding-left:70px;'>"
    	            	+"<input type='hidden' value='"+item.id+"'>"
    	            	+"<input class='layui-input' style='width:198px;background-color:rgba(255, 255, 255, 0.64);' value='"+item.url+"'></div>"
    	            	+"<span class='layui-badge-rim' style='line-height:35px;height:35px;color:#0c9b8e;'>状态:"+item.staStr+"</span>"
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