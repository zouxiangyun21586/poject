<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="/projectYr/js/jquery-3.2.1.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>添加商品</title>
	<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
	
</head>
<script src="../plugins/layui/layui.js"></script>
<script src="../src/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript"> 
    /* 添加 */
    $("#sb").click(function(){
        var commo = $("#mername").val();
        var money = $("#mermoney").val();
        var number = $("#mernumber").val();
        var merType = $("#mertype").val();
        var qGp = $("#merqGp").val(); // 需获取下拉框值的value 传值过去
        var brand = $("#merbrand").val();
        var storageMethod = $("#merstm").val();  // 下拉框值的value
        var origin = $("#merorigin").val();
        var netContent = $("#mernetc").val();
        var packingMethod = $("#merpack").val();  // 下拉框值的value
        layui.use(['layer', 'form', 'element'], function(){
	        $.ajax({
	            type: "get",  // 请求方式(post或get)
	            async:false,  //默认true(异步请求),设置为false(同步请求)
	            url:"<%=request.getContextPath()%>/supSer?sup=2", // 发送请求的地址
	            dataType:"text",
	            data:{"commodity":commo,"netContent":netContent,"packingMethod":packingMethod,"merType":merType,"storageMethod":storageMethod,"money":money,"origin":origin,"brand":brand,"qGp":qGp,"number":number,"state":0},   // 传参数
	            success:function(res){
	            	layer.msg('保存成功!',{icon: 1});
	            	setTimeout("parent.location.href=parent.location.href;","1000");
	            },
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
                        <div class="layui-input-block">
                        </div>
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
                            <select value='sel' id="mertype" name="mertype" lay-filter="aihao" lay-search>
	                            <option value="">请选择商品类型</option>
	                            <option value='1'>汇吃美食</option>
	                            <option value='2'>手机数码</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品保质期</label>
                        <div class="layui-input-block" id="merbrand">
                            <select value='selMot' id="merqGp" name="merqGp" lay-filter="aihao" lay-search>
	                            <option value="">请选择商品保质期</option>
	                            <option value='1'>一个月</option>
	                            <option value='2'>两个月</option>
	                            <option value='3'>三个月</option>
	                            <option value='4'>四个月</option>
	                            <option value='5'>五个月</option>
	                            <option value='6'>六个月</option>
	                            <option value='7'>七个月</option>
	                            <option value='8'>八个月</option>
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
                             <select value='stoSel' id="merstm" name="merstm" lay-filter="aihao" lay-search>
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
                            <select value='pakSel' id="merpack" name="merpack" lay-filter="aihao" lay-search>
                                <option value="">请选择商品包装</option>
                                <option value='1'>盒装</option>
                                <option value='2'>箱装</option>
                                <option value='3'>袋装</option>
                                <option value='4'>罐藏</option>
                                <option value='5'>瓶装</option>
                             </select>
                        </div>
                    </div>
                    <button type="submit" class="layui-btn" id="sb">立即提交</button>
                    <button id="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>