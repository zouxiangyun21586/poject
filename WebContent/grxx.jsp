<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
</head>
<style>
.layui-form-item{
	margin-bottom:0px;
}
</style>
<body>
<% 
HttpSession hs=request.getSession(true);  
String username=(String)hs.getAttribute("username");  
%>
	<div class="layui-container" width="100%" height="100%" align="center">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md8" style="width: 100%;">
				<div class="layui-form layui-form-pane">
					<div class="layui-form-item">
					    <label class="layui-form-label">
					    	<i class="layui-icon">&#xe612;</i> 用户名</label>
					    <div class="layui-input-block">
					      <span class="layui-badge layui-bg-green" style="width:100px;height:37px;left:1px;line-height:36px;">${username}</span>
					    </div>
					</div>
					<div class="layui-form-item">
					    <label class="layui-form-label">
					    	<i class="layui-icon">&#xe609;</i> 用户级别</label>
					    <div class="layui-input-block">
					      <span class="layui-badge" style="width:100px;height:37px;left:1px;line-height:36px;">管理员</span>
					    </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="src/js/jquery-2.2.4.min.js"></script>
<script>
$(document).ready(function(){
	
});
</script>
</html>