<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改账户</title>
<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
</head>
<body>
<br/>
	<div class="layui-container" style="width:100%;height:100%;" align="center">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md8" style="width: 100%;">
				<div class="layui-form layui-form-pane">
						<div class="layui-form-item">
						    <label class="layui-form-label">TA是</label>
						    <div class="layui-input-block">
						    	<select id="interest" name="interest" lay-filter="aihao" lay-search></select>
						    </div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">用户名</label>
							<div class="layui-input-block">
								<input type="text" id="username" name="username" autocomplete="off"
									placeholder="请输入用户名" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">账号</label>
							<div class="layui-input-block">
								<input type="text" id="account" name="account" autocomplete="off"
									placeholder="请输入账号" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">密码</label>
							<div class="layui-input-block">
								<input type="password" id="password" name="password" autocomplete="off"
									placeholder="请输入密码" class="layui-input">
							</div>
						</div>
				        <button type="submit" class="layui-btn" id="sb">立即提交</button>
				        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
			</div>
		</div>
	</div>
</body>
<script src="../plugins/layui/layui.js"></script>
<script src="../src/js/jquery-2.2.4.min.js"></script>
<script>
layui.use([ 'layer', 'form' ], function() {
	var form = layui.form;
	
});
</script>
</html>