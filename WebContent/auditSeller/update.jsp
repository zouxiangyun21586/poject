<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商品修改</title>
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
                           class="layui-input" value="4" style="display:none;"/>
                    <input type="text" name="auditID" id="auditID" autocomplete="off"
                           class="layui-input" value="${u.auditID}" style="display:none;"/>
                    <input type="text" name="id" id="id" autocomplete="off"
                           class="layui-input" value="${u.id}" style="display:none;"/>
                    <input type="text" name="wares_id" id="wares_id" autocomplete="off"
                           class="layui-input" value="${u.wares_id}" style="display:none;"/>
                    <input type="text" name="speciID" id="speciID" autocomplete="off"
                           class="layui-input" value="${u.speciID}" style="display:none;"/>
                    <div class="layui-form-item">
                        <label class="layui-form-label">账户名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="auditName" id="auditName" autocomplete="off"
                                   class="layui-input" value="${u.auditName}" readonly="readonly">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品类型</label>
                        <div class="layui-input-block">
                            <select name="nameTypeID" id="nameTypeID" lay-filter="nameType" lay-search>
                              <option value="${u.nameTypeID}">${u.nameType}</option>
                              <option value="1">汇吃美食</option>
                              <option value="2">手机数码</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="name" id="name" autocomplete="off"
                                   class="layui-input" value="${u.name}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品价格</label>
                        <div class="layui-input-block">
                            <input type="text" name="money" id="money" autocomplete="off"
                                   class="layui-input" value="${u.money}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品数量</label>
                        <div class="layui-input-block">
                            <input type="text" name="number" id="number" autocomplete="off"
                                   class="layui-input" value="${u.number}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品描述</label>
                        <div class="layui-input-block">
                            <input type="text" name="desc" id="desc" autocomplete="off"
                                   class="layui-input" value="${u.describe}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品产地</label>
                        <div class="layui-input-block">
                            <input type="text" name="origin" id="origin" autocomplete="off"
                                   class="layui-input" value="${u.origin}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">净含量</label>
                        <div class="layui-input-block">
                            <input type="text" name="netContent" id="netContent" autocomplete="off"
                                   class="layui-input" value="${u.netContent}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">包装方式</label>
                        <div class="layui-input-block">
                            <input type="text" name="packingMethod" id="packingMethod" autocomplete="off"
                                   class="layui-input" value="${u.packingMethod}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">品牌</label>
                        <div class="layui-input-block">
                            <input type="text" name="brand" id="brand" autocomplete="off"
                                   class="layui-input" value="${u.brand}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">保质期</label>
                        <div class="layui-input-block">
                            <select name="interest" id="qGP" lay-filter="month" lay-search>
                                <option value="${u.qGP}">${u.month}</option>
                                <option value="1">一个月</option>
                                <option value="2">两个月</option>
                                <option value="3">三个月</option>
                                <option value="4">四个月</option>
                                <option value="5">五个月</option>
                                <option value="6">六个月</option>
                                <option value="7">七个月</option>
                                <option value="8">八个月</option>
                                <option value="9">九个月</option>
                                <option value="10">十个月</option>
                                <option value="11">十一个月</option>
                                <option value="12">十二个月</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">储藏方法</label>
                        <div class="layui-input-block">
                            <input type="text" name="storageMethod" id="storageMethod" autocomplete="off"
                                   class="layui-input" value="${u.storageMethod}">
                        </div>
                    </div>
                </c:forEach>
                <button id="btn" name="btn" class="layui-btn">修改</button>
            </div>
        </div>
    </div>
</div>
</body>
<script src="plugins/layui/layui.js"></script>
<script src="js/jquery-3.2.1.js"></script>
<script>
//遮盖属性
function ityzl_SHOW_LOAD_LAYER(){  
    return layer.msg('努力中...', {icon: 16,shade: [0.8, '#f5f5f5'],scrollbar: false,offset: '0px', time:100000}) ;  
}
    $("#btn").click(function(){     
        var Id = $("#id").val();
        var Wares_id = $("#wares_id").val();
        var SpeciID = $("#speciID").val();
        var NameTypeID = $("#nameTypeID").val();
        var NameType = $("#nameType").val();
        var Name = $("#name").val();
        var Money = $("#money").val();
        var Number = $("#number").val();
        var Desc = $("#desc").val();
        var Origin = $("#origin").val();
        var NetContent = $("#netContent").val();
        var PackingMethod = $("#packingMethod").val();
        var Brand = $("#brand").val();
        var QGP = $("#qGP").val();
        var StorageMethod = $("#storageMethod").val();
        var i = $("#i").val();
        var t;
        layui.use(['layer', 'form', 'element'], function(){
            $.ajax({
                url:"auditSellerServlet",
                type:"get",
                cache : false,
                async : true,
                data:{"i":i,"id":Id,"wares_id":Wares_id,"speciID":SpeciID,"nameTypeID":NameTypeID,"name":Name,"money":Money,"number":Number,"desc":Desc,"origin":Origin,"netContent":NetContent,"packingMethod":PackingMethod,"brand":Brand,"qGP":QGP,"storageMethod":StorageMethod},
                beforeSend : function(){
                    t = ityzl_SHOW_LOAD_LAYER();  
                },
                success:function(result){
                    if("0"==result){
	                    layer.msg('修改成功',{icon: 1,shade: [0.3, '#f5f5f5']});
	                    setTimeout('parent.location="auditSeller/user.jsp";',1000);
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