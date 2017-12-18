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
				<button class="layui-btn layui-btn-sm" style="float: left;"
					id="adduser">
					<i class="layui-icon">&#xe654;</i>添加角色
				</button>
				<button class="layui-btn layui-btn-sm layui-btn-normal"
					style="float: right; margin-left: 0px;">
					<i class="layui-icon">&#xe615;</i>搜索
				</button>
				<input type="text" class="layui-input" placeholder="请输入要查询的角色"
					style="float: right; width: 250px; height: 30px;" />
				<table class="layui-table" id="table">
					<thead>
						<tr>
							<td>编号</td>
							<td>姓名</td>
							<td>账号</td>
							<td>职位</td>
							<td>状态</td>
							<td style="width: 150px;">操作</td>
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
$(document).ready(function(){
       
    $.ajax({
        url:'<%=request.getContextPath()%>/query', //请求的路径
        type:'get',//请求方式   
        dataType:'json',
        success:function(strjson){
           for (var i = 0; i< strjson.length; i++) {
               var a=strjson[i];
               $("#table").append("<tr><td>"+a.id+"</td><td>"+a.accountName+"</td><td>"+a.account+"</td><td>"+a.roleName+"</td><td>"+a.state+"</td><td><button class='layui-btn layui-btn-xs layui-btn-normal' onclick='update(this)'><i class='layui-icon'>&#xe642;</i> 修改</button><button class='layui-btn layui-btn-xs layui-btn-danger' onclick='del(this)' ><i class='layui-icon'>&#xe640;</i> 删除</button></td></tr>");
           }
        },error:function(XMLHttpRequest, textStatus, errorThrown)
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
    var mark = true;
    $.ajax({
           url:'<%=request.getContextPath()%>/delete',//请求的路径
           type:"get",//请求方式   
           async:false,//是否异步请求,修改false就表示同步,true表示异步
           data:{"id":id},//传递参数.json格式(这个明天再说)
           dataType:'json',
           success:function(result){
               if(result == "0")
               {
                 alert("权限不够");
                 mark = false;
               }
               else if(result == "1"){
                  alert("只有超级管理员能执行");
                  mark = false;
               }else if(result == "2"){
                   alert("需要向超级管理员申请这个权限");
                   mark=false;
               }
           },//请求成功,进入该方法
           error:function(XMLHttpRequest, textStatus, errorThrown)
           {
               alert("后台发生错误!!");
           }
      });
    
    if(mark)
    {
        $.ajax({
             url:"<%=request.getContextPath()%>/delete",//请求的路径
                type : "post",//请求方式   
                async : false,//是否异步请求,修改false就表示同步,true表示异步
                data : {
                    "id" : id
                },//传递参数.json格式(这个明天再说)
                success : function(result) {
                    if (result == "3") {
                        tr.remove();
                        alert("删除成功");
                    } else {
                        alert("删除失败");
                    }
                },//请求成功,进入该方法
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    alert("后台发生错误!!");
                }
            });

        }

    }
    layui.use([ 'layer', 'laypage', 'element' ], function() {
        $('#adduser').click(function() {
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