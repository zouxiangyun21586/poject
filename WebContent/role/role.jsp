<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账号管理</title>
<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
</head>
<body>
<br/>
	<div class="layui-container" width="100%" height="100%" align="center">
    	<div class="layui-row layui-col-space15">
			<div class="layui-col-md8"  style="width: 100%;">
				<button class="layui-btn layui-btn-sm" style="float:left;" id="adduser">
				<i class="layui-icon">&#xe654;</i> 添加角色</button>
				<button class="layui-btn layui-btn-sm layui-btn-normal" style="float:right;margin-left:0px;">
				<i class="layui-icon">&#xe615;</i> 搜索</button>
				<input type="text" class="layui-input" placeholder="请输入要查询的角色" style="float:right;width:250px;height:30px;"/>
				<table class="layui-table">
					<thead>
						<tr>
							<td>角色编号</td>
							<td>角色名称</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>管理员</td>
							<td style="width:122px;">
								<button class="layui-btn layui-btn-xs layui-btn-normal">
								<i class="layui-icon">&#xe642;</i> 修改</button>
								<button class="layui-btn layui-btn-xs layui-btn-danger">
								<i class="layui-icon">&#xe640;</i> 删除</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
<script src="../plugins/layui/layui.js"></script>
<script src="../src/js/jquery-2.2.4.min.js"></script>
<script>
layui.use([ 'layer', 'laypage', 'element' ], function() {
	$('#adduser').click(function(){
		layer.open({
			anim: 2,
			title : '添加角色',
			type: 2, //窗口类型
			resize:false,//禁止拉伸
			maxmin:false,//最大化,最小化
			shade: [0.3,'#000'],
			area: ['300px', '165px'],//窗口宽高
			content: ['add.jsp','no']
		});
	});
});
</script>
</html>