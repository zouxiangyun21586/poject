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
<style>
    .layui-form-select dl{
        position:absolute;
        left:0px;
        top:42px;
        padding:5px 0px;
        min-width:100%;
        border:1px solid #d2d2d2;
        max-height:190px;
        overflow-y:auto;
        background-color:#fff;
        border-radius:2px;
        box-shadow:0 2px 4px rgba(0,0,0,.12);
        box-sizing:border-box;
    }
    </style>
<script type="text/javascript">

layui.use(['form','layer'], function() {
    var form = layui.form;
    $(document).ready(function(){
    	$.ajax({
            type: "get",  // 请求方式(post或get)
            async:false,  //默认true(异步请求),设置为false(同步请求)
            url:"<%=request.getContextPath()%>/supSer?sup=5", // 发送请求的地址
            dataType:"json",
            data:{"state":0},
            success:function(res){
                for (var i = 0; i < res.length; i++) {
                    var ym = res[i];
                    $("#table").append("<tr id='zui'><td>"+ym.id+"</td><td>"+ym.commo+"</td><td>"+ym.money+"</td><td>"+ym.netContent+"</td><td>"+ym.packingMethod+"</td><td>"+ym.qGp+"</td><td>"+ym.storageMethod+"</td><td>"+ym.upFrameTime+"</td><td><input type='button' name='xj' onclick='xiajia(this);' value='下架商品'><input type='button' name='xg' onclick='upd(this);' value='修改商品'></td></tr>");
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                alert(XMLHttpRequest.status);  
                alert(XMLHttpRequest.readyState);  
                alert(textStatus); 
                alert("查询后台出现错误");
            }
         });
    
    	var myDate = new Date();
        var mytime = myDate.toLocaleString();
        
        /* 添加   */
        $("#zui").click(function(){
        	$("tr:last").after("<td><input type='text' id='commo'></td>"+"<td><select id='sel'><option value='1'>汇吃美食</option><option value='2'>手机数码</option></select></td>"+"<td><input type='text' id='netContent' type='text'></td>"+"<td><input type='text' id='money'></td>"+"<td><input type='text' id='origin'></td>"+"<td><input type='text' id='brand'></td>"+"<td><select value='selMot'><option value='1'>一个月</option><option value='2'>两个月</option><option value='3'>三个月</option><option value='4'>四个月</option><option value='5'>五个月</option><option value='6'>六个月</option><option value='7'>七个月</option><option value='38'>八个月</option><option value='9'>九个月</option><option value='10'>十个月</option><option value='11'>十一个月</option><option value='12'>十二个月</option></select></td>"+"<td><input type='text' id='number'></td><td><input type='button' value='取消' onclick='cancel(this);'><input type='button' onclick='baoCun(this);' value='保存'></td>"+"</tr>")
        });
    });
    
}
    
    /* 保存 */
    function baoCun(bc){
        var tr = $(bc).parent().parent();
        var commo = tr.find("td").eq(0).find("input").val();
        var merType = tr.$("#sel").find("option:selected").val(); // 获取下拉框的值 
        var netContent = tr.find("td").eq(2).find("input").val();
        var money = tr.find("td").eq(3).find("input").val();
        var origin = tr.find("td").eq(4).find("input").val();
        var brand = tr.find("td").eq(5).find("input").val();
        var qGp = tr.$("#selMot").find("option:selected").val();
        var number = tr.find("td").eq(7).find("input").val();
        
        $.ajax({
            type: "get",  // 请求方式(post或get)
            async:false,  //默认true(异步请求),设置为false(同步请求)
            url:"<%=request.getContextPath()%>/supSer?sup=2", // 发送请求的地址
            dataType:"text",
            data:{"commodity":commo,"merType":merType,"netContent":netContent,"money":money,"origin":origin,"brand":brand,"qGp":qGp,"number":number,"state":0},   // 传参数
            success:function(res){
                tr.html("<td>"+id+"</td><td>"+commo+"</td><td>"+money+"</td><td>"+netContent+"</td><td>"+packingMethod+"</td><td>"+qGp+"</td><td>"+storageMethod+"</td><td>"+upFrameTime+"</td><td><input type='button' name='xj' onclick='xiajia(this);' value='下架商品'><input type='button' name='xg' onclick='upd(this);' value='修改商品'></td></td>");
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                alert("保存失败(get)error");
            }
        });
    }
    
    /* 取消 */
    function cancel(cc){ // 有多个值时会把前面全删掉再删除选择删除的那个值
        $(cc).parent().parent().remove(); // 删除tr
    }
    
    /* 删除值 (删除数据库中保存好的值) */
    function del(dlt){
        alert("删除");
        var a = $(dlt).parent().parent() // 自己本身的父类的父类 tr
        var va=a.find("td").eq(0).text(); // 获取到第一个td(id)的文本内容
        if(confirm('确定删除么')){
            $.ajax({
                url:"<%=request.getContextPath()%>/supSer?sup=3",
                type:"get",
                async:false,
                dataType:"text",
                data:{"supDel":va,"state":0}, // data是服务器返回的数据
                success:function(data){
                    $(dlt).parent().parent().find("td").remove();
                    /* 在tr下找到td然后将td删除*/
                }
            });
        }
    }
    
    
    /* 修改 */
    function upd(x){
        var tr = $(x).parent().parent();
        var id = tr.find("td").eq(0).text();
        var merType = tr.find("td").eq(1).text();
        var commo = tr.find("td").eq(2).text();
        var money = tr.find("td").eq(3).text();
        var origin = tr.find("td").eq(4).text();
        var brand = tr.find("td").eq(5).text();
        var qGp = tr.find("td").eq(6).text();
        var storageMethod = tr.find("td").eq(7).text();
        var number = tr.find("td").eq(8).text();
        var upFrameTime = tr.find("td").eq(9).text();

        tr.html("<td>"+id+"</td>"+"<td><input type='text' id='merType' value="+merType+"></td>"+"<td><input type='text' id='commo' value="+commo+"></td>"+"<td><input type='text' id='money' value="+money+"></td>"+"<td><input type='text' id='origin' value="+origin+"></td>"+"<td><input type='text' id='brand' value="+brand+"></td>"+"<td><input type='text' id='qGp' value="+qGp+"></td>"+
                "<td><input type='text' id='number' value="+number+"></td><td>"+upFrameTime+"</td><td><input type='button' value='取消' onclick=\"upXg(this,'"+id+"','"+merType+"','"+commo+"','"+money+"','"+origin+"','"+brand+"','"+qGp+"','"+number+"','"+upFrameTime+"');\"><input type='button' onclick='tjbc(this);' value='保存'></td>");
    }
    
    /* 修改取消 */
    function upXg(x,a,b,c,d,e,f,g,h,i){
        alert("<td>"+a+"</td><td>"+b+"</td><td>"+c+"</td><td>"+d+"</td><td>"+e+"</td><td>"+f+"</td><td>"+g+"</td><td>"+h+"</td><td>"+i+"</td><td><input type='button' name='sc' onclick='del(this);' value='删除'><input type='button' name='xg' onclick='upd(this);' value='修改'></td>");
        var tr = $(x).parent().parent();
        tr.html("<td>"+a+"</td>"+"<td>"+b+"</td><td>"+c+"</td><td>"+d+"</td><td>"+e+"</td><td>"+f+"</td><td>"+g+"</td><td>"+h+"</td><td>"+i+"</td><td><input type='button' name='sc' onclick='del(this);' value='删除'><input type='button' name='xg' onclick='upd(this);' value='修改'></td>");
    }
    
    /* 修改保存 */
    function tjbc(bc){
        /* var addTr = $(this).parent().parent().parent().find("td").eq(0).find("input").val();
        // 到达自己本身的父类的父类的父类，在自己本身的祖父中找到第一个td 找到input获取其中的值 */
        var tr = $(bc).parent().parent();
        var id = tr.find("td").eq(0).text();
        var merType = tr.find("td").eq(1).find("input").val();
        var commo = tr.find("td").eq(2).find("input").val();
        var money = tr.find("td").eq(3).find("input").val();
        var origin = tr.find("td").eq(4).find("input").val();
        var brand = tr.find("td").eq(5).find("input").val();
        var qGp = tr.find("td").eq(6).find("input").val();
        var storageMethod = tr.find("td").eq(7).find("input").val();
        var number = tr.find("td").eq(8).find("input").val();
        var upFrameTime = tr.find("td").eq(9).text();;

        $.ajax({
            type: "get",  // 请求方式(post或get)
            async:false,  //默认true(异步请求),设置为false(同步请求)
            url:"<%=request.getContextPath()%>/supSer?sup=4", // 发送请求的地址
            dataType:"text",
            data:{"id":id,"commodity":commo,"merType":merType,"money":money,"origin":origin,"brand":brand,"qGp":qGp,"number":number,"upFrametTime":upFrameTime,"state":0},   // 传参数
            success:function(res){
                tr.html("<td>"+id+"</td><td>"+merType+"</td><td>"+commo+"</td><td>"+money+"</td><td>"+origin+"</td><td>"+brand+"</td><td>"+qGp+"</td><td>"+number+"</td><td>"+upFrameTime+"</td><td><input type='button' name='sc' onclick='del(this);' value='删除'><input type='button' name='xg' onclick='upd(this);' value='修改'></td>");
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
                        <label class="layui-form-label">添加商品信息</label>
                        <div class="layui-input-block">
                            <select id="addmer" name="addmer" lay-filter="aihao" lay-search>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品类型</label>
                        <div class="layui-input-block">
                            <input type="text" id="mertype" name="mertype" autocomplete="off"
                                placeholder="请输入商品类型" class="layui-input">
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
                        <label class="layui-form-label">商品产地</label>
                        <div class="layui-input-block">
                            <input type="text" id="merorigin" name="merorigin" autocomplete="off"
                                placeholder="请输入商品产地" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品品牌</label>
                        <div class="layui-input-block">
                            <input type="text" id="merbrand" name="merbrand" autocomplete="off"
                                placeholder="商品品牌" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品保质期</label>
                        <div class="layui-input-block">
                            <input type="text" id="merqGp" name="merqGp" autocomplete="off"
                                placeholder="商品保质期" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品数量</label>
                        <div class="layui-input-block">
                            <input type="text" id="mernumber" name="mernumber" autocomplete="off"
                                placeholder="请输入商品数量" class="layui-input">
                        </div>
                    </div>
                    <a href="logic.jsp"><button type="submit" class="layui-btn" id="sb">立即提交</button></a>
                    <button id="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </div>
    </div>
</body>

</html>