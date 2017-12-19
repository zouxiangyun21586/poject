<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片管理</title>
<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
</head>
<body>
	<br />
	<div class="layui-container" width="100%" height="100%" align="center">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md8" style="width: 100%;">
				<div class="layui-upload">
					<div class="layui-upload-list">
						<button type="button"
							class="layui-btn layui-btn-sm layui-btn-normal" id="addpictrue"
							style="float: left;">
							<i class="layui-icon">&#xe654;</i> 添加图片
						</button>
						<table class="layui-table" lay-size="sm" lay-skin="row">
							<thead>
								<tr>
									<th><div class="layui-table-cell laytable-cell-2-status"
											align="center"">
											<span>大图预览</span>
										</div></th>
									<th><div class="layui-table-cell laytable-cell-2-status"
											align="center">
											<span>小图预览</span>
										</div></th>
									<th><div class="layui-table-cell laytable-cell-2-status"
											align="center">
											<span>上传时间</span>
										</div></th>
									<th><div class="layui-table-cell laytable-cell-2-status"
											align="center">
											<span>文件大小</span>
										</div></th>
									<th><div class="layui-table-cell laytable-cell-2-status"
											align="center">
											<span>操作</span>
										</div></th>
								</tr>
							</thead>
							<tbody id="imageListView">
								<tr>
									<td align="center" style="width:150px;"><img src="../images/0.jpg"/><br/><span class="layui-badge-rim">/images/0.jpg</span></td>
									<td align="center" style="width:150px;"><img src="../images/0.jpg"/><br/><span class="layui-badge-rim">/images/0.jpg</span></td>
									<td align="center" style="width:150px;font-size:15px;">2017-12-14 00:00:00</td>
									<td align="center">164KB</td>
									<td style="width:150px;">
										<button class="layui-btn layui-btn-xs layui-btn-normal">
										<i class="layui-icon"></i> 修改</button>
										<button class="layui-btn layui-btn-xs layui-btn-danger">
										<i class="layui-icon"></i> 删除</button>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="../plugins/layui/layui.js"></script>
<script src="../src/js/jquery-2.2.4.min.js"></script>
<script>
layui.use([ 'layer', 'laypage', 'element' ], function() {
	$('#addpictrue').click(function(){
		layer.open({
			anim: 2,
			title : '添加图片',
			type: 2, //窗口类型
			resize:false,//禁止拉伸
			maxmin:false,//最大化,最小化
			shade: [0.3,'#000'],
			area: ['550px', '510px'],//窗口宽高
			content: ['add.jsp','no']
		});
	});
});
</script>
</html>