<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="/projectYr/js/jquery-3.2.1.js"></script>
	<script src="../plugins/layui/layui.js"></script>
	<script src="../js/fenye.js"></script>
	<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
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
                             "<td align='center' style='display:none;' id='id"+list[i].auditId+"'>"+list[i].auditID+"</td>"+
                             "<td align='center' style='display:none;'>"+list[i].account_id+"</td>"+
                             "<td align='center' style='display:none;'>"+list[i].merId+"</td>"+
                             "<td align='center'>"+list[i].account+"</td>"+
                             "<td align='center'>"+list[i].typeName+"</td>"+
                             "<td align='center'><a href='#' onclick='query("+list[i].auditId+")'>"+list[i].commo+"</a></td>"+
                             "<td align='center'>"+list[i].auditStatus+"</td>"+
                             "<td align='center'>"+list[i].auditTime+"</td>"+
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
            content : '../auditSupplieServlet?i=1&&o=1&&auditID='+d,
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
            content : '../auditSellerServlet?i=1&&o=2&&auditID='+obj,
            area : [ '400px', '505px' ],//宽高
            shadeClose : true
        });
    }
    
    function upd(uu){
    	layer.open({
            title : '修改商品',
            type : 2,
            anim : 2,
            maxmin : false,
            resize : false,
            scrollbar : true,
            //页面路径
            content : '../supSer?sup=5&&state=0&&id='+uu,
            area : [ '500px', '490px' ],//宽高
            shadeClose : true
        });
    };
        
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
                <input type="text" class="layui-input" placeholder="请输入要查询的商品名称"
                    style="float: right; width: 250px; height: 30px;" id="select" name="select"/>
                <table class="layui-table">
                    <thead>
                        <tr>
                            <td style="display: none;">保存商品ID</td>
                            <td style="display: none;">表ID</td>
                            <td style="display: none;">账户ID</td>
                            <td style="display: none;">商品ID</td>
                            <td align="center" style="width:150px;"> 用户名称</td>
                            <td align="center" style="width:150px;"> 商品类型</td>
                            <td align="center" style="width:150px;"> 商品名称</td>
                            <td align="center" style="width:150px;"> 审核状态</td>
                            <td align="center" style="width:280px;"> 添加时间</td>
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