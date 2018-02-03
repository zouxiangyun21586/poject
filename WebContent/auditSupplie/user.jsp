<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="../js/jquery-3.2.1.js"></script><%-- <%=request.getContextPath() %> --%>
	<script src="../plugins/layui/layui.js"></script>
	<script src="../js/fenye.js"></script>
	<link rel="stylesheet" href="../plugins/layui/css/global.css" media="all">
	<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>供应商审核</title>
</head>
<script type="text/javascript">
/* 页面加载完出现 */
    $(document).ready(function(){
    	$(window).resize(function(){
    		var width = $(window).width();
    	})
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
                type:"get", //请求方式     对应form的  method请求
                url:"../auditSupplieServlet", //请求路径  对应 form的action路径
                cache: false,  //是否缓存，false代表拒绝缓存
                data:{"i":0,"pageNow":getPar("pageNow"),"type":"list","select":$('#select').val()},  //传参
                dataType: "json",   //返回值类型 
                success:function(data){
                     var pageContent = page(data.pageCount,data.pageNow,data.pageCode);
                     var list =  data.list;
                     var html = "";
                     $("#t_body").empty();
                     for(var i in list){
                         if("1"==list[i].auditStatus){
                             list[i].auditStatus = "等待审核";
                             html += "<tr><td align='center' style='display:none;'>"+list[i].suptId+"</td>"+
                             "<td align='center' style='display:none;' id='id"+list[i].auditId+"'>"+list[i].auditId+"</td>"+
                             "<td align='center' style='display:none;'>"+list[i].account_id+"</td>"+
                             "<td align='center'>"+list[i].merId+"</td>"+
                             "<td align='center'>"+list[i].account+"</td>"+
                             "<td align='center'>"+list[i].typeName+"</td>"+
                             "<td align='center'><a href='#' onclick='query("+list[i].auditId+")'>"+list[i].commo+"</a></td>"+
                             "<td align='center'>"+list[i].auditStatus+"</td>"+
                             "<td align='center'>"+list[i].auditTime+"</td>"+
                             "<td align='center'><a href='#' onclick='pass(this)'>允许</a>| <a href='#' onclick='del(this)'>禁止</a>| <a href='#' id='update' onclick='update("+list[i].auditId+")'>修改</a></td></tr>";
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
            content : '../auditSupplieServlet?i=1&&o=1&&auditId='+d,
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
            content : '../auditSupplieServlet?i=1&&o=2&&auditId='+obj,
            area : [ '400px', '505px' ],//宽高
            shadeClose : true
        });
    }
    // 允许上架
    function pass(a){
    	var tr = $(a).parent().parent();
    	var suptId = tr.find("td").eq(0).text();
    	var auditId = tr.find("td").eq(1).text();
    	var account_id = tr.find("td").eq(2).text();
    	var merId = tr.find("td").eq(3).text();
    	var i = 1;
    	$.ajax({
    		url:"../auditSupplieServlet",
            type:"post",
            cache:false,//取消缓存
            async:true,//是否异步请求,修改false就表示同步,true表示异步
            data:{"i":i,"suptId":suptId,"auditId":auditId,"account_id":account_id,"merId":merId},
            success:function(result){
                if(result == "0")
                {
                    tr.remove();
                }
                else
                {
                   alert("操作失败");
                }
            }
    	});
    }
    // 禁止上架
    function del(b){
    	if(confirm("是否禁止该商品上架?")){
    		var tr = $(b).parent().parent();
            var suptId = tr.find("td").eq(0).text();
            var auditId = tr.find("td").eq(1).text();
            var account_id = tr.find("td").eq(2).text();
            var merId = tr.find("td").eq(3).text();
            $.ajax({
                url:"../auditSupplieServlet",
                type:"post",
                cache:false,//取消缓存
                async:true,//是否异步请求,修改false就表示同步,true表示异步
                data:{"i":2,"suptId":suptId,"auditId":auditId,"account_id":account_id,"merId":merId},
                success:function(result){
                    if(result == "0")
                    {
                        tr.remove();
                    }
                    else
                    {
                       alert("操作失败");
                    }
                }
            });
    	}
    }
        
</script>
<body background="../images/tp.jpg">
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
</html>