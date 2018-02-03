<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>审核管理</title>
<link rel="stylesheet" href="../plugins/layui/css/global.css" media="all">
<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
</head>
<body>
    <br />
    <div class="layui-container" width="100%" height="100%" align="center">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md8" style="width: 100%;">
                <button class="layui-btn layui-btn-sm layui-btn-normal"
                    style="float: right; margin-left: 0px;" id="sel">
                    <i class="layui-icon">&#xe615;</i> 查询
                </button>
                <input type="text" class="layui-input" placeholder="请输入要查询的账户或商品名称"
                    style="float: right; width: 250px; height: 30px;" id="select" name="select"/>
                <table class="layui-table">
                    <thead>
                        <tr>
                            <td align="center" style="display: none;">保存商品ID</td>
                            <td align="center" style="display: none;">表ID</td>
                            <td align="center" style="display: none;">账户ID</td>
                            <td align="center" style="width:150px;"> 商品编号</td>
                            <td align="center" style="width:150px;"> 账户名称</td>
                            <td align="center" style="width:150px;"> 商品类型</td>
                            <td align="center" style="width:150px;"> 商品名称</td>
                            <td align="center" style="width:150px;"> 审核状态</td>
                            <td align="center" style="width:280px;"> 申请时间</td>
                            <td align="center" style="width:280px;"> 基本操作</td>
                        </tr>
                    </thead>
                    <tbody id="t_body">
                    </tbody>
                </table>
                <div id="page" class="page"></div>
            </div>
        </div>
    </div>
</body>
<script src="../js/fenye.js"></script>
<script src="../plugins/layui/layui.js"></script>
<script src="../js/jquery-3.2.1.js"></script>
<script>
$(document).ready(function(){
    Data();
    $("#sel").click(function(){
        Data();
    });
});
function Data(){
    layui.use([ 'layer', 'form' ], function() {
        var form = layui.form;
        form.render('select');
        $.ajax({
            type:"post", //请求方式     对应form的  method请求
            url:"../auditSellerServlet", //请求路径  对应 form的action路径
            cache: false,  //是否缓存，false代表拒绝缓存
            data:{"pageNow":getPar("pageNow"),"type":"list","select":$('#select').val()},  //传参
            dataType: "json",   //返回值类型 
            success:function(data){
                 var pageContent = page(data.pageCount,data.pageNow,data.pageCode);
                 var list =  data.list;
                 var html = "";
                 $("#t_body").empty();
                 for(var i in list){
                     if("1"==list[i].auditStatus){
                         list[i].auditStatus = "等待审核";
                         html += "<tr><td align='center' style='display:none;'>"+list[i].id+"</td>"+
                         "<td align='center' id='id"+list[i].auditID+"'>"+list[i].auditID+"</td>"+
                         "<td align='center' style='display:none;'>"+list[i].seller_id+"</td>"+
                         "<td align='center' style='display:none;'>"+list[i].wares_id+"</td>"+
                         "<td align='center'>"+list[i].auditName+"</td>"+
                         "<td align='center'>"+list[i].nameType+"</td>"+
                         "<td align='center'><a href='#' onclick='query("+list[i].auditID+")'>"+list[i].name+"</a></td>"+
                         "<td align='center'>"+list[i].auditStatus+"</td>"+
                         "<td align='center'>"+list[i].addTime+"</td>"+
                         "<td align='center'><a href='#' onclick='pass(this)'>允许</a>| <a href='#' onclick='del(this)'>禁止</a>| <a href='#' id='update' onclick='update("+list[i].auditID+")'>修改</a></td></tr>";
                     }
                 }
                 $("#t_body").append(html);
             } 
          });
    });
}
//修改商品保存信息
function update(d){
    layer.open({
        title : '修改商品',
        type : 2,
        anim : 2,
        maxmin : false,
        resize : false,
        scrollbar : true,
        //页面路径
        content : '../auditSellerServlet?i=3&&o=1&&auditID='+d,
        area : [ '400px', '505px' ],//宽高
        shadeClose : true
    });
}
//商品详细信息查询
function query(obj){
    layer.open({
        title : '详细信息',
        type : 2,
        anim : 2,
        maxmin : false,
        resize : true,
        scrollbar : true,
        //页面路径
        content : '../auditSellerServlet?i=3&&o=2&&auditID='+obj,
        area : [ '400px', '505px' ],//宽高
        shadeClose : true
    });
}
//禁止上架
function del(a){
    if(confirm("是否禁止该商品上架?")){
        var tr = $(a).parent().parent();
        var id = tr.find("td").eq(0).text();//把ID通过AJAX传入后台进行删除
        var auditID = tr.find("td").eq(1).text();
        var seller_id = tr.find("td").eq(2).text();
        var wares_id = tr.find("td").eq(3).text();
        layer.open({
            title : '禁止提醒',
            type : 2,
            anim : 2,
            maxmin : false,
            resize : true,
            scrollbar : true,
            //页面路径
            content : '../auditSellerServlet?i=5&&o=1&&id='+id+'&&auditID='+auditID+'&&seller_id='+seller_id+'&&wares_id='+wares_id,
            area : [ '400px', '505px' ],//宽高
            shadeClose : true
        });
    }
}
//允许上架
function pass(b){
    var tr = $(b).parent().parent();
    var id = tr.find("td").eq(0).text();//把ID通过AJAX传入后台进行删除
    var auditID = tr.find("td").eq(1).text();
    var seller_id = tr.find("td").eq(2).text();
    var wares_id = tr.find("td").eq(3).text();
    layer.open({
        title : '上架提醒',
        type : 2,
        anim : 2,
        maxmin : false,
        resize : true,
        scrollbar : true,
        //页面路径
        content : '../auditSellerServlet?i=5&&o=2&&id='+id+'&&auditID='+auditID+'&&seller_id='+seller_id+'&&wares_id='+wares_id,
        area : [ '400px', '505px' ],//宽高
        shadeClose : true
    });
}

</script>
</html>