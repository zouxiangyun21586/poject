<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加账号</title>
<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
</head>
<style>
.layui-form-select dl{
	position:absolute;
	left:0px;
	top:42px;
	padding:5px 0px;
	min-width:100%;
	border:1px solid #d2d2d2;
	max-height:190px;
	overflow-y:auto;
	background-color:#fff;
	border-radius:2px;
	box-shadow:0 2px 4px rgba(0,0,0,.12);
	box-sizing:border-box;
}
</style>
<body>
	<br />
	<div class="layui-container" width="100%" height="100%" align="center">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md8" style="width: 100%;">
				<form class="layui-form layui-form-pane" action="#" method="get">
					<div class="layui-form-item">
					    <label class="layui-form-label">TA是</label>
					    <div class="layui-input-block">
					      <select name="interest" lay-filter="aihao" lay-search>
					        <option value=""></option>
					        <option value="0">管理员</option>
					        <option value="1">买家</option>
					        <option value="2">卖家</option>
					        <option value="3">供应商</option>
					        <option value="4">角色管理员</option>
					        <option value="5">审核员</option>
					        <option value="6">采购员</option>
					        <option value="7">商品审核员</option>
					      </select>
					    </div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">用户名</label>
						<div class="layui-input-block">
							<input type="text" name="title" autocomplete="off"
								placeholder="请输入用户名" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">账号</label>
						<div class="layui-input-block">
							<input type="text" name="title" autocomplete="off"
								placeholder="请输入账号" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">密码</label>
						<div class="layui-input-block">
							<input type="password" name="title" autocomplete="off"
								placeholder="请输入密码" class="layui-input">
						</div>
					</div>
					      <button type="submit" class="layui-btn">立即提交</button>
					      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</form>
			</div>
		</div>
	</div>
</body>
<script src="../plugins/layui/layui.js"></script>
<script src="../src/js/jquery-2.2.4.min.js"></script>
<script>
layui.use([ 'layer', 'form' ], function() {});
</script>
</html>