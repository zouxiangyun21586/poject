<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加账号</title>
<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
</head>
<body>
	<br />
	<div class="layui-container" width="100%" height="100%" align="center">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md8" style="width: 100%;">
				<form class="layui-form layui-form-pane" action="#" method="get">
					<div class="layui-form-item">
						<label class="layui-form-label">角色名称</label>
						<div class="layui-input-block">
							<input type="text" name="title" autocomplete="off"
								placeholder="请输入角色名称" class="layui-input">
						</div>
					</div>
			      <button type="submit" class="layui-btn">立即添加</button>
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