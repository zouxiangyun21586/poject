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
	<body background="../images/jjs.jpg">
	<br/>
		<div class="layui-container" width="100%" height="100%" align="center">
	    	<div class="layui-row layui-col-space15">
				<div class="layui-col-md8"  style="width: 100%;">
					<button class="layui-btn" style="float:left;" id="adduser">
					<i class="layui-icon">&#xe654;</i> 添加账号</button>
					<button class="layui-btn layui-btn-normal" style="float:right;margin-left:0px;" id="sel">
					<i class="layui-icon">&#xe615;</i> 搜索</button>
					<input type="text" class="layui-input" placeholder="请输入要查询的账号" style="float:right;width:250px;" id = "select" name="select"/>
					<!-- <div class='layui-form layui-form-pane'><div class='layui-form-item'><div class='layui-input-inline'><select id='interest' name='interest' lay-filter='aihao' lay-search></select></div></div></div> -->
					<div class='layui-form layui-form-pane' style="float:right;">
					<div class="layui-inline">
					  <label class="layui-form-label">所属角色:</label>
				      <div class="layui-input-inline" style="width:120px;">
				        <select id='interest' name='interest' lay-filter='aihao' lay-search></select>
				      </div>
				    </div>
				    </div>
					<table class="layui-table" style="background-color:#ffffff6b;">
						<thead>
							<tr>
								<th>编号</th>
								<th>账号</th>
								<th>角色</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="tbodyId">
						</tbody>
					</table>
					<div id="page" class="page"></div>
				</div>
			</div>
		</div>
	</body>
	<script src="<%=request.getContextPath() %>/js/fenye.js"></script>
	<script src="../plugins/layui/layui.js"></script>
	<script src="../src/js/jquery-2.2.4.min.js"></script>
	<script>
	$(document).ready(function(){
		getData();
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
		        	$("#interest").append("<option value='quan'>全部</option>");
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
		});
		$("#sel").click(function (){
			getData();
		});
	});
	var state="";
	function getData(){
		layui.use([ 'layer', 'form' ], function() {
			var form = layui.form;
			<%-- $.ajax({     
		        //要用post方式      
		        type: "POST",//请求方式,默认GET
		        cache: false,  //是否缓存，false代表拒绝缓存
		        //方法所在页面和方法名      
		        url: "<%=request.getContextPath() %>/superAdminServlet?i=3",     
		        contentType: "application/text; charset=utf-8",  
		        dataType: "json",
		        success: function(zh) {
		        	$("#interest").append("<option value='quan'>全部</option>");
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
			}); --%>
			$.ajax({
      	       type:"GET", //请求方式     对应form的  method请求
      	       url:"<%=request.getContextPath()%>/superAdminServlet?i=1", //请求路径  对应 form的action路径
      	       cache: false,  //是否缓存，false代表拒绝缓存
      	       data:{"pageNow":getPar("pageNow"),"type":"list","select":$('#select').val(),"interest":$("#interest option:selected").val()},  //传参
      	       dataType: 'json',   //返回值类型 
      	       success:function(data){
      	    		var pageContent = page(data.pageCount,data.pageNow,data.pageCode);
      	    	 	var list =  data.list;
      	    	 	var tbodyContent = ""; 	
      	          	for(var i in list){
      	          		if("使用中"==list[i].stateStr){
      	          			state="<a href='#'  class='layui-btn layui-btn-danger layui-btn-xs' onclick='del("+list[i].id+")'><i class='layui-icon'>&#xe640;</i> 停用</a></td></tr>";
      	          		}else if("已停用"==list[i].stateStr){
      	          			state="<a href='#'  class='layui-btn layui-btn-danger layui-btn-xs' onclick='qi("+list[i].id+")'><i class='layui-icon'>&#xe640;</i> 启用</a></td></tr>";
      	          		}
      	        		tbodyContent += "<tr><td>"+list[i].id+"</td>"+
      	        	 		"<td>"+list[i].userName+"</td>"+
      	        	 		"<td>"+list[i].roleName+"</td>"+
      	        	 		"<td>"+list[i].stateStr+"</td>"+
      	        	 		"<td style='width:220px;' align='center'><a href='#' class='layui-btn layui-btn-xs' onclick='updecho(this);'><i class='layui-icon'>&#xe642;</i> 修改职位</a>&nbsp;"+state;
      	        	 		
      	       		}
      	          	$("#tbodyId").html(tbodyContent);
      	     	} 
      	     });
		});
		
	}
	function qi(code) {
		if (confirm("确认要启用？")) {
			window.location.href="<%=request.getContextPath()%>/superAdminServlet?del=del&i=3&id="+code;
		} else {
			window.close();
		}
	}
	//停用账号
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
	layui.use([ 'layer', 'element' ], function() {
		var laypage = layui.laypage,
		layer = layui.layer;
		$('#adduser').click(function(){
			layer.open({
				anim: 2,
				title : '添加账号',
				type: 2, //窗口类型
				resize:false,//禁止拉伸
				maxmin:false,//最大化,最小化
				shade: [0.3,'#000'],
				area: ['570px', '360px'],//窗口宽高
				content: ['add.jsp','no']
			});
		});
	});
		function updecho(obj){
			var id = $(obj).parent().parent().find("td").eq(0).text();//修改的用户表id
			var acc = $(obj).parent().parent().find("td").eq(1).text();//修改的账号
			var oldRole = $(obj).parent().parent().find("td").eq(2).text();//修改前的角色值
			//alert(id+"  "+oldRole+"  "+acc);
			layer.open({
				anim: 2,
				title : '修改职位',
				type: 2, //窗口类型
				resize:false,//禁止拉伸
				maxmin:false,//最大化,最小化
				shade: [0.3,'#000'],
				area: ['570px', '360px'],//窗口宽高
				content: ['/projectYr/superAdminServlet?i=4&id='+id+'&oldRole='+oldRole+'&acc='+acc,'no']
			});	
		}
	//修改调用的回显
	/* function updecho(obj){
		layui.use([ 'layer', 'form' ], function() {
			var id = $(obj).parent().parent().find("td").eq(0).text();//修改的id
			var a = $(obj).parent().parent().find("td").eq(1).text();//修改前的值
			var acc = $(obj).parent().parent().find("td").eq(2).text();//修改前的值
			var select="<div class='layui-form layui-form-pane' id='di'></div>";
			var btn="<button class='layui-btn layui-btn-xs' onclick='baoup(\""+a+"\",\""+id+"\",\""+acc+"\",this)'><i class='layui-icon'>&#xe618;</i> 保存</button><a href='#' onclick='quxiao(this,\""+a+"\");' class='layui-btn layui-btn-danger layui-btn-xs'><i class='layui-icon'>&#xe640;</i> 取消</a>";
			$(obj).parent().parent().find("td").eq(1).html(select);
			$(obj).parent().parent().find("td").eq(4).html(btn);
			role();//修改下拉框显示值
		});
	} */
	//修改保存    修改前的角色    修改的id  修改的账号
	function baoup(oldrole,id,acc,th){
		var id_array=new Array();  
		$('input[name="interes"]:checked').each(function(){  
			id_array.push($(this).val());//向数组中添加元素  
		});  
		var idstr=id_array.join(',');//将数组元素连接起来以构建一个字符串  
		alert(idstr);
		$.ajax({
 	       type:"POST", //请求方式     对应form的  method请求
 	       url:"<%=request.getContextPath()%>/superAdminServlet?i=4", //请求路径  对应 form的action路径
 	       cache: false,  //是否缓存，false代表拒绝缓存
 	       data:{"oldrole":oldrole,"upRoleId":idstr,"id":id,"acc":acc},  //传参 
 	       dataType: 'text',   //返回值类型 
 	       success:function(data){
 	    	   //1 没有这个角色,2 不能修改一样的
 	    	   if(data == "1"){
 	    		   alert("没有这个角色");
 	    	   }else if(data == "2"){
 	    		   alert("不能修改一样的");
 	    	   }else{
 	    		   $(th).parent().parent().find("td").eq(1).html(data);
 	    		  //$(th).parent().parent().find("td").eq(4).html("<a href='#' class='layui-btn layui-btn-xs' onclick='updecho(this)'><i class='layui-icon'>&#xe642;</i> 修改职位</a>&nbsp;");
	 	    		var btn="";
	 	    		var id=$(th).parent().parent().find("td").eq(0).text();
	 	    		var state=$(th).parent().parent().find("td").eq(3).text();
	 	    		if(state=="使用中"){
	 	    			btn="<a href='#' class='layui-btn layui-btn-xs' onclick='updecho()'><i class='layui-icon'>&#xe642;</i> 修改职位</a><a href='#' class='layui-btn layui-btn-danger layui-btn-xs' onclick='del("+id+")'><i class='layui-icon'>&#xe640;</i> 停用</a></td></tr>";
	 	    		}else if(state=="已停用"){
	 	    			btn="<a href='#' class='layui-btn layui-btn-xs' onclick='updecho()'><i class='layui-icon'>&#xe642;</i> 修改职位</a><a href='#' class='layui-btn layui-btn-danger layui-btn-xs' onclick='del("+id+")'><i class='layui-icon'>&#xe640;</i> 启用</a></td></tr>";
	 	    		}
	 	    		$(th).parent().parent().find("td").eq(4).html(btn);
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
	//取消编辑
	function quxiao(obj,a){
		var btn="";
		var id=$(obj).parent().parent().find("td").eq(0).text();
		var state=$(obj).parent().parent().find("td").eq(3).text();
		if(state=="使用中"){
			btn="<a href='#' class='layui-btn layui-btn-xs' onclick='updecho()'><i class='layui-icon'>&#xe642;</i> 修改职位</a><a href='#' class='layui-btn layui-btn-danger layui-btn-xs' onclick='del("+id+")'><i class='layui-icon'>&#xe640;</i> 停用</a></td></tr>";
		}else if(state=="已停用"){
			btn="<a href='#' class='layui-btn layui-btn-xs' onclick='updecho()'><i class='layui-icon'>&#xe642;</i> 修改职位</a><a href='#' class='layui-btn layui-btn-danger layui-btn-xs' onclick='del("+id+")'><i class='layui-icon'>&#xe640;</i> 启用</a></td></tr>";
		}
		$(obj).parent().parent().find("td").eq(1).html(a);
		$(obj).parent().parent().find("td").eq(4).html(btn);
	}
	//修改复选框显示值
	function role(){
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
	}
	//查询下拉框显示值
	<%-- function qurole(){
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
		        	$("#interest").append("<option value='quan'>全部</option>");
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
		});
	} --%>
	</script>
</html>