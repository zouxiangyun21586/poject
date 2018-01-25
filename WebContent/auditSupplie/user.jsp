<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="/projectYr/js/jquery-3.2.1.js"></script>
	<script src="../plugins/layui/layui.js"></script>
	<script src="<%=request.getContextPath() %>/js/fenye.js"></script>
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
    
   
    //撤销
   function cancel(cencel){
       if(confirm("确定撤销商品?")){
           var tr = $(cencel).parent().parent();
           var id = tr.find("td").eq(0).text();
           var commo = tr.find("td").eq(1).text();
           var money = tr.find("td").eq(2).text();
           var number = tr.find("td").eq(3).text();
           var merType = tr.find("td").eq(4).text();
           var qGp = tr.find("td").eq(5).text();
           var brand = tr.find("td").eq(6).text();
           var storageMethod = tr.find("td").eq(7).text();
           var origin = tr.find("td").eq(8).text();
           var netContent = tr.find("td").eq(9).text();
           var packingMethod = tr.find("td").eq(10).text();
           var account_id = tr.find("td").eq(11).text();
           var release_supplierId = tr.find("td").eq(12).text();
           
           $.ajax({
               url:"<%=request.getContextPath()%>/supSer?sup=3",
               scriptCharset: 'utf-8',
               type:"get",
               cache:false,
               async:true,
               data:{"id":id,"state":0,"account_id":account_id,"release_supplierId":release_supplierId},
               success:function(result){
                   tr.html("<td style='display:none;'>"+id+"</td>"+
                   "<td>"+commo+"</td>"+
                   "<td>"+money+"</td>"+
                   "<td>"+number+"</td>"+
                   "<td>"+merType+"</td>"+
                   "<td>"+qGp+"</td>"+
                   "<td>"+brand+"</td>"+
                   "<td>"+storageMethod+"</td>"+
                   "<td>"+origin+"</td>"+
                   "<td>"+netContent+"</td>"+
                   "<td>"+packingMethod+"</td>"+
                   "<td style='display:none;'>"+account_id+"</td>"+
                   "<td style='display:none;'>"+mer_id+"</td>"+
                   "<td><button class='layui-btn layui-btn-xs' name='sj' onclick='sjia(this);'>上架商品</button><button class='layui-btn layui-btn-xs' id='upd' name='xg'  onclick='upd("+list[i].id+")'>修改商品</button></td>");
               },
               error:function(XMLHttpRequest, textStatus, errorThrown)
               {
                   alert("后台出现错误");
               }
           });
       }
   }
   
    //下架
    function xiajia(xj){
        if(confirm("确定下架商品?")){
            var tr = $(xj).parent().parent();
            var id = tr.find("td").eq(0).text();
            var commo = tr.find("td").eq(1).text();
            var money = tr.find("td").eq(2).text();
            var number = tr.find("td").eq(3).text();
            var merType = tr.find("td").eq(4).text();
            var qGp = tr.find("td").eq(5).text();
            var brand = tr.find("td").eq(6).text();
            var storageMethod = tr.find("td").eq(7).text();
            var origin = tr.find("td").eq(8).text();
            var netContent = tr.find("td").eq(9).text();
            var packingMethod = tr.find("td").eq(10).text();
            var upFrameTime = tr.find("td").qu(11).text(); // 需获取网络时间
            $.ajax({
                url:"<%=request.getContextPath()%>/supSer?sup=6",
                scriptCharset: 'utf-8',
                type:"get",
                cache:false,
                async:true,
                data:{"id":id,"state":0},
                dataType:"json",
                success:function(result){
                    $.each(result,function(index,item){
                        var Downtime = item.upFrameTime;
                        tr.html("<td style='display:none;'>"+id+"</td>"+
                                "<td>"+commo+"</td>"+
                                "<td>"+money+"</td>"+
                                "<td>"+number+"</td>"+
                                "<td>"+merType+"</td>"+
                                "<td>"+qGp+"</td>"+
                                "<td>"+brand+"</td>"+
                                "<td>"+storageMethod+"</td>"+
                                "<td>"+origin+"</td>"+
                                "<td>"+netContent+"</td>"+
                                "<td>"+packingMethod+"</td>"+
                                "<td>"+Downtime+"</td>"+
                                "<td><button class='layui-btn layui-btn-xs' name='sj' onclick='sjia(this);'>上架商品</button><button class='layui-btn layui-btn-xs' name='xg' id='upd' onclick='upd("+list[i].id+")'>修改商品</button></td>");
                    });
                },
                error:function(XMLHttpRequest, textStatus, errorThrown)
                {
                	alert("后台出现错误");
                }
            });
        }
    }
    
    layui.use([ 'layer', 'element' ], function() {
    	var laypage = layui.laypage,
        layer = layui.layer;
        $('#zui').click(function(){
            layer.open({
                anim: 2,
                title : '添加商品',
                type: 2, //窗口类型
                resize:false,//禁止拉伸
                maxmin:false,//最大化,最小化
                shade: [0.3,'#000'],
                area: ['570px', '750px'],//窗口宽高
                content: ['jump.jsp','no']
            });
        });
    });
    
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