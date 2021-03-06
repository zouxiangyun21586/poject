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
					<div class="layui-form layui-form-pane" id="di">
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
						<div class="layui-form-item">
							<label class="layui-form-label">邮箱</label>
							<div class="layui-input-block">
								<input type="email" id="youxiang" name="youxiang" autocomplete="off"
									placeholder="请输入邮箱" class="layui-input">
							</div>
						</div>
					</div>
			        <button type="submit" class="layui-btn" id="sb">立即提交</button>
			        <button id="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</div>
	</body>
	<script src="../plugins/layui/layui.js"></script>
	<script src="../src/js/jquery-2.2.4.min.js"></script>
	<script>
	$("#reset").click(function(){
		$("#username").val("");
		$("#password").val("");
		$("#account").val("");
	});
	layui.use(['form','layer'], function() {
		var form = layui.form;
		$(document).ready(function(){
			//页面显示角色值
			<%-- $.ajax({     
		        //要用post方式      
		        type: "POST",//请求方式,默认GET
		        cache: false,  //是否缓存，false代表拒绝缓存
		        //方法所在页面和方法名      
		        url: "<%=request.getContextPath() %>/superAdminServlet?i=3",     
		        contentType: "application/text; charset=utf-8",  
		        dataType: "json",
		        success: function(zh) {
		            for(var i in zh){
		            	/* $("#interest").append("<option value='"+zh[i].id+"'>"+zh[i].name+"</option>"); */
		            	$("#di").append("<input id='interest' name='interest' type='checkbox' value='"+zh[i].id+"' />"+zh[i].name+" ");
		            }
		            form.render('checkbox');
		        },     
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
		     	   alert("失败");
		           alert(XMLHttpRequest.status);//200客户端请求已成功
		           alert(XMLHttpRequest.readyState);//4 响应内容解析完成，可以在客户端调用了
		           alert(textStatus);//parsererror
		        }
		     }); --%>
			//添加ajax
			$("#sb").click(function(){
				var id_array=new Array();  
				$('input[name="interest"]:checked').each(function(){  
					id_array.push($(this).val());//向数组中添加元素  
				});  
				var idstr=id_array.join(',');//将数组元素连接起来以构建一个字符串  
				$.ajax({
			        //要用post方式
			        type: "POST",//请求方式,默认GET
			        cache: false,  //是否缓存，false代表拒绝缓存
			        //方法所在页面和方法名      
			        url: "<%=request.getContextPath() %>/superAdminServlet?i=2",     
			        /* data:{"interest":idstr,"username":$("#username").val(),"account":$("#account").val(),"password":$("#password").val(),"youxiang":$("#youxiang").val()}, */  //传参
			        data:{"username":$("#username").val(),"account":$("#account").val(),"password":$("#password").val(),"youxiang":$("#youxiang").val()},
			        dataType: "text",
			        success: function(zh) {
			           if(zh == "good"){
			        	   layui.use(['layer', 'laypage', 'element'], function(){
							layer.msg('添加成功!', 
							{icon: 1}
							); 
							setTimeout('parent.location.href=parent.location.href;','1000');
						  });
			           }else if(zh == "1"){
			        	   alert("账号已存在");
			           }else if(zh == "0"){
			        	   alert("请选择角色");
			           }else if(zh == "2"){
			        	   alert("账号或者密码不能为空 ");
			           }else{
			        	   alert("连接数据库出错");
			           }
			        },     
			        error: function(XMLHttpRequest, textStatus, errorThrown) {
			     	   alert("失败");
			           alert(XMLHttpRequest.status);//200客户端请求已成功
			           alert(XMLHttpRequest.readyState);//4 响应内容解析完成，可以在客户端调用了
			           alert(textStatus);//parsererror
			        }
			     });
			     //禁用按钮的提交      
			     return false;
			});
		});
	});
	</script>
</html>