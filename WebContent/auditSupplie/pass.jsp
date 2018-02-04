<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>允许上架</title>
    <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
</head>
<body>
<div class="layui-container" width="100%" height="100%" align="center">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8" style="width: 100%;">
            <div class="layui-form layui-form-pane">
                <c:forEach var="u" items="${list}">
                    <br/>
                    <input type="text" name="i" id="i" autocomplete="off"
                           class="layui-input" value="1" style="display:none;"/>
                    <input type="text" name="auditId" id="auditId" autocomplete="off"
                           class="layui-input" value="${u.auditId}" style="display:none;"/>
                    <input type="text" name="suptId" id="suptId" autocomplete="off"
                           class="layui-input" value="${u.suptId}" style="display:none;"/>
                    <input type="text" name="merId" id="merId" autocomplete="off"
                           class="layui-input" value="${u.merId}" style="display:none;"/>
                    <input type="text" name="account_id" id="account_id" autocomplete="off"
                           class="layui-input" value="${u.account_id}" style="display:none;"/>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">提醒内容</label>
                        <div class="layui-input-block">
                          <textarea name="remind" id="remind" placeholder="请输入内容" class="layui-textarea" lay-verify="required"></textarea>
                        </div>
                    </div>
                </c:forEach>
                <button id="btn" name="btn" class="layui-btn">发送</button>
            </div>
        </div>
    </div>
</div>
</body>
<script src="plugins/layui/layui.js"></script>
<script src="js/jquery-3.2.1.js"></script>
<script>
    $("#btn").click(function(){
    	var suptId = $("#suptId").val();
        var merId = $("#merId").val();
        var auditId = $("#auditId").val();
        var account_id = $("#account_id").val();
        var remind = $("#remind").val();
        var i = $("#i").val();
        layui.use(['layer', 'form', 'element'], function(){
            $.ajax({
                url:"auditSupplieServlet",
                type:"post",
                cache : false,
                async : true,
                data:{"i":i,"suptId":suptId,"merId":merId,"account_id":account_id,"auditId":auditId,"remind":remind},
                success:function(result){
                    if("0"==result){
                        layer.msg('发送成功',{icon: 1});
                        setTimeout('parent.location="auditSupplie/user.jsp";',1000);
                    }
                },error:function(XMLHttpRequest, textStatus, errorThrown)
                {
                    layer.msg('后台发生错误!',{icon: 2});
                }
            });
        });
    });
    layui.use([ 'layer', 'form' ], function() {});
</script>
</html>