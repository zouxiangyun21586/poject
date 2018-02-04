<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
	</head>
	<link rel="stylesheet" href="../plugins/layui/css/global.css" media="all">
	<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
	<script src="../js/fenye.js"></script>
	<script src="../plugins/layui/layui.js"></script>
	<script src="../js/jquery-3.2.1.js"></script>
	<script>
		$(document).ready(function(){
			//确认
			$("#pofei").click(function(){
				$.ajax({
					type:"post", //请求方式     对应form的  method请求
					url:"<%=request.getContextPath()%>/buyersServlet?i=2", //请求路径  对应 form的action路径
					cache: false,  //是否缓存，false代表拒绝缓存
					data:{"id":$.getUrlParam('id'),"accountId":$.getUrlParam('accountId'),"wares":$.getUrlParam('wares'),"speciId":$.getUrlParam('speciId'),
						"nameType":$.getUrlParam('nameType'),"name":$.getUrlParam('name'),"describe":$.getUrlParam('describe'),"money":$.getUrlParam('money'),
						"number":$.getUrlParam('number'),"much":$.getUrlParam('much'),"shuru":$.getUrlParam('shuru')},  //传参
					dataType: "text",   //返回值类型 
					success: function(zh) {
			        	
			            
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
		$.getUrlParam = function(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象  
			var r = window.location.search.substr(1).match(reg); //匹配目标参数  
			if (r != null)
				return decodeURI(r[2]);
			return null; //返回参数值  
		}
		//调用法$.getUrlParam('name')		
		
		
	</script>
	<body>
		<input type="number" id="much" name="quantity" min="1" max="5"/>
		<br/>
		<textarea id="shuru" name="ziwojieshao" cols="30" rows="6" readonly="readonly">
			
		</textarea><br/>
		<input type="button" id="pofei" name="pofei" value="确定"/>
	</body>
</html>