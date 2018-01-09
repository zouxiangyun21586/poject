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
    	/* getAd(); */
    	layui.use([ 'layer', 'form' ], function() {
            var form = layui.form;
            $.ajax({
                type: "get",  // 请求方式(post或get)
                async:false,  //默认true(异步请求),设置为false(同步请求)
                url:"<%=request.getContextPath()%>/supSer?sup=1", // 发送请求的地址
                scriptCharset: 'utf-8',
                dataType:"json",
                data:{"state":0,"pageNow":getPar("pageNow"),"type":"list","select":$('#select').val()},
                success:function(res){
                    var pageContent = page(res.pageCount,res.pageNow,res.pageCode);
                    var list =  res.list;
                    var tobody_limt = "";
                    for(var i in list){
                        if("0"==list[i].auditStatus){ // 未提交
                            tobody_limt += "<tr id='zui'><td>"+list[i].id+"</td><td>"+list[i].commo+"</td><td>"+list[i].money+"</td><td>"
                               +list[i].number+"</td><td>"+list[i].typeName+"</td><td>"+list[i].qGp+"</td><td>"+list[i].brand+"</td><td>"
                               +list[i].storageMethod+"</td><td>"+list[i].origin+"</td><td>"+list[i].netContent+"</td><td>"
                               +list[i].packingMethod+"</td><td>"+list[i].upFrameTime
                               +"</td><td><button class='layui-btn layui-btn-xs' name='sj' onclick='sjia(this);'>上架商品</button><button class='layui-btn layui-btn-xs' name='xg' onclick='upd("+list[i].id+");'>修改商品</button></td></tr>";
                            }else if("2"==list[i].auditStatus){ // 已审核
                            tobody_limt += "<tr id='zui'><td>"+list[i].id+"</td><td>"+list[i].commo+"</td><td>"+list[i].money+"</td><td>"
                               +list[i].number+"</td><td>"+list[i].typeName+"</td><td>"+list[i].qGp+"</td><td>"+list[i].brand+"</td><td>"
                               +list[i].storageMethod+"</td><td>"+list[i].origin+"</td><td>"+list[i].netContent+"</td><td>"
                               +list[i].packingMethod+"</td><td>"+list[i].upFrameTime
                               +"</td><td><button class='layui-btn layui-btn-xs' name='xj' onclick='xiajia(this);'>下架商品</button></td></tr>";
                           }else if("1"==list[i].auditStatus){ // 未审核
                                tobody_limt += "<tr id='zui'><td>"+list[i].id+"</td><td>"+list[i].commo+"</td><td>"+list[i].money+"</td><td>"
                                +list[i].number+"</td><td>"+list[i].typeName+"</td><td>"+list[i].qGp+"</td><td>"+list[i].brand+"</td><td>"
                                +list[i].storageMethod+"</td><td>"+list[i].origin+"</td><td>"+list[i].netContent+"</td><td>"
                                +list[i].packingMethod+"</td><td>"+list[i].upFrameTime
                                +"</td><td><button class='layui-btn layui-btn-xs' name='cx' onclick='cancel(this);'>撤销商品</button></td></tr>";
                           }
                    }
                    $("#tobody_limt").append(tobody_limt);
               }
           });
       });
        $("#sel").click(function (){
        	getAd();
        });
    });
    /**
     * 添加保存
     */
    function getAd(){
    	layui.use([ 'layer', 'form' ], function() {
            var form = layui.form;
            $.ajax({
                type: "get",  // 请求方式(post或get)
                async:false,  //默认true(异步请求),设置为false(同步请求)
                url:"<%=request.getContextPath()%>/supSer?sup=1", // 发送请求的地址
                scriptCharset: 'utf-8',
                dataType:"json",
                data:{"state":0,"pageNow":getPar("pageNow"),"type":"list","select":$('#select').val()},
                success:function(res){
                    var pageContent = page(res.pageCount,res.pageNow,res.pageCode);
                    var list =  res.list;
                    var tobody_limt = "";
                    for(var i in list){
                        if("0"==list[i].auditStatus){ // 未提交
                            tobody_limt += "<tr id='zui'><td>"+list[i].id+"</td><td>"+list[i].commo+"</td><td>"+list[i].money+"</td><td>"
                               +list[i].number+"</td><td>"+list[i].typeName+"</td><td>"+list[i].qGp+"</td><td>"+list[i].brand+"</td><td>"
                               +list[i].storageMethod+"</td><td>"+list[i].origin+"</td><td>"+list[i].netContent+"</td><td>"
                               +list[i].packingMethod+"</td><td>"+list[i].upFrameTime
                               +"</td><td><button class='layui-btn layui-btn-xs' name='sj' onclick='sjia(this);'>上架商品</button><button class='layui-btn layui-btn-xs' name='xg' onclick='upd("+list[i].id+");'>修改商品</button></td></tr>";
                            }else if("2"==list[i].auditStatus){ // 已审核
                            tobody_limt += "<tr id='zui'><td>"+list[i].id+"</td><td>"+list[i].commo+"</td><td>"+list[i].money+"</td><td>"
                               +list[i].number+"</td><td>"+list[i].typeName+"</td><td>"+list[i].qGp+"</td><td>"+list[i].brand+"</td><td>"
                               +list[i].storageMethod+"</td><td>"+list[i].origin+"</td><td>"+list[i].netContent+"</td><td>"
                               +list[i].packingMethod+"</td><td>"+list[i].upFrameTime
                               +"</td><td><button class='layui-btn layui-btn-xs' name='xj' onclick='xiajia(this);'>下架商品</button></td></tr>";
                           }else if("1"==list[i].auditStatus){ // 未审核
                                tobody_limt += "<tr id='zui'><td>"+list[i].id+"</td><td>"+list[i].commo+"</td><td>"+list[i].money+"</td><td>"
                                +list[i].number+"</td><td>"+list[i].typeName+"</td><td>"+list[i].qGp+"</td><td>"+list[i].brand+"</td><td>"
                                +list[i].storageMethod+"</td><td>"+list[i].origin+"</td><td>"+list[i].netContent+"</td><td>"
                                +list[i].packingMethod+"</td><td>"+list[i].upFrameTime
                                +"</td><td><button class='layui-btn layui-btn-xs' name='cx' onclick='cancel(this);'>撤销商品</button></td></tr>";
                           }
                    }
                    $("#tobody_limt").append(tobody_limt);
               }
           });
       });
   };
    
   
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
           $.ajax({
               url:"<%=request.getContextPath()%>/supSer?sup=3",
               scriptCharset: 'utf-8',
               type:"get",
               cache:false,
               async:true,
               data:{"id":id,"state":0},
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
                   "<td><button class='layui-btn layui-btn-xs' name='sj' onclick='sjia(this);'>上架商品</button><button class='layui-btn layui-btn-xs' name='xg' onclick='upd("+list[i].id+");'>修改商品</button></td>");
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
                url:"<%=request.getContextPath()%>/supSer?sup=3",
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
                                "<td><button class='layui-btn layui-btn-xs' name='sj' onclick='sjia(this);'>上架商品</button><button class='layui-btn layui-btn-xs' name='xg' onclick='upd("+list[i].id+");'>修改商品</button></td>");
                    });
                },
                error:function(XMLHttpRequest, textStatus, errorThrown)
                {
                	alert("后台出现错误");
                }
            });
        }
    }
    
    
    /* 修改 */
    function updt(obj){
    	var tr = $(obj).parent().parent();
        var id = tr.find("td").eq(0).text();
        var commo = $("#mername").val();
        var money = $("#mermoney").val();
        var number = $("#mernumber").val();
        var merType = $("#mertype").val();
        var qGp = $("#merqGp  option:selected").val();
        var brand = $("#merbrand").val();
        var storageMethod = $("#merstm  option:selected").val();
        var origin = $("#merorigin").val();
        var netContent = $("#mernetc").val();
        var packingMethod = $("#merpack  option:selected").val();
        var upFrameTime = tr.find("td").eq(11).text();
        layui.use(['layer', 'form', 'element'], function(){
            $.ajax({
                type: "get",  // 请求方式(post或get)
                async:false,  //默认true(异步请求),设置为false(同步请求)
                url:"<%=request.getContextPath()%>/supSer?sup=4", // 发送请求的地址
                contentType: "application/text; charset=utf-8",
                dataType:"text",
                data:{"id":id,"commodity":commo,"storageMethod":storageMethod,"netContent":netContent,"packingMethod":packingMethod,"merType":merType,"storageMethod":storageMethod,"money":money,"origin":origin,"brand":brand,"qGp":qGp,"number":number,"upFrameTime":upFrameTime,"state":0},   // 传参数
                success:function(res){
                    layer.msg('修改成功!',{icon: 1});
                    setTimeout("parent.location.href=parent.location.href;","1000");
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    alert("修改失败");
                    alert(XMLHttpRequest.status);//200客户端请求已成功
                    alert(XMLHttpRequest.readyState);//4 响应内容解析完成，可以在客户端调用了
                    alert(textStatus);//parsererror
                }
            });
        });
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
                area: ['570px', '700px'],//窗口宽高
                content: ['jump.jsp','no']
            });
        });
    });
    
    function upd(obj){
       layer.open({
           anim : 2,
    	   title : '修改商品',
           type : 2,
           maxmin : false,
           resize : false,
           scrollbar : true,
           //页面路径
           content : '<%=request.getContextPath()%>/supSer?sup=4',
           area : [ '400px', '505px' ],//宽高
           shadeClose : true
       });
    }

</script>
<body background="../images/tp.jpg">
<br/>
    <div class="layui-container" width="100%" height="100%" align="center">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md8"  style="width: 100%;">
                <button class="layui-btn layui-btn-sm" style="float:left;" id="zui">
                    <i class="layui-icon">&#xe654;</i> 添加商品
                </button>
                <button class="layui-btn layui-btn-normal" style="float:right;margin-left:0px;" id="sel">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </button>
                <input type="text" class="layui-input" placeholder="请输入要查询的商品" style="float:right;width:250px;" id = "select" name="select"/>
                <div class='layui-form layui-form-pane' style="float:right;">
             <form>
        <table id="table" class="layui-table">  <!-- style="background-color:#ffffff6b;"  透明度 -->
	        <thead>
	            <tr>
	                <th>编号</th>
	                <th>名称</th>
	                <th>价格</th>
	                <th>数量</th>
	                <th>类型</th>
	                <th>保质期</th>
	                <th>品牌</th>
	                <th>储藏方法</th>
	                <th>出产地</th>
	                <th>净含量</th>
	                <th>包装</th>
	                <th>上架时间</th>
	                <th>操作</th>
	            </tr>
	        </thead>
	        <tbody id="tobody_limt">
            </tbody>
        </table>    
        <div id="page" class="page"></div>
    </form>
            </div>
        </div>
    </div>
</body>
</html>