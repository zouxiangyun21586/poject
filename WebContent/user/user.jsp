<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>账号管理</title>
		<link rel="stylesheet" href="../plugins/layui/css/global.css" media="all">
		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
	</head>
	<body>
	<br/>
		<div class="layui-container" width="100%" height="100%" align="center">
	    	<div class="layui-row layui-col-space15">
				<div class="layui-col-md8"  style="width: 100%;">
					<button class="layui-btn layui-btn-sm" style="float:left;" id="adduser">
					<i class="layui-icon">&#xe654;</i> 添加账号</button>
					<button class="layui-btn layui-btn-sm layui-btn-normal" style="float:right;margin-left:0px;" id="sel">
					<i class="layui-icon">&#xe615;</i> 搜索</button>
					<input type="text" class="layui-input" placeholder="请输入要查询的账号" style="float:right;width:250px;height:30px;" id = "select" name="select"/>
					<table class="layui-table">
						<thead>
							<tr>
								<th>编号</th>
								<th>角色</th>
								<th>账号</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="tbodyId">
						</tbody>
					</table>
					<div id="page" class="page"></div>
					<div id="demo7"></div>
				</div>
			</div>
		</div>
	</body>
	<script src="../plugins/layui/layui.js"></script>
	<script src="../src/js/jquery-2.2.4.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/myjs.js"></script>
	<script>
	$(document).ready(function(){
		getData();
		/* $("#sel").click(function (){
			getData();
		}); */
	});
	
	function getData(){
		$.ajax({
 	       type:"GET", //请求方式     对应form的  method请求
 	       url:"<%=request.getContextPath()%>/superAdminServlet?i=1", //请求路径  对应 form的action路径
 	       cache: false,  //是否缓存，false代表拒绝缓存
 	       data:{"pageNow":getPar("pageNow"),"type":"list","select":$('#select').val()},  //传参 
 	       dataType: 'json',   //返回值类型 
 	       success:function(data){
 	    		var pageContent = page(data.pageCount,data.pageNow,data.pageCode);
 	    	 	var list =  data.list;
 	    	 	var tbodyContent = ""; 	
 	          	for(var i in list){ 
 	        		tbodyContent += "<tr><td>"+list[i].id+"</td>"+
 	        	 		"<td>"+list[i].userName+"</td>"+
 	        	 		"<td>"+list[i].roleName+"</td>"+
 	        	 		"<td>"+list[i].stateStr+"</td>"+
 	        	 		"<td style='width:220px;' align='center'><a href='#' class='layui-btn layui-btn-xs' onclick='updecho("+list[i].id+")'><i class='layui-icon'>&#xe642;</i> 编辑</a>&nbsp;"+
 	        	 		"<a href='#' data-id='1' data-opt='del' class='layui-btn layui-btn-danger layui-btn-xs' onclick='del("+list[i].id+")'><i class='layui-icon'>&#xe640;</i> 停用</a></td></tr>";
 	       		}
 	          	$("#tbodyId").html(tbodyContent); 
 	     	} 
 	     }); 
	}
	
	function del(code) {
		if (confirm("确认要停用？")) {
			window.location.href="<%=request.getContextPath()%>/superAdminServlet?del=del&i=2&id="+code;
		} else {
			window.close();
		}
		<%-- $.ajax({
 	       type:"GET", //请求方式     对应form的  method请求
 	       url:"<%=request.getContextPath()%>/superAdminServlet?i=2", //请求路径  对应 form的action路径
 	       cache: false,  //是否缓存，false代表拒绝缓存
 	       data:{"id":code},  //传参 
 	       //dataType: 'text',   //返回值类型 
 	       success:function(data){
 	    	   var tx = $(this).parent().parent().find("td").eq(3).text();
 	    	   if(tx == "使用中"){
 	    	   		$(this).parent().parent().find("td").eq(3).html("已停用");
 	    	   }else if(tx == ""){
 	    		   
 	    	   }
 	       } 
 	    }); --%>
	}
	layui.use([ 'layer', 'laypage', 'element' ], function() {
		var laypage = layui.laypage,
		layer = layui.layer;
		laypage.render({
		    elem: 'demo7',
		    count: 100,
		    limits:[5,10,15],
		    layout: ['prev', 'page', 'next', 'limit', 'skip','count'],
		    jump: function(obj){
		      console.log(obj)
		    }
		  });
		$('#adduser').click(function(){
			layer.open({
				anim: 2,
				title : '添加账号',
				type: 2, //窗口类型
				resize:false,//禁止拉伸
				maxmin:false,//最大化,最小化
				shade: [0.3,'#000'],
				area: ['300px', '330px'],//窗口宽高
				content: ['add.jsp','no']
			});
		});
	});
	</script>
</html>