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
				<form class="layui-form layui-form-pane">
					<div class="layui-form-item">
						<label class="layui-form-label">角色名称</label>
						<div class="layui-input-block">
							<input type="text" name="title" autocomplete="off"
								placeholder="请输入角色名称" class="layui-input" > 
						</div>
					</div>
			      <button type="submit" class="layui-btn" onclick="add(this)">立即添加</button>
			      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</form>
			</div>
		</div>
	</div>
</body>
<script src="../plugins/layui/layui.js"></script>
<script src="../src/js/jquery-2.2.4.min.js"></script>
<script>
function add(a){
	var form=$(a).parent().parent();
	var value=form.find("input").val();
	alert(value);
	  $.ajax({
	       url:'<%=request.getContextPath()%>/roleServlet?role=8',
	       type:'post',
	       async:false,
	       data:{'value':value},
	       dataType:'json',
	       success:function(data){
	          if(data == "0"){
			   	   alert("进行添加！");
		               $.ajax({
		                   url:'<%=request.getContextPath()%>/roleServlet?role=7',
		                   type:'get',
		                   async:false,
		                   data:{'value':value},
		                   dataType:'json',
		                   success:function(json){
		                       if(json ==  "0"){
		                           alert("添加成功！");
		                       }else{
		                           alert("添加失败");
		                       }
		                   },error:function(XMLHttpRequest, textStatus, errorThrown)
		                   {
		                       alert("后台发生错误!!");
		                   }
		               });
	          }else{
	        	  alert("职位已存在！！！");
	          }
	          window.location.reload();
	       },
	       error:function(XMLHttpRequest, textStatus, errorThrown)
	       {
	           alert("后台发生错误!!");
	       }
	   });
}



layui.use([ 'layer', 'form' ], function() {});
</script>
</html>