<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/projectYr/js/jquery-3.2.1.js"></script>
    <script src="./plugins/layui/layui.js"></script>
    <link rel="stylesheet" href="./plugins/layui/css/layui.css" media="all" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>供应商修改</title>
</head>
<body>
    <br />
    <div class="layui-container" width="100%" height="100%" align="center">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md8" style="width: 100%;">
                <div class="layui-form layui-form-pane">
                    <c:forEach var="su" items="${list}">
	                    <div class="layui-form-item">
                            <label class="layui-form-label">商品信息</label>
                            <div class="layui-input-block"></div>
	                    </div>
	                    
	                    <input type="text" name="i" id="i" autocomplete="off"
                                class="layui-input" value="5" style="display:none;"/>
                        <input type="text" name="suptId" id="suptId" autocomplete="off"
                                class="layui-input" value="${su.suptId}" style="display:none;"/>
                        <input type="text" name="account_id" id="account_id" autocomplete="off"
                                class="layui-input" value="${su.account_id}" style="display:none;"/>
                        <input type="text" name="merId" id="merId" autocomplete="off"
                                class="layui-input" value="${su.merId}" style="display:none;"/>
                        <input type="text" name="account" id="account" autocomplete="off"
                                class="layui-input" value="${su.account}" style="display:none;"/>
	                    <div class="layui-form-item">
	                        <label class="layui-form-label">商品价格</label>
	                        <div class="layui-input-block">
	                            <input type="text" id="mermoney" name="mermoney" autocomplete="off"
	                                value="${su.money}" class="layui-input">
	                        </div>
	                    </div>
	                    <div class="layui-form-item">
	                        <label class="layui-form-label">商品数量</label>
	                        <div class="layui-input-block">
	                            <input type="text" id="mernumber" name="mernumber" autocomplete="off"
	                                value="${su.number}" class="layui-input">
	                        </div>
	                    </div>
	                    <div class="layui-form-item">
	                        <label class="layui-form-label">商品保质期</label>
	                        <div class="layui-input-block" id="merqGp">
	                            <select value='selMot' id="merqGp" name="merqGp" lay-search>
	                                <option value="">请选择商品保质期</option>
	                                <option value="${su.month}"></option>
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
	                    <div class="layui-form-item" >
	                        <label class="layui-form-label">商品净含量</label>
	                        <div class="layui-input-block">
	                            <input type="text" id="mernetc" name="mernetc" autocomplete="off"
	                                value="${su.netContent}" class="layui-input">
	                        </div>
	                    </div>
	                    <div class="layui-form-item">
	                        <label class="layui-form-label">商品包装</label>
	                        <div class="layui-input-block" id="merpack">
	                            <select value='pakSel' id="merpack" name="merpack" lay-search>
	                                <option value="">请选择商品包装</option>
	                                <option value="${su.packingMethod}"></option>
	                                <option value='1'>盒装</option>
	                                <option value='2'>箱装</option>
	                                <option value='3'>袋装</option>
	                                <option value='4'>罐藏</option>
	                                <option value='5'>瓶装</option>
	                             </select>
	                        </div>
	                    </div>
	                    <div class="layui-form-item">
                            <label class="layui-form-label">商品描述</label>
                            <div class="layui-input-block">
                                <input type="text" id="merDes" name="merDes" autocomplete="off"
                                    value="${su.describe}" class="layui-input">
                            </div>
                        </div>
                        <input type="text" name="aduId" id="aduId" autocomplete="off"
                                class="layui-input" value="${su.auditStatus}" style="display:none;"/>
                    </c:forEach>
                    <button type="submit" class="layui-btn" id="sb">立即修改</button>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
    /* 修改保存 */
    $("#sb").click(function tjbc(obj){
        var speId = $("#suptId").val();
        var merId = $("#merId").val();
        var money = $("#mermoney").val();
        var number = $("#mernumber").val();
        var qGp = $("#merqGp option:selected").val();
        var netContent = $("#mernetc").val();
        var packingMethod = $("#merpack option:selected").val();
        var merDes = $("#merDes").val();
        layui.use(['layer', 'form', 'element'], function(){
            $.ajax({
                type: "get",  // 请求方式(post或get)
                async:false,  //默认true(异步请求),设置为false(同步请求)
                url:"<%=request.getContextPath()%>/supSer?sup=4", // 发送请求的地址
                contentType: "application/text; charset=utf-8",
                dataType:"text",
                data:{"netContent":netContent,"speId":speId,"merId":merId,"packingMethod":packingMethod,"money":money,"qGp":qGp,"number":number,"describe":merDes,"state":0},   // 传参数
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
    });
    layui.use([ 'layer', 'form' ], function() {});
</script>
</html>