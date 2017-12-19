<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
</head>
<script>
	$("#aa").text();
</script>
<body>
<ul id="demo"></ul>
<ul class="layui-nav layui-nav-tree" lay-filter="test" style="width:355px;">
<c:forEach var="a" items="${genList}">
	<li class="layui-nav-item">
    <a href="javascript:;" id="aa">${a.fatherName }</a>
    <dl class="layui-nav-child">
    <c:redirect url="<%=request.getContextPath() %>/powerServlet?i=0&name="+${a.fatherName }/>
    <c:forEach var="b" items="${testList}">
      <dd>
	      <label class="layui-form-label" style="width:50px;">${b.fatherName }</label>
	      <div class="layui-input-inline">
	      	<input class="layui-input" style="width:100px;background-color:#ffffff75;">
	      </div>
	      <span class="layui-badge-rim" style="line-height:33px;height:33px;">状态:使用中</span>
	     <%--  <c:if test="${b.state == '使用中'}">  
		      <button class="layui-btn layui-btn-danger"><i class='layui-icon'>&#xe640;</i> 停用</button>
	      </c:if>
	      <c:if test="${b.state == '已停用'}">  
		      <button class="layui-btn layui-btn-danger"><i class='layui-icon'>&#xe640;</i> 启用</button>
	      </c:if> --%>
      </dd>
     </c:forEach> 
    </dl>
  </li>
</c:forEach>
</ul>
</body>
<script src="plugins/layui/layui.js"></script>
<script src="src/js/jquery-2.2.4.min.js"></script>
<script>
layui.use(['tree','element'], function(){
	layui.tree({
		  elem: '#demo',
		  
		  nodes: [
		          
		  {
		    name: 'aa',
		    children: [{
		      name: 'bb'
		    },{
		      name: 'cc'
		    }]
		  }
		  
		  ,
		  
		  {
		    name: '11',
		    children: [{
		      name: '22'
		    },{
		    	name:'33'
		    }]
		  }
		  
		  ]
	
		});
	});
</script>
</html>