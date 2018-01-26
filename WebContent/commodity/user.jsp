<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>买家管理</title>
<link rel="stylesheet" href="../plugins/layui/css/global.css" media="all">
<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
</head>
	<style>
		video#bgvid {
			position: fixed;
			right: 0;
			bottom: 0;
			min-width: 50%;
			min-height: 100%;
			width: auto;
			height: auto;
			z-index: -100;
			background: url(polina.jpg) no-repeat;
			background-size: cover;
		}
	</style>
<body>
	<video height="500" id="bgvid" autoplay loop>
		<source src="../images/big-v8.webm" type="video/webm"/>
		<source src="../images/big-v8.webm" type="video/mp4" />
	</video>
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
                <table class="layui-table" style="background-color:#ffffff6b;">
                    <thead>
                        <tr>
                            <td style="display: none;">发布ID</td>
                            <td style="display: none;">卖家ID</td>
                            <td style="display: none;">商品ID</td>
                            <td style="display: none;">规格ID</td>
                            <td align="center" style="width:150px;">类型</td>
                            <td align="center" style="width:150px;">名称</td>
                            <td align="center" style="width:150px;">描述</td>
                            <td align="center" style="width:110px;">价格</td>
                            <td align="center" style="width:110px;">数量</td>
                            <td align="center" style="width:80px;">上架</td>
                            <td align="center" style="width:280px;">操作</td>
                        </tr>
                    </thead>
                    <tbody id="t_body"></tbody>
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
            url:"../buyersServlet", //请求路径  对应 form的action路径
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
                         html += "<tr><td align='center' style='display:none;' id='id"+list[i].id+"'>"+list[i].id+"</td>"+
                         "<td align='center' style='display:none;'>"+list[i].seller_id+"</td>"+
                         "<td align='center' style='display:none;'>"+list[i].wares_id+"</td>"+
                         "<td align='center' style='display:none;'>"+list[i].speciID+"</td>"+
                         "<td align='center'>"+list[i].nameType+"</td>"+
                         "<td align='center'><a href='#' onclick='query("+list[i].id+")'>"+list[i].name+"</a></td>"+
                         "<td align='center'>"+list[i].describe+"</td>"+
                         "<td align='center'>"+list[i].money+"</td>"+
                         "<td align='center'>"+list[i].number+"</td>"+
                         "<td align='center'>"+list[i].upFrameTime+"</td>"+
                         "<td align='center'><a href='#'  class='layui-btn layui-btn-danger layui-btn-xs' onclick='gou(this)'><i class='layui-icon'>&#xe640;</i> 购买</a></td></tr>";
                     }
                 }
                 $("#t_body").append(html);
             } 
          });
    });
}
//购买
function gou(a){
	var id=$(a).parent().parent().find("td").eq(0).text();//自己的发布表id
	var seller_id=$(a).parent().parent().find("td").eq(1).text();
	var wares_id=$(a).parent().parent().find("td").eq(2).text();
	var speciID=$(a).parent().parent().find("td").eq(3).text();
	var nameType=$(a).parent().parent().find("td").eq(4).text();//类型
	var name=$(a).parent().parent().find("td").eq(5).text();//名称
	var describe=$(a).parent().parent().find("td").eq(6).text();//描述
	var money=$(a).parent().parent().find("td").eq(7).text();//价格
	var number=$(a).parent().parent().find("td").eq(8).text();//数量
	var upFrameTime=$(a).parent().parent().find("td").eq(9).text();//上架时间
	alert(id+" "+seller_id+" "+wares_id+" "+speciID+" "+nameType+" "+describe+" "+money+" "+number+" "+upFrameTime);
	layui.use([ 'layer', 'form' ], function() {
		var form = layui.form;
		$.ajax({
  	       type:"GET", //请求方式     对应form的  method请求
  	       url:"<%=request.getContextPath()%>/buyersServlet?i=2", //请求路径  对应 form的action路径
  	       cache: false,  //是否缓存，false代表拒绝缓存
  	       data:{"pageNow":getPar("pageNow"),"type":"list","select":$('#select').val(),"interest":$("#interest option:selected").val()},  //传参
  	       dataType: 'json',   //返回值类型 
  	       success:function(data){
  	    		var pageContent = page(data.pageCount,data.pageNow,data.pageCode);
  	    	 	var list =  data.list;
  	    	 	var tbodyContent = ""; 	
  	          	for(var i in list){
  	       		}
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
        content : '../buyersServlet?i=1&&o=1&&id='+obj,
        area : [ '400px', '505px' ],//宽高
        shadeClose : true
    });
}

</script>
</html>