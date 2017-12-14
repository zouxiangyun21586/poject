<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
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
					<div class="layui-form layui-form-pane">
						<div class="layui-form-item">
						    <label class="layui-form-label">TA是</label>
						    <div class="layui-input-block">
						    	<select id="interest" name="interest" lay-filter="aihao" lay-search>
						        </select>
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
		$(document).ready(function(){
				//alert("a");
			$.ajax({
		        //要用post方式      
		        type: "POST",//请求方式,默认GET
		        cache: false,  //是否缓存，false代表拒绝缓存
		        //方法所在页面和方法名      
		        url: "<%=request.getContextPath() %>/superAdminServlet?i=3",     
		        contentType: "application/text; charset=utf-8",  
		        dataType: "json",
		        success: function(zh) {
		            for(var i in zh){
		            	$("#interest").append("<option value='"+zh[i].id+"'>"+zh[i].name+"</option>");
		            }
		            form.render('select');
		        },     
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
		     	   alert("失败");
		           alert(XMLHttpRequest.status);//200客户端请求已成功
		           alert(XMLHttpRequest.readyState);//4 响应内容解析完成，可以在客户端调用了
		           alert(textStatus);//parsererror
		        }
		     });
			//添加ajax
			$("#sb").click(function(){
				$.ajax({
			        //要用post方式      
			        type: "POST",//请求方式,默认GET
			        cache: false,  //是否缓存，false代表拒绝缓存
			        //方法所在页面和方法名      
			        url: "<%=request.getContextPath() %>/superAdminServlet?i=2",     
			        contentType: "application/text; charset=utf-8",  
			        data:{"interest":$("#interest option:selected").val(),"username":$("#username").val(),"account":$("#account").val(),"password":$("#password").val()},  //传参 
			        dataType: "text",
			        success: function(zh) {
			           if(zhi == "good"){
			        	   window.location.href="<%=request.getContextPath()%>/user/user.jsp";
			           }else if(zh == "1"){
			        	   alert("请选择角色");
			           }else {
			        	   alert("添加失败");
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
		function kuang(){
			//读取角色ajax
			$("#interest").click(function(){
				alert("a");
				$.ajax({     
			        //要用post方式      
			        type: "POST",//请求方式,默认GET
			        cache: false,  //是否缓存，false代表拒绝缓存
			        //方法所在页面和方法名      
			        url: "<%=request.getContextPath() %>/superAdminServlet?i=3",     
			        contentType: "application/text; charset=utf-8",  
			        dataType: "json",
			        success: function(zh) {
			            for(var i in zh){
			            	$("#interest").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
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
		}
	</script>
	<body>
		<br />
		<div class="layui-container" width="100%" height="100%" align="center">
			<div class="layui-row layui-col-space15">
				<div class="layui-col-md8" style="width: 100%;">
					<div class="layui-form layui-form-pane">
						<div class="layui-form-item">
						    <label class="layui-form-label">TA是</label>
						    <div class="layui-input-block">
						    	<select id="interest" name="interest" lay-filter="aihao" lay-search>
							        <!-- <option value="0">管理员</option>
							        <option value="1">买家</option>
							        <option value="2">卖家</option>
							        <option value="3">供应商</option>
							        <option value="4">角色管理员</option>
							        <option value="5">审核员</option>
							        <option value="6">采购员</option>
							        <option value="7">商品审核员</option> -->
						        </select>
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
	layui.use([ 'layer', 'form' ], function() {});
	</script>
</html> --%>







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
					<div class="layui-form layui-form-pane">
						<div class="layui-form-item">
						    <label class="layui-form-label">TA是</label>
						    <div class="layui-input-block">
						    	<select id="interest" name="interest" lay-filter="aihao" lay-search>
						        </select>
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
		$(document).ready(function(){
			$.ajax({     
		        //要用post方式      
		        type: "POST",//请求方式,默认GET
		        cache: false,  //是否缓存，false代表拒绝缓存
		        //方法所在页面和方法名      
		        url: "<%=request.getContextPath() %>/superAdminServlet?i=3",     
		        contentType: "application/text; charset=utf-8",  
		        dataType: "json",
		        success: function(zh) {
		            for(var i in zh){
		            	$("#interest").append("<option value='"+zh[i].id+"'>"+zh[i].name+"</option>");
		            }
		            form.render('select');
		        },     
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
		     	   alert("失败");
		           alert(XMLHttpRequest.status);//200客户端请求已成功
		           alert(XMLHttpRequest.readyState);//4 响应内容解析完成，可以在客户端调用了
		           alert(textStatus);//parsererror
		        }
		     });
			//添加ajax
			$("#sb").click(function(){
				$.ajax({
			        //要用post方式      
			        type: "POST",//请求方式,默认GET
			        cache: false,  //是否缓存，false代表拒绝缓存
			        //方法所在页面和方法名      
			        url: "<%=request.getContextPath() %>/superAdminServlet?i=2",     
			        contentType: "application/text; charset=utf-8",  
			        data:{"interest":$("#interest option:selected").val(),"username":$("#username").val(),"account":$("#account").val(),"password":$("#password").val()},  //传参 
			        dataType: "text",
			        success: function(zh) {
			           if(zhi == "good"){
			        	   window.location.href="<%=request.getContextPath()%>/user/user.jsp";
			           }else if(zh == "1"){
			        	   alert("请选择角色");
			           }else {
			        	   alert("添加失败");
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