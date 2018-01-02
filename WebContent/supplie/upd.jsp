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
	/* 修改保存 */
	$("#sb").click(function tjbc(){
	    var id = tr.find("td").eq(0).text();
	    var commo = $("#mername").val();
        var money = $("#mermoney").val();
        var number = $("#mernumber").val();
        var merType = $("#mertype").val();
        var qGp = $("#merqGp").val();
        var brand = $("#merbrand").val();
        var storageMethod = $("#merstm").val();
        var origin = $("#merorigin").val();
        var netContent = $("#mernetc").val();
        var packingMethod = $("#merpack").val();
	    var upFrameTime = tr.find("td").eq(11).text();
	    layui.use(['layer', 'form', 'element'], function(){
		    $.ajax({
		        type: "get",  // 请求方式(post或get)
		        async:false,  //默认true(异步请求),设置为false(同步请求)
		        url:"<%=request.getContextPath()%>/supSer?sup=4", // 发送请求的地址
		        dataType:"text",
		        data:{"id":id,"commodity":commo,"storageMethod":storageMethod,"netContent":netContent,"packingMethod":packingMethod,"merType":merType,"storageMethod":storageMethod,"money":money,"origin":origin,"brand":brand,"qGp":qGp,"number":number,"upFrameTime":upFrameTime,"state":0},   // 传参数
		        success:function(res){
		        	layer.msg('修改成功!',{icon: 1});
                    setTimeout("parent.location.href=parent.location.href;","1000");
		        },
		        error:function(XMLHttpRequest, textStatus, errorThrown){
		            alert("修改失败");
		        }
		    });
	    });
	});
	layui.use([ 'layer', 'form' ], function() {});
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
                    <button type="submit" class="layui-btn" id="sb">立即修改</button>
                    <button id="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>