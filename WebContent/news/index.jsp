<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>news</title>
<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
</head>
<style>
.layui-unselect layui-layedit-tool{
float:right;
}
</style>
<body style="background-color: #f2f2f2;">
	<br />
	<input type="hidden" value="${ip}" id="ip">
	<div class="layui-container" style=" height:100%;width:100%;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md8" style="width: 100%;background-color: #fff;">
				<div id="message" style="overflow-x: hidden;height: 230px; background-color: #f2f2f2;"></div>
				<div class="layui-form layui-form-pane">
					<div class="layui-form-item" style="text-align: right;">
						<textarea id="text" style="display: none;"></textarea>
						<ul class="layui-fixbar" style="display:none;" id="btn">
							<li class="layui-icon" style="background-color: rgba(255, 87, 34, 0.42);" onclick="send();">&#xe609;</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="../js/jquery-3.2.1.js"></script>
<script src="../plugins/layui/layui.js"></script>
<script src="socketr.js"></script>
<script>
var index;
	layui.use('layedit', function() {
		var layedit = layui.layedit;
		index=layedit.build('text', {
			height : 50,
			tool : [ 'left', 'center', 'right', 'face' ]
		});
	});
</script>
</html>