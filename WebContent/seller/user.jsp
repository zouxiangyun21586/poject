<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>卖家管理</title>
<link rel="stylesheet" href="../plugins/layui/css/global.css" media="all">
<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
</head>
<body>
    <br />
    <div class="layui-container" width="100%" height="100%" align="center">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md8" style="width: 100%;">
                <button class="layui-btn layui-btn-sm" style="float: left;"
                    id="addgoods">
                    <i class="layui-icon">&#xe654;</i> 添加商品
                </button>
                <button class="layui-btn layui-btn-sm layui-btn-normal"
                    style="float: right; margin-left: 0px;" id="sel">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </button>
                <input type="text" class="layui-input" placeholder="请输入要查询的商品名称"
                    style="float: right; width: 250px; height: 30px;" id="select" name="select"/>
                <table class="layui-table">
                    <thead>
                        <tr>
                            <td align="center" style="width:150px;">编号</td>
                            <td align="center" style="display: none;">卖家ID</td>
                            <td align="center" style="display: none;">商品ID</td>
                            <td align="center" style="display: none;">规格ID</td>
                            <td align="center" style="width:150px;">类型</td>
                            <td align="center" style="width:150px;">名称</td>
                            <td align="center" style="display: none;">描述</td>
                            <td align="center" style="width:110px;">价格</td>
                            <td align="center" style="width:110px;">数量</td>
                            <td align="center" style="width:80px;">添加</td>
                            <td align="center" style="width:80px;">上架</td>
                            <td align="center" style="width:80px;">下架</td>
                            <td align="center" style="width:280px;">操作</td>
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
            url:"../sellerServlet", //请求路径  对应 form的action路径
            cache: false,  //是否缓存，false代表拒绝缓存
            data:{"pageNow":getPar("pageNow"),"type":"list","select":$('#select').val()},  //传参
            dataType: "json",   //返回值类型 
            success:function(data){
                 var pageContent = page(data.pageCount,data.pageNow,data.pageCode);
                 var list =  data.list;
                 var html = "";
                 $("#t_body").empty();
                 for(var i in list){
                     if("0"==list[i].auditStatus){
                         html += "<tr><td align='center' id='id"+list[i].id+"'>"+list[i].id+"</td>"+
                         "<td align='center' style='display:none;'>"+list[i].seller_id+"</td>"+
                         "<td align='center' style='display:none;'>"+list[i].wares_id+"</td>"+
                         "<td align='center' style='display:none;'>"+list[i].speciID+"</td>"+
                         "<td align='center'>"+list[i].nameType+"</td>"+
                         "<td align='center'><a href='#' onclick='query("+list[i].id+")'>"+list[i].name+"</a></td>"+
                         "<td align='center' style='display:none;'>"+list[i].describe+"</td>"+
                         "<td align='center'>"+list[i].money+"</td>"+
                         "<td align='center'>"+list[i].number+"</td>"+
                         "<td align='center'>"+list[i].upFrameTime+"</td>"+
                         "<td align='center'>"+list[i].time+"</td>"+
                         "<td align='center'>"+list[i].downtime+"</td>"+
                         "<td align='center'><a href='#' onclick='shelves(this)'>上架</a>| <a href='#' onclick='del(this)'>删除</a>| <a href='#' id='update' onclick='update("+list[i].id+")'>修改</a></td></tr>";
                     }else if("1"==list[i].auditStatus){
                         html += "<tr><td align='center'>"+list[i].id+"</td>"+
                         "<td align='center' style='display:none;'>"+list[i].seller_id+"</td>"+
                         "<td align='center' style='display:none;'>"+list[i].wares_id+"</td>"+
                         "<td align='center' style='display:none;'>"+list[i].speciID+"</td>"+
                         "<td align='center'>"+list[i].nameType+"</td>"+
                         "<td align='center'><a href='#' onclick='query("+list[i].id+")'>"+list[i].name+"</a></td>"+
                         "<td align='center' style='display:none;'>"+list[i].describe+"</td>"+
                         "<td align='center'>"+list[i].money+"</td>"+
                         "<td align='center'>"+list[i].number+"</td>"+
                         "<td align='center'>"+list[i].upFrameTime+"</td>"+
                         "<td align='center'>"+list[i].time+"</td>"+
                         "<td align='center'>"+list[i].downtime+"</td>"+
                         "<td align='center'><a href='#' onclick='cancel(this)'>撤销</a></td></tr>";
                     }else if("2"==list[i].auditStatus){
                         html += "<tr><td align='center'>"+list[i].id+"</td>"+
                         "<td align='center' style='display:none;'>"+list[i].seller_id+"</td>"+
                         "<td align='center' style='display:none;'>"+list[i].wares_id+"</td>"+
                         "<td align='center' style='display:none;'>"+list[i].speciID+"</td>"+
                         "<td align='center'>"+list[i].nameType+"</td>"+
                         "<td align='center'><a href='#' onclick='query("+list[i].id+")'>"+list[i].name+"</a></td>"+
                         "<td align='center' style='display:none;'>"+list[i].describe+"</td>"+
                         "<td align='center'>"+list[i].money+"</td>"+
                         "<td align='center'>"+list[i].number+"</td>"+
                         "<td align='center'>"+list[i].upFrameTime+"</td>"+
                         "<td align='center'>"+list[i].time+"</td>"+
                         "<td align='center'>"+list[i].downtime+"</td>"+
                         "<td align='center'><a href='#' onclick='underCarriage(this)'>下架</a></td></tr>";
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
        content : '../sellerServlet?i=1&&o=2&&id='+d,
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
        content : '../sellerServlet?i=1&&o=1&&id='+obj,
        area : [ '400px', '505px' ],//宽高
        shadeClose : true
    });
}
//删除操作
function del(a){
    if(confirm("是否进入删除操作?")){
        var tr = $(a).parent().parent();
        var id = tr.find("td").eq(0).text();//把ID通过AJAX传入后台进行删除
        var seller_id = tr.find("td").eq(1).text();
        var wares_id = tr.find("td").eq(2).text();
        var time = tr.find("td").eq(10).text();
        var downtime = tr.find("td").eq(11).text();
        $.ajax({
            url:"../sellerServlet",
            type:"get",
            cache:false,//取消缓存
            async:true,//是否异步请求,修改false就表示同步,true表示异步
            data:{"id":id,"seller_id":seller_id,"wares_id":wares_id,"time":time,"downtime":downtime,"i":2},//传递参数
            success:function(result){
                if(result == "0")
                {
                    tr.remove();
                }
                else
                {
                   alert("删除失败");         
                }
            },//请求成功,进入该方法
            error:function(XMLHttpRequest, textStatus, errorThrown)
            {
                alert("发生错误!!");
            }
        });
    }
}
//上架操作
function shelves(e){
    var tr = $(e).parent().parent();
    var Id = tr.find("td").eq(0).text();
    var Seller_id = tr.find("td").eq(1).text();
    var Wares_id = tr.find("td").eq(2).text();
    var SpeciID = tr.find("td").eq(3).text();
    var NameType = tr.find("td").eq(4).text();
    var Name = tr.find("td").eq(5).text();
    var Describe = tr.find("td").eq(6).text();
    var Money = tr.find("td").eq(7).text();
    var Number = tr.find("td").eq(8).text();
    var UpFrameTime = tr.find("td").eq(9).text();
    var Time = tr.find("td").eq(10).text();
    var Downtime = tr.find("td").eq(11).text();
    $.ajax({
        url:"../sellerServlet",
        type:"get",
        cache:false,//取消缓存
        async:true,//是否异步请求,修改false就表示同步,true表示异步
        data:{"id":Id,"seller_id":Seller_id,"wares_id":Wares_id,"i":7},
        success:function(result){
            tr.html("");
            if(result=="0"){
                tr.html("<td align='center' style='display:none;'>"+Id+"</td><td align='center' style='display:none;'>"+Seller_id+"</td><td align='center' style='display:none;'>"+Wares_id+"</td><td align='center' style='display:none;'>"+SpeciID+"</td><td align='center'>"+NameType+"</td><td align='center'><a href='#' onclick='query("+Id+")'>"+Name+"</a></td><td align='center'>"+Describe+"</td><td align='center'>"+Money+"</td><td align='center'>"+Number+"</td><td align='center'>"+UpFrameTime+"</td><td align='center'>"+Time+"</td><td align='center'>"+Downtime+"</td><td align='center'><a href='#' onclick='cancel(this)'>撤销</a></td>");
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown)
        {
            alert("发生错误!!");
        }
    });
}
//撤销操作
function cancel(b){
    if(confirm("是否撤销上架?")){
        var tr = $(b).parent().parent();
        var Id = tr.find("td").eq(0).text();//把ID通过AJAX传入后台进行删除
        var Seller_id = tr.find("td").eq(1).text();
        var Wares_id = tr.find("td").eq(2).text();
        var SpeciID = tr.find("td").eq(3).text();
        var NameType = tr.find("td").eq(4).text();
        var Name = tr.find("td").eq(5).text();
        var Describe = tr.find("td").eq(6).text();
        var Money = tr.find("td").eq(7).text();
        var Number = tr.find("td").eq(8).text();
        var UpFrameTime = tr.find("td").eq(9).text();
        var Time = tr.find("td").eq(10).text();
        var Downtime = tr.find("td").eq(11).text();
        $.ajax({
            url:"../sellerServlet",
            type:"get",
            cache:false,//取消缓存
            async:true,//是否异步请求,修改false就表示同步,true表示异步
            data:{"id":Id,"i":3,"seller_id":Seller_id,"wares_id":Wares_id},
            success:function(result){
                if(result == "0")
                {
                    tr.html("<td align='center' style='display:none;'>"+Id+"</td><td align='center' style='display:none;'>"+Seller_id+"</td><td align='center' style='display:none;'>"+Wares_id+"</td><td align='center' style='display:none;'>"+SpeciID+"</td><td align='center'>"+NameType+"</td><td align='center'><a href='#' onclick='query("+Id+")'>"+Name+"</a></td><td align='center'>"+Describe+"</td><td align='center'>"+Money+"</td><td align='center'>"+Number+"</td><td align='center'>"+UpFrameTime+"</td><td align='center'>"+Time+"</td><td align='center'>"+Downtime+"</td><td align='center'><a href='#' onclick='shelves(this)'>上架</a>| <a href='#' onclick='del(this)'>删除</a>| <a href='#' id='update onclick='update("+Id+")'>修改</a></td>");
                }
                else
                {
                   alert("操作失败");         
                }
            },//请求成功,进入该方法
            error:function(XMLHttpRequest, textStatus, errorThrown)
            {
                alert("发生错误!!");
            }
        });
    }
}
//下架操作
function underCarriage(c){
    if(confirm("是否下架商品?")){
        var tr = $(c).parent().parent();
        var Id = tr.find("td").eq(0).text();//把ID通过AJAX传入后台进行删除
        var Seller_id = tr.find("td").eq(1).text();
        var Wares_id = tr.find("td").eq(2).text();
        var SpeciID = tr.find("td").eq(3).text();
        var NameType = tr.find("td").eq(4).text();
        var Name = tr.find("td").eq(5).text();
        var Describe = tr.find("td").eq(6).text();
        var Money = tr.find("td").eq(7).text();
        var Number = tr.find("td").eq(8).text();
        var UpFrameTime = tr.find("td").eq(9).text();
        var Time = tr.find("td").eq(10).text();
        $.ajax({
            url:"../sellerServlet",
            type:"get",
            cache:false,//取消缓存
            async:true,//是否异步请求,修改false就表示同步,true表示异步
            data:{"id":Id,"i":4,"wares_id":Wares_id},
            dataType:"json",
            success:function(result){
                $.each(result,function(index,item){
                    var Downtime = item.downtime;
                    tr.html("<td align='center' style='display:none;'>"+Id+"</td><td align='center' style='display:none;'>"+Seller_id+"</td><td align='center' style='display:none;'>"+Wares_id+"</td><td align='center' style='display:none;'>"+SpeciID+"</td><td align='center'>"+NameType+"</td><td align='center'><a href='#' onclick='query("+Id+")'>"+Name+"</a></td><td align='center'>"+Describe+"</td><td align='center'>"+Money+"</td><td align='center'>"+Number+"</td><td>"+UpFrameTime+"</td><td>"+Time+"</td><td>"+Downtime+"<td align='center'><a href='#' onclick='shelves(this)'>上架</a>| <a href='#' onclick='del(this)'>删除</a>| <a href='#' id='update onclick='update("+Id+")'>修改</a></td>");
                });
            },//请求成功,进入该方法
            error:function(XMLHttpRequest, textStatus, errorThrown)
            {
                alert("发生错误!!");
            }
        });
    }
}
    layui.use([ 'layer', 'laypage', 'element' ], function() {
        $('#addgoods').click(function() {
            layer.open({
                anim : 2,
                title : '添加商品',
                type : 2, //窗口类型
                resize : false,//false禁止拉伸
                scrollbar: true,//出现滚动条
                maxmin : false,//最大化,最小化
                shade : [ 0.3, '#000' ],
                area : [ '400px', '505px' ],//窗口宽高
                content : [ 'add.jsp', 'yes' ]
            });
        });
    });
</script>
</html>