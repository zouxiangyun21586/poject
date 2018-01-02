<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
</head>
<body>
	<br/>
	<blockquote style="padding: 10px; border-left: 5px solid #FF5722;"
		class="layui-elem-quote">系统信息：</blockquote>
		<div class="layui-container" width="100%" height="100%" align="center">
	    	<div class="layui-row layui-col-space15">
				<div class="layui-col-md8"  style="width: 100%;">
	<table width="100%" class="layui-table">
		<tr>
			<td width="110px">程序版本</td>
			<td>1.0</td>
		</tr>
		<tr>
			<td>计算机名/IP</td>
			<td>${ip}</td>
		</tr>
		<tr>
			<td>服务器类型</td>
			<td>tomcat</td>
		</tr>
		<tr>
			<td>数据库大小</td>
			<td>
				<span class="layui-breadcrumb" lay-separator="/">
					<a style="color:#0c9b8e!important">${mysqlsize}</a>
					<a style="color:#ff5f2d!important">500MB</a>
				</span>
			</td>
		</tr>
		<tr>
			<td>JDK版本</td>
			<td>${jdk}</td>
		</tr>
		<tr>
			<td>服务器语言</td>
			<td>zh</td>
		</tr>
		<tr>
			<td>服务器Web端口</td>
			<td>${port}</td>
		</tr>
	</table>
	</div></div></div>
</body>
<script src="plugins/layui/layui.js" charset="utf-8"></script>
<script>
layui.use('element', function(){
  var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
  
  //监听导航点击
  element.on('nav(demo)', function(elem){
    //console.log(elem)
    layer.msg(elem.text());
  });
});
</script>
</html>