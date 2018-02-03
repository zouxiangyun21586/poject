<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>商品详情</title>
    <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
</head>
<body>
<div class="layui-container" width="100%" height="100%" align="center">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8" style="width: 100%;">
            <c:forEach var="u" items="${list}">
                <br/>
                <div class="layui-form-item">
                    <label class="layui-form-label">账户名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="auditName" autocomplete="off"
                               class="layui-input" value="${u.auditName}" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">商品类型</label>
                    <div class="layui-input-block">
                        <input type="text" name="nameType" autocomplete="off"
                               class="layui-input" value="${u.nameType}" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">商品名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" autocomplete="off"
                               class="layui-input" value="${u.name}" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">商品价格</label>
                    <div class="layui-input-block">
                        <input type="text" name="money" autocomplete="off"
                               class="layui-input" value="${u.money}" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">商品数量</label>
                    <div class="layui-input-block">
                        <input type="text" name="number" autocomplete="off"
                               class="layui-input" value="${u.number}" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">商品描述</label>
                    <div class="layui-input-block">
                        <input type="text" name="desc" autocomplete="off"
                               class="layui-input" value="${u.describe}" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">商品产地</label>
                    <div class="layui-input-block">
                        <input type="text" name="origin" autocomplete="off"
                               class="layui-input" value="${u.origin}" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">净含量</label>
                    <div class="layui-input-block">
                        <input type="text" name="netContent" autocomplete="off"
                               class="layui-input" value="${u.netContent}" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">包装方式</label>
                    <div class="layui-input-block">
                        <input type="text" name="packingMethod" autocomplete="off"
                               class="layui-input" value="${u.packingMethod}" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">商品品牌</label>
                    <div class="layui-input-block">
                        <input type="text" name="brand" autocomplete="off"
                               class="layui-input" value="${u.brand}" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">保质期</label>
                    <div class="layui-input-block">
                        <input type="text" name="month" autocomplete="off"
                               class="layui-input" value="${u.month}" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">储藏方法</label>
                    <div class="layui-input-block">
                        <input type="text" name="storageMethod" autocomplete="off"
                               class="layui-input" value="${u.storageMethod}" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">申请时间</label>
                    <div class="layui-input-block">
                        <input type="text" name="addTime" autocomplete="off"
                               class="layui-input" value="${u.addTime}" readonly="readonly">
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
<script src="plugins/layui/layui.js"></script>
<script src="js/jquery-3.2.1.js"></script>
<script>
    layui.use([ 'layer', 'form' ], function() {});
</script>
</html>