<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/projectYr/js/jquery-3.2.1.js"></script>
    <script src="../plugins/layui/layui.js"></script>
    <link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
/* 修改 */    //  OK ----- 
	/* function upd(x){
	    var tr = $(x).parent().parent();
	    var id = tr.find("td").eq(0).text();
	    var commo = tr.find("td").eq(1).text();
	    var money = tr.find("td").eq(2).text();
	    var number = tr.find("td").eq(3).text();
	    var merType = tr.find("td").eq(4).text();
	    var qGp = tr.find("td").eq(5).text();
	    var brand = tr.find("td").eq(6).text();
	    var NetContent = tr.find("td").eq(7).text();
	    var origin = tr.find("td").eq(8).text();
	    var packingMethod = tr.find("td").eq(9).text();
	    var storageMethod = tr.find("td").eq(10).text();
	    var upFrameTime = tr.find("td").eq(11).text();
	    
	    tr.html("<td>"+id+"</td>"+
	            "<td><input type='text' id='commo' value="+commo+"></td>"+
	            "<td><input type='text' id='money' value="+money+"></td>"+
	            "<td><input type='text' id='number' value="+number+"></td>"+
	            "<td><select value='sel'><option value='1'>汇吃美食</option><option value='2'>手机数码</option></select></td>"+
	            "<td><select value='selMot'><option value='1'>一个月</option><option value='2'>两个月</option><option value='3'>三个月</option><option value='4'>四个月</option><option value='5'>五个月</option><option value='6'>六个月</option><option value='7'>七个月</option><option value='38'>八个月</option><option value='9'>九个月</option><option value='10'>十个月</option><option value='11'>十一个月</option><option value='12'>十二个月</option></select></td>"+
	            "<td><input type='text' id='brand' value="+brand+"></td>"+
	            "<td><select id='stoSel'><option value='1'>干燥阴凉环境</option><option value='2'>密封环境</option><option value='3'>通风环境</option><option value='4'>高温环境</option><option value='5'>低温环境</option></select></td>"+
	            "<td><input type='text' id='origin' value="+origin+"></td>"+
	            "<td><input type='text' id='origin' value="+packingMethod+"></td>"+
	            "<td><select id='pakSel'><option value='1'>盒装</option><option value='2'>箱装</option><option value='3'>袋装</option><option value='4'>罐藏</option><option value='5'>瓶装</option></select></td>"+
	            "<td>"+upFrameTime+"</td>"+
	            "<td><button class='layui-btn layui-btn-xs' onclick=\"upCencel(this,'"+id+"','"+commo+"','"+money+"','"+number+"','"+merType+"','"+qGp+"','"+brand+"','"+NetContent+"','"+origin+"','"+packingMethod+"','"+storageMethod+"','"+upFrameTime+"');\">取消</button><button class='layui-btn layui-btn-xs' onclick='tjbc(this);'>保存</button></td>");
	} */
	
	/* 修改保存 */
	function tjbc(bc){
	    /* var addTr = $(this).parent().parent().parent().find("td").eq(0).find("input").val();
	    // 到达自己本身的父类的父类的父类，在自己本身的祖父中找到第一个td 找到input获取其中的值 */
	    var tr = $(bc).parent().parent();
	    var id = tr.find("td").eq(0).text();
	    var commo = tr.find("td").eq(1).find("input").val();
        var money = tr.find("td").eq(2).find("input").val();
        var number = tr.find("td").eq(3).find("input").val();
        var merType = tr.$("#sel").find("option:selected").val(); // 获取下拉框的值 
        var qGp = tr.$("#selMot").find("option:selected").val();
        var brand = tr.find("td").eq(6).find("input").val();
        var netContent = tr.find("td").eq(7).find("input").val();
        var origin = tr.find("td").eq(8).find("input").val();
        var packingMethod = tr.find("td").eq(9).find("input").val();
        var storageMethod = tr.find("td").eq(10).find("input").val();
	    var upFrameTime = tr.find("td").eq(11).text();
	    
	    var panDuan = true;
	    $.ajax({
	        type: "get",  // 请求方式(post或get)
	        async:false,  //默认true(异步请求),设置为false(同步请求)
	        url:"<%=request.getContextPath()%>/supSer?sup=4", // 发送请求的地址
	        dataType:"text",
	        data:{"id":id,"commodity":commo,"merType":merType,"money":money,"origin":origin,"brand":brand,"qGp":qGp,"storageMethod":storageMethod,"number":number,"upFrametTime":upFrameTime,"state":0},   // 传参数
	        success:function(res){
                 tr.html("<td>"+id+"</td><td>"+merType+"</td><td>"+commo+"</td><td>"+money+"</td><td>"+origin+"</td><td>"+brand+"</td><td>"+qGp+"</td><td>"+storageMethod+"</td><td>"+number+"</td><td>"+upFrameTime+"</td><td><button class='layui-btn layui-btn-xs' name='xj' onclick='xiajia(this);'>下架商品</button><button class='layui-btn layui-btn-xs' name='xg' onclick='upd(this);'>修改商品</button></td>");
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown){
	            alert("修改失败(get)error");
	        }
	    });
	}
</script>
<body>
    <br />
    <div class="layui-container" width="100%" height="100%" align="center">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md8" style="width: 100%;">
                <div class="layui-form layui-form-pane">
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品信息</label>
                        <div class="layui-input-block"></div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品名称</label>
                        <div class="layui-input-block">
                            <input type="text" id="mername" name="mername" autocomplete="off"
                                placeholder="请输入商品名" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品价格</label>
                        <div class="layui-input-block">
                            <input type="text" id="mermoney" name="mermoney" autocomplete="off"
                                placeholder="请输入商品价格" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品数量</label>
                        <div class="layui-input-block">
                            <input type="text" id="mernumber" name="mernumber" autocomplete="off"
                                placeholder="请输入商品数量" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品类型</label>
                        <div class="layui-input-block" id="mertype">
                            <select value='sel' id="interest" name="interest" lay-filter="aihao" lay-search>
                                <option value="">请选择商品类型</option>
                                <option value='1'>汇吃美食</option>
                                <option value='2'>手机数码</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品保质期</label>
                        <div class="layui-input-block" id="merbrand">
                            <select value='selMot' id="interest" name="interest" lay-filter="aihao" lay-search>
                                <option value="">请选择商品保质期</option>
                                <option value='1'>一个月</option>
                                <option value='2'>两个月</option>
                                <option value='3'>三个月</option>
                                <option value='4'>四个月</option>
                                <option value='5'>五个月</option>
                                <option value='6'>六个月</option>
                                <option value='7'>七个月</option>
                                <option value='38'>八个月</option>
                                <option value='9'>九个月</option>
                                <option value='10'>十个月</option>
                                <option value='11'>十一个月</option>
                                <option value='12'>十二个月</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品品牌</label>
                        <div class="layui-input-block">
                            <input type="text" id="merbrand" name="merbrand" autocomplete="off"
                                placeholder="请输入商品品牌" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">储藏方法</label>
                        <div class="layui-input-block" id="merstm">
                            <!-- <input type="text" id="merstm" name="merstm" autocomplete="off"
                                placeholder="请输入商品储藏方法" class="layui-input"> -->
                             <select value='stoSel' id="interest" name="interest" lay-filter="aihao" lay-search>
                                <option value="">请选择商品储藏方法</option>
                                <option value='1'>干燥阴凉环境</option>
                                <option value='2'>密封环境</option>
                                <option value='3'>通风环境</option>
                                <option value='4'>高温环境</option>
                                <option value='5'>低温环境</option>
                             </select>
                        </div>
                    </div>
                    
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品产地</label>
                        <div class="layui-input-block">
                            <input type="text" id="merorigin" name="merorigin" autocomplete="off"
                                placeholder="请输入商品产地" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品净含量</label>
                        <div class="layui-input-block">
                            <input type="text" id="mernetc" name="mernetc" autocomplete="off"
                                placeholder="请输入商品净含量" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品包装</label>
                        <div class="layui-input-block" id="merpack">
                            <select value='pakSel' id="interest" name="interest" lay-filter="aihao" lay-search>
                                <option value="">请选择商品包装</option>
                                <option value='1'>盒装</option>
                                <option value='2'>箱装</option>
                                <option value='3'>袋装</option>
                                <option value='4'>罐藏</option>
                                <option value='5'>瓶装</option>
                             </select>
                        </div>
                    </div>
                    <a href="logic.jsp"><button type="submit" class="layui-btn" id="sb">立即修改</button></a>
                    <button id="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>