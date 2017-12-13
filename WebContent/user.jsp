<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账号管理</title>
<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
</head>
<body>
<br/>
	<div class="layui-container" width="100%" height="100%" align="center">
    	<div class="layui-row layui-col-space15">
			<div class="layui-col-md8"  style="width: 100%;">
				<button class="layui-btn layui-btn-sm" style="float:left;">
				<i class="layui-icon">&#xe654;</i> 添加账号</button>
				<button class="layui-btn layui-btn-sm layui-btn-normal" style="float:right;margin-left:0px;">
				<i class="layui-icon">&#xe615;</i> 搜索</button>
				<input type="text" class="layui-input" placeholder="请输入要查询的账号" style="float:right;width:150px;height:30px;"/>
				<table class="layui-table">
					<thead>
						<tr>
							<td>表头</td>
							<td>自己</td>
							<td>修改</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>数据</td>
							<td>自己</td>
							<td>改好</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>