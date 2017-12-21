<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
		<title>Insert title here</title>
	</head>
	<body>
		<div class="layui-container" width="100%" height="100%" align="center">
			<div class="layui-row layui-col-space15">
				<div class="layui-col-md8" style="width: 100%;">
					<div class="layui-form layui-form-pane" id="di">
						
						<button type="submit" class="layui-btn" onclick="baoup()">完成</button>
				        <button id="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</div>
		</div><br/>
		<input type="hidden" id="zhi1" value="${param.id}">
		<input type="hidden" id="zhi2" value="${param.oldRole}">
		<input type="hidden" id="zhi3" value="${param.acc}">
	</body>
	<script src="../plugins/layui/layui.js"></script>
	<script src="../src/js/jquery-2.2.4.min.js"></script>
	<script>
		$(document).ready(function(){
			layui.use([ 'layer', 'form' ], function() {
				var form = layui.form;
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
			            	$("#di").append("<input id='interes' name='interes' type='checkbox' value='"+zh[i].id+"' />"+zh[i].name+" ");
			            }
			            form.render('checkbox');
			        },     
			        error: function(XMLHttpRequest, textStatus, errorThrown) {
			     	   alert("失败");
			           alert(XMLHttpRequest.status);//200客户端请求已成功
			           alert(XMLHttpRequest.readyState);//4 响应内容解析完成，可以在客户端调用了
			           alert(textStatus);//parsererror
			   		}
				});
			});
		});
		//保存修改
		function baoup(){
			var id_array=new Array();  
			$('input[name="interes"]:checked').each(function(){  
				id_array.push($(this).val());//向数组中添加元素  
			});  
			var idstr=id_array.join(',');//将数组元素连接起来以构建一个字符串  
			$.ajax({
	 	       type:"POST", //请求方式     对应form的  method请求
	 	       url:"<%=request.getContextPath()%>/superAdminServlet?i=4", //请求路径  对应 form的action路径
	 	       cache: false,  //是否缓存，false代表拒绝缓存
	 	       data:{"oldrole":$("zhi2").val(),"upRoleId":idstr,"id":$("zhi1").val(),"acc":$("zhi3").val()},  //传参 
	 	       dataType: 'text',   //返回值类型 
	 	       success:function(data){
	 	    	  if(data == "1"){
	 	    		   alert("请选择职位");
	 	    	  }else{
		 	    	  layui.use(['layer', 'laypage', 'element'], function(){
						layer.msg('修改成功!', 
						{icon: 1}
						); 
						setTimeout('parent.location.href=parent.location.href;','1000');
					  });
	 	    	  }
	 	       },
	 	       error: function(XMLHttpRequest, textStatus, errorThrown) {
		     	   alert("失败");
		           alert(XMLHttpRequest.status);//200客户端请求已成功
		           alert(XMLHttpRequest.readyState);//4 响应内容解析完成，可以在客户端调用了
		           alert(textStatus);//parsererror
		   		}
	 	    });
		}
	</script>
</html>