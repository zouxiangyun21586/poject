<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
	</head>
	<link rel="stylesheet" href="../plugins/layui/css/global.css" media="all">
	<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
	<script src="../js/fenye.js"></script>
	<script src="../plugins/layui/layui.js"></script>
	<script src="../js/jquery-3.2.1.js"></script>
	<script>
		$(document).ready(function(){
			$.ajax({
				type:"post", //请求方式     对应form的  method请求
				url:"../buyersServlet", //请求路径  对应 form的action路径
				cache: false,  //是否缓存，false代表拒绝缓存
				data:{"pageNow":getPar("pageNow"),"type":"list","select":$('#select').val()},  //传参
				dataType: "json",   //返回值类型 
				
			});
		});
		
	</script>
	<body>
	</body>
</html>