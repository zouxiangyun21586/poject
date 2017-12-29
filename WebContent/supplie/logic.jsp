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
    	getAd();
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
	            url:"<%=request.getContextPath()%>/supSer?sup=5", // 发送请求的地址
	            dataType:"json",
	            data:{"state":0},
	            success:function(res){
	                for (var i = 0; i < res.length; i++) {
	                    var ym = res[i];
	                    $("#table").append("<tr id='zui'><td>"+ym.id+"</td><td>"+ym.commo+"</td><td>"+ym.money+"</td><td>"
	                    		+ym.number+"</td><td>"+ym.typeName+"</td><td>"+ym.qGp+"</td><td>"+ym.brand+"</td><td>"
	                    		+ym.storageMethod+"</td><td>"+ym.origin+"</td><td>"+ym.netContent+"</td><td>"
	                    		+ym.packingMethod+"</td><td>"+ym.upFrameTime
	                    		+"</td><td><button class='layui-btn layui-btn-xs' name='xj' onclick='xiajia(this);'>下架商品</button><button class='layui-btn layui-btn-xs' name='xg' onclick='upd(this);'>修改商品</button></td></tr>");
	                }
	                $.ajax({
	                    type: "get",  // 请求方式(post或get)
	                    async:false,  //默认true(异步请求),设置为false(同步请求)
	                    url:"<%=request.getContextPath()%>/supSer?sup=6", // 发送请求的地址
	                    dataType:"json",
	                    data:{"state":0,"pageNow":getPar("pageNow")},
	                    success:function(res){
	                        for (var i = 0; i < res.length; i++) {
	                            var ym = res[i];
	                            $("#table").append("<tr id='zui'>"+
	                            		"<td>"+ym.id+"</td>"+
	                            		"<td>"+ym.commo+"</td>"+
	                            		"<td>"+ym.money+"</td>"+
	                            		"<td>"+ym.number+"</td>"+
	                            		"<td>"+ym.typeName+"</td>"+
	                            		"<td>"+ym.qGp+"</td>"+
	                            		"<td>"+ym.brand+"</td>"+
	                            		"<td>"+ym.storageMethod+"</td>"+
	                            		"<td>"+ym.netContent+"</td>"+
	                            		"<td>"+ym.packingMethod+"</td>"+
	                            		"<td>"+ym.upFrameTime+"</td>"+
	                            		"<td><button class='layui-btn layui-btn-xs' name='xj' onclick='xiajia(this);'>下架商品</button><button class='layui-btn layui-btn-xs' name='xg' onclick='upd(this);'>修改商品</button></td>"+
	                            		"</tr>");
	                        }
	                    }
	                });
	            },
	            error:function(XMLHttpRequest, textStatus, errorThrown){
	                alert(XMLHttpRequest.status);  
	                alert(XMLHttpRequest.readyState);  
	                alert(textStatus); 
	                alert("查询后台出现错误");
	            }
	          });
    	});
    };
    
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
    
    /* 保存 */
    <%-- function baoCun(bc){
        /* var addTr = $(this).parent().parent().parent().find("td").eq(0).find("input").val();
        // 到达自己本身的父类的父类的父类，在自己本身的祖父中找到第一个td 找到input获取其中的值 */
        var tr = $(bc).parent().parent();
        var id = tr.find("td").eq(0).find("input").text();
        var commo = tr.find("td").eq(1).find("input").val();
        var money = tr.find("td").eq(2).find("input").val();
        var SupMerName = tr.find("td").eq(3).find("input").val();
        var number = tr.find("td").eq(4).find("input").val();
        var merType = tr.find("td").eq(5).find("input").val();
        var qGp = tr.find("td").eq(6).find("input").val();
        var brand = tr.find("td").eq(7).find("input").val();
        var NetContent = tr.find("td").eq(8).find("input").val();
        var origin = tr.find("td").eq(9).find("input").val();
        var packingMethod = tr.find("td").eq(10).find("input").val();
        var storageMethod = tr.find("td").eq(11).find("input").val();
        var upFrameTime = tr.find("td").eq(12).find("input").val();
        
        $.ajax({
            type: "get",  // 请求方式(post或get)
            async:false,  //默认true(异步请求),设置为false(同步请求)
            url:"<%=request.getContextPath()%>/supSer?sup=2", // 发送请求的地址
            dataType:"text",
            data:{"id":id,"commodity":commo,"merType":merType,"SupMerName":SupMerName,"money":money,"origin":origin,"brand":brand,"qGp":qGp,"storageMethod":storageMethod,"NetContent":NetContent,"packingMethod":packingMethod,"number":number,"upFrametTime":upFrameTime,"state":0},   // 传参数
            success:function(res){
                tr.html("<td>"+id+"</td><td>"+commo+"</td><td>"+merType+"</td><td>"+SupMerName+"</td><td>"+money+"</td><td>"+origin+"</td><td>"+brand+"</td><td>"+qGp+"</td><td>"+storageMethod+"</td><td>"+NetContent+"</td><td>"+packingMethod+"</td><td>"+number+"</td><td>"+upFrameTime+"</td><td><button name='xj' onclick='xiajia(this);' class='layui-btn layui-btn-xs'>下架商品</button><button class='layui-btn layui-btn-xs' type='button' name='xg' onclick='upd(this);' value='修改商品'></td>");
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                alert("保存失败(get)error");
            }
        });
    } --%>
    function upd(obj){
       layer.open({
           anim: 2,
           title : '修改账号',
           type: 2, //窗口类型
           resize:false,//禁止拉伸
           maxmin:false,//最大化,最小化
           shade: [0.3,'#000'],
           area: ['570px', '360px'],//窗口宽高
           content: ['upd.jsp','no']
       });
    }
    /* 取消 */
    function cancel(cc){ // 有多个值时会把前面全删掉再删除选择删除的那个值
        $(cc).parent().parent().remove(); // 删除tr
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
             <form>
        <table id="table" class="layui-table">  <!-- style="background-color:#ffffff6b;" -->
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
        </table>    
        <div id="page" class="page"></div>
    </form>
            </div>
        </div>
    </div>
</body>
</html>