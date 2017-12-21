<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/css/layui.css" media="all" />
		<title>Insert title here</title>
	</head>
	<body>
		<button type="submit" class="layui-btn" onclick="baoup()">完成</button>
        <button id="reset" class="layui-btn layui-btn-primary">重置</button>
        <div id="di"></div>
		<input type="text" id="zhi1" value="${id}"><!-- 账户表id -->
		<input type="text" id="zhi2" value="${acc}"><!-- 账号 -->
		<input type="text" id="zhi3" value="${roleId}"><!-- 角色id -->
		<a>${id}</a>
		<a>${acc}</a>
		<a>${roleId}</a>
	</body>
	<script src="<%=request.getContextPath() %>/plugins/layui/layui.js"></script>
	<script src="<%=request.getContextPath() %>/src/js/jquery-2.2.4.min.js"></script>
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
			        	var id_array=new Array();  
			        	id_array = $("#zhi3").val().split(',');
			        	var dataObj=zh;
			        	var a="";
			        	$.each(dataObj, function(index, item){
			        		for (var z = 0; z < id_array.length; z++) {
		        				if(id_array[z]!=item.id){
				        			a="<input id='interes' style='display:inline;' name='interes' type='checkbox' value='"+item.id+"' title='"+item.name+"'/>";
				        		}else{
					        		a="<input id='interes' style='display:inline;' name='interes' type='checkbox' value='"+item.id+"' title='"+item.name+"' checked='checked'/>";
				        		}
			        		}
			        		$("#di").append(a);
			        	}); 
			        	/* var id_array=new Array();
			        	var a="";
			        	var b="";
			        	id_array = $("#zhi3").val().split(',');
			        	alert(id_array); 
			            for(var i in zh){
			            	for (var z = 0; z < id_array.length; z++) {
				        		var ro = id_array[z];
				            	if(ro == zh[i].id){
				            		
				            		a="<input id='interes' name='interes' type='checkbox' value='"+zh[i].id+"' title='"+zh[i].name+"' checked='checked'/>";
				            		
				            		 	}else{
				            		a="<input id='interes' name='interes' type='checkbox' value='"+zh[i].id+"' title='"+zh[i].name+"'/>";
				            			}
			            	$("#di").append(a);
				            			}
			            } */
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
			alert($("zhi2").val()+"  "+$("zhi1").val()+"  "+$("zhi3").val()+"  ");
			var id_array=new Array();  
			$('input[name="interes"]:checked').each(function(){  
				id_array.push($(this).val());//向数组中添加元素  
			});  
			var idstr=id_array.join(',');//将数组元素连接起来以构建一个字符串  
			$.ajax({
	 	       type:"POST", //请求方式     对应form的  method请求
	 	       url:"<%=request.getContextPath()%>/superAdminServlet?i=4", //请求路径  对应 form的action路径
	 	       cache: false,  //是否缓存，false代表拒绝缓存
	 	       data:{"oldroleId":$("zhi3").val(),"upRoleId":idstr,"id":$("zhi1").val(),"acc":$("zhi2").val()},  //传参 
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