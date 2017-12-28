<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加商品</title>
<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
</head>
<body>
    <div class="layui-container" width="100%" height="100%" align="center">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md8" style="width: 100%;">
                <div class="layui-form layui-form-pane">    
                    <input type="text" name="i" autocomplete="off" class="layui-input" style="display:none;" value="6">
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品类型</label>
                        <div class="layui-input-block">
                          <select name="interest" id="interest" lay-filter="aihao" lay-search>
                            <option value=""></option>
                            <option value="1">汇吃美食</option>
                            <option value="2">手机数码</option>
                          </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="name" id="name" autocomplete="off"
                                placeholder="请输入商品名称" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品价格</label>
                        <div class="layui-input-block">
                            <input type="text" name="money" id="money" autocomplete="off"
                                placeholder="请输入商品价格" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品描述</label>
                        <div class="layui-input-block">
                            <input type="text" name="desc" id="desc" autocomplete="off"
                                placeholder="请输入商品描述" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品产地</label>
                        <div class="layui-input-block">
                            <input type="text" name="origin" id="origin" autocomplete="off"
                                placeholder="请输入商品产地" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">净含量</label>
                        <div class="layui-input-block">
                            <input type="text" name="netContent" id="netContent" autocomplete="off"
                                placeholder="请输入商品净含量" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">包装方式</label>
                        <div class="layui-input-block">
                            <input type="text" name="packingMethod" id="packingMethod" autocomplete="off"
                                placeholder="请输入商品包装方式" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">品牌</label>
                        <div class="layui-input-block">
                            <input type="text" name="brand" id="brand" autocomplete="off"
                                placeholder="请输入商品品牌" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">保质期</label>
                        <div class="layui-input-block">
                        <select name="interest" id="qGP" lay-filter="month" lay-search>
                              <option value=""></option>
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
                                placeholder="请输入商品储藏方法" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品数量</label>
                        <div class="layui-input-block">
                            <input type="text" name="number" id="number" autocomplete="off"
                                placeholder="请输入商品数量" class="layui-input">
                        </div>
                    </div>
                    <button id="btn" name="btn" class="layui-btn">提交</button>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="../plugins/layui/layui.js"></script>
<script src="../src/js/jquery-3.2.1.js"></script>
<script>
$("#btn").click(function(){
    var NameType = $("#interest").val();
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
    var i = 6;
    layui.use(['layer', 'form', 'element'], function(){
    $.ajax({
        url:"../sellerServlet",
        type:"get",
        cache : false,
        async : true,
        data:{"i":i,"interest":NameType,"name":Name,"money":Money,"number":Number,"desc":Desc,"origin":Origin,"netContent":NetContent,"packingMethod":PackingMethod,"brand":Brand,"qGP":QGP,"storageMethod":StorageMethod},
        success:function(result){
            layer.msg('保存成功!',{icon: 1});
            setTimeout("parent.location.href=parent.location.href;","1000");
        }
        });
    });
});
layui.use([ 'layer', 'form' ], function() {});
</script>
</html>