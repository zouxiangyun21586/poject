<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账号管理</title>
<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
</head>
<body>
	<br />
	<div class="layui-container" width="100%" height="100%" align="center">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md8" style="width: 100%;">
			<button class="layui-btn layui-btn-sm" style="float:left;" id="addrole">
                    <i class="layui-icon">&#xe654;</i> 添加职位</button>
			<form class="layui-form layui-form-pane" action="">
				<button class="layui-btn layui-btn-sm layui-btn-normal"
					style="float: right; margin-left: 0px;" onclick="search(this)" id="sousuo">
					<i class="layui-icon">&#xe615;</i>搜索
				</button>
				<input type="text" class="layui-input" placeholder="请根据职位查询"
					style="float: right; width: 250px; height: 30px;" />
				</form>
				<table class="layui-table" id="table">
					<thead>
						<tr>
							<td>编号</td>
							<td>职位</td>
							<td style="width: 250px;">操作</td>
						</tr>
					</thead>
					<tbody id="table"></tbody>
				</table>
			</div>
		</div>
	</div>
</body>
<script src="../plugins/layui/layui.js"></script>
<script src="../src/js/jquery-2.2.4.min.js"></script>
<script>

layui.use('form', function(){
	  var form = layui.form
	  
});

$(document).ready(function(){
    $.ajax({
       url:'<%=request.getContextPath()%>/query',
       type:'get',
       dataType:'json',
       success:function(strjson){
           for (var i = 0; i< strjson.length; i++){
               var a=strjson[i];
               if(a.roleName =="超级管理员"){
            	   rolebtn="<button class='layui-btn layui-btn-xs layui-btn-disabled' disabled=disabled onclick='update(this)' id='update'><i class='layui-icon'>&#xe640;</i>修改</button><button class='layui-btn layui-btn-xs layui-btn-disabled' disabled=disabled onclick='del(this)' id='del'><i class='layui-icon'>&#xe640;</i> 删除</button><button class='layui-btn layui-btn-xs layui-btn-disabled' disabled=disabled onclick='empowerment(this)' id='empowerment'><i class='layui-icon'>&#xe640;</i>赋权</button>";
               }
               else{
            	   rolebtn="<button class='layui-btn layui-btn-xs layui-btn-normal' onclick='update(this)'><i class='layui-icon'>&#xe642;</i> 修改</button><button class='layui-btn layui-btn-xs layui-btn-danger' onclick='del(this)' id='del'><i class='layui-icon'>&#xe640;</i> 删除</button><button class='layui-btn layui-btn-xs layui-btn-danger' onclick='empowerment(this)' id='empowerment'><i class='layui-icon'>&#xe640;</i>赋权</button>"; 
               }
               $("#table").append("<tr><td>"+a.id+"</td><td>"+a.roleName+"</td><td>"+rolebtn+"</td></tr>");
           }
       },
       error:function(XMLHttpRequest, textStatus, errorThrown)
       {
           alert("后台发生错误!!");
       }
   });
});



		function del(a)
		{
		    var tr = $(a).parent().parent();
		    var id = tr.find("td").eq(0).text();
		    alert(id);
		    //调用ajax把数据进行保存到数据库,添加到数据时,判断ID是否存在,如果存在不添加
		    $.ajax({
		        url:'<%=request.getContextPath()%>/delete',
		        type:'post',
		        async:false,
		        data:{"id":id},
		        dataType:'json',
		        success:function(strjson){
		        	tr.remove();
		        	alert("删除成功！");
		        },
		        error:function(XMLHttpRequest, textStatus, errorThrown)
		        {
		            alert("后台发生错误!!");
		        }
		    });
		    
		
		}



    layui.use([ 'layer', 'laypage', 'element' ], function() {
        $('#addrole').click(function() {
            layer.open({
                anim : 2,
                title : '添加角色',
                type : 2,//窗口类型
                resize : false,//禁止拉伸
                maxmin : false,//最大化,最小化
                shade : [ 0.3, '#000' ],
                area : [ '300px', '165px' ],//窗口宽高
                content : [ 'add.jsp', 'no' ]
            });
        });
    });
</script>
</html>