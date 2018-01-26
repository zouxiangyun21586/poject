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
    <div class="layui-container" width="100%" height="100%">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md8" style="width: 70%;">
            <button class="layui-btn layui-btn-sm" style="float:left;" id="addrole">
                    <i class="layui-icon">&#xe654;</i> 添加职位</button>
            <div class="layui-form layui-form-pane">
                <button class="layui-btn layui-btn-sm layui-btn-normal"
                    style="float: right; margin-left: 0px;" onclick="search(this)" id="sousuo">
                    <i class="layui-icon">&#xe615;</i>搜索
                </button>
                <input type="text" class="layui-input" placeholder="请根据职位查询"
                    style="float: right; width: 250px; height: 30px;" id="zhi">
                </div>
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
                <div id="page" class="page"></div>
            </div>
             <div class="layui-form" style="display:none;" id="layui-xtree-demo1" style="float:left;width:250px; border:0px solid #009688;"></div>
        </div>
    </div>
</body>
<script src="../plugins/layui/layui.js"></script>
<script src="../js/layui-xtree.js"></script>
<script src="../js/rolefenye.js"></script>
<script src="json.js"></script>
<script src="../src/js/jquery-2.2.4.min.js"></script>
<script>

layui.use('form', function(){
      var form = layui.form;
      var xtree = new layuiXtree({
          elem: 'layui-xtree-demo1' //放xtree的容器（必填）
           , form: form              //layui form对象 （必填）
           , data: json              //数据，结构请参照下面 （必填）
           , isopen: false            //初次加载时全部展开，默认true （选填）
           , color: "#000"           //图标颜色 （选填）
           , icon: {                 //图标样式 （选填）
               open: "&#xe7a0;"      //节点打开的图标
               , close: "&#xe622;"   //节点关闭的图标
               , end: "&#xe621;"     //末尾节点的图标
           }
      });
});

/*
    页面加载完成后进入这里执行查询
*/
$(document).ready(function(){
    $(window).resize(function() {
        var width = $(window).width();
    });
    getData(); 
    $("#sousuo").click(function (){
        getData();
    });
});

/* 删除 */
function del(a)
{
    if(confirm("你确定删除这个角色？")){
        var tr = $(a).parent().parent();
        var id = tr.find("td").eq(0).text();
        //调用ajax把数据进行保存到数据库,添加到数据时,判断ID是否存在,如果存在不添加
        $.ajax({
            url:'<%=request.getContextPath()%>/roleServlet?role=2',
            type:'get',
            async:false,
            data:{"id":id},
            success:function(strjson){
                tr.remove();
                layer.msg('删除成功!', 
                {icon: 1}
                ); 
            },
            error:function(XMLHttpRequest, textStatus, errorThrown)
            {
                alert(XMLHttpRequest.status);  
                alert(XMLHttpRequest.readyState);  
                alert(textStatus); 
                layer.msg('删除失败!', 
                {icon: 1}
                ); 
            }
        });
    }
}



/* 修改 */
function update(upd){
    var tr = $(upd).parent().parent();
       var id = tr.find("td").eq(0).text();
       var roleName = tr.find("td").eq(1).text();
       tr.html("<td>"+id+"</td>"+"<td><input type='text' id='roleName' value="+roleName+"></td><td><button class='layui-btn layui-btn-xs layui-btn-danger' onclick=\"cancel(this,'"+id+"','"+roleName+"');\"><i class='layui-icon'>&#xe640;</i> 取消</button><button onclick='updPreservation(this);' class='layui-btn layui-btn-xs'><i class='layui-icon'>&#xe640;</i> 保存</button></td>");
}
/* 修改取消 */
function cancel(updc,id,rome){
    if(rome == "超级管理员"){
              rolebtn="<button class='layui-btn layui-btn-xs layui-btn-disabled' disabled=disabled onclick='update(this)' id='update'><i class='layui-icon'>&#xe640;</i>修改</button><button class='layui-btn layui-btn-xs layui-btn-disabled' disabled=disabled onclick='del(this)' id='del'><i class='layui-icon'>&#xe640;</i> 删除</button><button class='layui-btn layui-btn-xs layui-btn-disabled' disabled=disabled onclick='empowerment(this)' id='empowerment'><i class='layui-icon'>&#xe640;</i>赋权</button>";
          }
          else{
              rolebtn="<button class='layui-btn layui-btn-xs layui-btn-normal' onclick='update(this)'><i class='layui-icon'>&#xe642;</i> 修改</button><button class='layui-btn layui-btn-xs layui-btn-danger' onclick='del(this)' id='del'><i class='layui-icon'>&#xe640;</i> 删除</button><button class='layui-btn layui-btn-xs layui-btn-danger' onclick='empowerment(this)' id='empowerment'><i class='layui-icon'>&#xe640;</i>赋权</button>"; 
          }
    var tr = $(updc).parent().parent();
    tr.html("<td>"+id+"</td><td>"+rome+"</td><td>"+rolebtn+"</td>");
}
/* 修改保存 */
      function updPreservation(updp){
        var tr = $(updp).parent().parent();
          var id = tr.find("td").eq(0).text();
          var roleName = tr.find("td").eq(1).find("input").val();
          $.ajax({
              type: "get",  // 请求方式(post或get)
              async:false,  //默认true(异步请求),设置为false(同步请求)
              url:"<%=request.getContextPath()%>/roleServlet?role=4", // 发送请求的地址
              dataType:"text",
              data:{"id":id,"roleName":roleName},   // 传参数
              success:function(){
                    if(roleName == "超级管理员"){
                        rolebtn="<button class='layui-btn layui-btn-xs layui-btn-disabled' disabled=disabled onclick='update(this)' id='update'><i class='layui-icon'>&#xe640;</i>修改</button><button class='layui-btn layui-btn-xs layui-btn-disabled' disabled=disabled onclick='del(this)' id='del'><i class='layui-icon'>&#xe640;</i> 删除</button><button class='layui-btn layui-btn-xs layui-btn-disabled' disabled=disabled onclick='empowerment(this)' id='empowerment'><i class='layui-icon'>&#xe640;</i>赋权</button>";
                    }
                    else{
                        rolebtn="<button class='layui-btn layui-btn-xs layui-btn-normal' onclick='update(this)'><i class='layui-icon'>&#xe642;</i> 修改</button><button class='layui-btn layui-btn-xs layui-btn-danger' onclick='del(this)' id='del'><i class='layui-icon'>&#xe640;</i> 删除</button><button class='layui-btn layui-btn-xs layui-btn-danger' onclick='empowerment(this)' id='empowerment'><i class='layui-icon'>&#xe640;</i>赋权</button>"; 
                    }
                    tr.html("<td>"+id+"</td><td>"+roleName+"</td><td>"+rolebtn+"</td>");
                    //alert("修改成功^o^");
                    layer.msg('修改成功^o^', 
                            {icon: 1}
                            );
              },
              error:function(XMLHttpRequest, textStatus, errorThrown)
              {
                alert(XMLHttpRequest.status);  
                  alert(XMLHttpRequest.readyState);  
                  alert(textStatus); 
                  alert("后台发生错误!!");
              }
          });
}

/* 赋权 */
 function empowerment(a){
    $("#layui-xtree-demo1").toggle();
}
 

/*分页 */
var state="";
function getData(){
    layui.use([ 'layer', 'form' ], function() {
        var form = layui.form;
        $.ajax({
           type:"get", //请求方式     对应form的  method请求
           url:"<%=request.getContextPath()%>/roleServlet?role=1", //请求路径  对应 form的action路径
           cache:false,  //是否缓存，false代表拒绝缓存
           data:{"pageNow":getPar("pageNow"),"type":"list","select":$('#zhi').val()},  //传参
           dataType: 'json',   //返回值类型 
           success:function(data){
               var pageContent = page(data.pageCount,data.pageNow,data.pageCode);
                var list =  data.list;
                var b="";
                for (var i in list){
                    if(list[i].name =="超级管理员"){
                        rolebtn="<button class='layui-btn layui-btn-xs layui-btn-disabled' disabled=disabled onclick='update(this)' id='update'><i class='layui-icon'>&#xe640;</i>修改</button><button class='layui-btn layui-btn-xs layui-btn-disabled' disabled=disabled onclick='del(this)' id='del'><i class='layui-icon'>&#xe640;</i> 删除</button><button class='layui-btn layui-btn-xs layui-btn-disabled' disabled=disabled onclick='empowerment(this)' id='empowerment'><i class='layui-icon'>&#xe640;</i>赋权</button>";
                    }
                    else{
                        rolebtn="<button class='layui-btn layui-btn-xs layui-btn-normal' onclick='update(this)'><i class='layui-icon'>&#xe642;</i> 修改</button><button class='layui-btn layui-btn-xs layui-btn-danger' onclick='del(this)' id='del'><i class='layui-icon'>&#xe640;</i> 删除</button><button class='layui-btn layui-btn-xs layui-btn-danger' onclick='empowerment(this)' id='empowerment'><i class='layui-icon'>&#xe640;</i>赋权</button>"; 
                    }
                    b+="<tr><td>"+list[i].id
                    +"</td><td>"+list[i].name+"</td><td>"
                    +rolebtn+"</td></tr>";
                }
                $("#table").html(b);
            } 
         });
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