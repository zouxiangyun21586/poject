<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width =device-width, initial-scale=1, maximum-scale=1" />
<title></title>
<meta charset="utf-8" />
<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
</head>

<body>
	<form class="layui-form">
		<div id="role" style="background-color: #fff; padding: 10px 0 25px 5px;"></div>
	</form>
	<ul class="layui-fixbar">
		<a class="layui-btn layui-btn-small layui-btn-normal" id="btn"><i class="layui-icon">&#xe618;</i> 确定</a>
	</ul>
</body>
<link rel="stylesheet" href="../plugins/layui/layui.js" media="all" />
<script src="../plugins/layui/layui.js"></script>
<script src="../js/layui-xtree.js"></script>
<script src="../js/rolefenye.js"></script>
<script src="json.js"></script>
<script src="../src/js/jquery-2.2.4.min.js"></script> 
	<!-- 
		<script src="../src/js/jquery-2.2.4.min.js"></script>
		<script src="../js/layui-xtree.js"></script>
		<script src="../src/js/jquery-2.2.4.min.js"></script> 
	-->
<script type="text/javascript">
$.getUrlParam = function (name) {  
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象  
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数  
	if (r != null) return decodeURI(r[2]); return null; //返回参数值  
}
	layui.use(['form','layer'], function() {
		var form = layui.form,
			layer = layui.layer,
			index = parent.layer.getFrameIndex(window.name);
			var xtree = new layuiXtree({
				elem : 'role',
				data : '<%=request.getContextPath()%>/jurisdiction',//请求链接
				form : form,
				ckall: true,
				icon: {        
	                end: "&#xe612;" 
	            }, color: {      
	                end: "#009688"   
	            }
			});
	});
</script>
</html>