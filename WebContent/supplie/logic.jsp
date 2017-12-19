<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="/projectYr/js/jquery-3.2.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
/* 页面加载完出现 */
    $(document).ready(function(){
        $.ajax({
            type: "get",  // 请求方式(post或get)
            async:false,  //默认true(异步请求),设置为false(同步请求)
            url:"<%=request.getContextPath()%>/supSer?sup=5", // 发送请求的地址
            dataType:"json",
            data:{"state":0},
            success:function(res){
                for (var i = 0; i < res.length; i++) {
                    var js = res[i];
                    $("#table").append("<tr id='jia'><td>"+js.id+"</td><td>"+js.merType+"</td><td>"+js.commo+"</td><td>"+js.money+"</td><td>"+js.describe+"</td><td>"+js.origin+"</td><td>"+js.netContent+"</td><td>"+js.packingMethod+"</td><td>"+js.brand+"</td><td>"+js.qGp+"</td><td>"+js.storageMethod+"</td><td>"+js.number+"</td><td>"+js.upFrameTime+"</td><td>"+js.merId+"</td><td>"+js.specificationID+"</td><td>"+js.suptID+"</td><td><input type='button' name='sc' onclick='del(this);' value='删除'><input type='button' name='xg' onclick='upd(this);' value='修改'></td></tr>");
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                alert(XMLHttpRequest.status);  
                alert(XMLHttpRequest.readyState);  
                alert(textStatus); 
                alert("查询后台出现错误");
            }
          });
        
        
        /* 添加 */
    $("#zui").click(function(){
        $("tr:last").after("<tr id='jia'>"+"<td><input type='text' id='id'></td>"+"<td><input type='text' id='merType'></td>"+"<td><input type='text' id='commo'></td>"+"<td><input type='text' id='money'></td>"+"<td><input type='text' id='describe'></td>"+"<td><input type='text' id='origin'></td>"+"<td><input type='text' id='netContent'></td>"+"<td><input type='text' id='packingMethod'></td>"+"<td><input type='text' id='brand'></td>"+"<td><input type='text' id='qGp'></td>"+"<td><input type='text' id='storageMethod'></td>"+"<td><input type='text' id='number'></td>"+
        "<td><input type='text' id='upFrameTime'></td>"+"<td><input type='text' id='merId'></td>"+"<td><input type='text' id='specificationID'></td>"+"<td><input type='text' id='suptID'></td>"+"<td><input type='button' value='取消' onclick='cancel(this);'><input type='button' onclick='baoCun(this);' value='保存'></td>"+"</tr>");
    });
    });
    
    /* 保存 */
    function baoCun(bc){
        /* var addTr = $(this).parent().parent().parent().find("td").eq(0).find("input").val();
        // 到达自己本身的父类的父类的父类，在自己本身的祖父中找到第一个td 找到input获取其中的值 */
        var tr = $(bc).parent().parent();
        var id = tr.find("td").eq(0).find("input").val();
        var merType = tr.find("td").eq(1).find("input").val();
        var commo = tr.find("td").eq(2).find("input").val();
        var money = tr.find("td").eq(3).find("input").val();
        var describe = tr.find("td").eq(4).find("input").val();
        var origin = tr.find("td").eq(5).find("input").val();
        var netContent = tr.find("td").eq(6).find("input").val();
        var packingMethod = tr.find("td").eq(7).find("input").val();
        var brand = tr.find("td").eq(8).find("input").val();
        var qGp = tr.find("td").eq(9).find("input").val();
        var storageMethod = tr.find("td").eq(10).find("input").val();
        var number = tr.find("td").eq(11).find("input").val();
        var upFrameTime = tr.find("td").eq(12).find("input").val();
        var merId = tr.find("td").eq(13).find("input").val();
        var specificationID = tr.find("td").eq(14).find("input").val();
        var suptID = tr.find("td").eq(15).find("input").val();
        /* var panDuan = true; */
        
        $.ajax({
            type: "get",  // 请求方式(post或get)
            async:false,  //默认true(异步请求),设置为false(同步请求)
            url:"<%=request.getContextPath()%>/supSer?sup=2", // 发送请求的地址
            dataType:"json",
            data:{"id":id,"commodity":commo,"merType":merType,"money":money,"describe":describe,"origin":origin,"netContent":netContent,"packingMethod":packingMethod,"brand":brand,"qGp":qGp,"storageMethod":storageMethod,"number":number,"upFrametTime":upFrameTime,"merId":merId,"specificationID":specificationID,"suptID":suptID,"state":0},   // 传参数
            success:function(res){
                 alert(33);
                 if(res == "1")
                 {
                     alert("保存失败");
                 }else{
                    tr.html("<td>"+id+"</td><td>"+merType+"</td><td>"+commo+"</td><td>"+money+"</td><td>"+describe+"</td><td>"+origin+"</td><td>"+netContent+"</td><td>"+packingMethod+"</td><td>"+brand+"</td><td>"+qGp+"</td><td>"+storageMethod+"</td><td>"+number+"</td><td>"+upFrameTime+"</td><td>"+merId+"</td><td>"+specificationID+"</td><td>"+suptID+"</td><td><input type='button' name='sc' onclick='del(this);' value='删除'><input type='button' name='xg' onclick='upd(this);' value='修改'></td>");
                 }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                alert("保存失败(post)error");
            }
        });
        
        
        /* 判断ID是否重复 */
        /* if(panDuan){
            pd();
        } */
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
                dataType:"json",
                data:{"supDel":va,"state":0}, // data是服务器返回的数据
                success:function(data){
                    alert("删除前");
                    $(dlt).parent().parent().find("td").remove();
                    alert("删除后");
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
        var describe = tr.find("td").eq(4).text();
        var origin = tr.find("td").eq(5).text();
        var netContent = tr.find("td").eq(6).text();
        var packingMethod = tr.find("td").eq(7).text();
        var brand = tr.find("td").eq(8).text();
        var qGp = tr.find("td").eq(9).text();
        var storageMethod = tr.find("td").eq(10).text();
        var number = tr.find("td").eq(11).text();
        var upFrameTime = tr.find("td").eq(12).text();
        var merId = tr.find("td").eq(13).find("input").val();
        var specificationID = tr.find("td").eq(14).find("input").val();
        var suptID = tr.find("td").eq(15).find("input").val();
        
        tr.html("<td>"+id+"</td>"+"<td><input type='text' id='merType' value="+merType+"></td>"+"<td><input type='text' id='commo' value="+commo+"></td>"+"<td><input type='text' id='money' value="+money+"></td>"+"<td><input type='text' id='describe' value="+describe+"></td>"+"<td><input type='text' id='origin' value="+origin+"></td>"+"<td><input type='text' id='netContent' value="+netContent+"></td>"+"<td><input type='text' id='packingMethod' value="+packingMethod+"></td>"+"<td><input type='text' id='brand' value="+brand+"></td>"+"<td><input type='text' id='qGp' value="+qGp+"></td>"+"<td><input type='text' id='storageMethod' value="+storageMethod+"></td>"+
                "<td><input type='text' id='number' value="+number+"></td><td>"+upFrameTime+"</td><td>"+merId+"</td><td>"+specificationID+"</td><td>"+suptID+"</td><td><input type='button' value='取消' onclick='upXg(this,"+id+","+merType+","+commo+","+money+","+describe+","+origin+","+netContent+","+packingMethod+","+brand+","+qGp+","+storageMethod+","+number+","+upFrameTime+","+merId+","+specificationID+","+suptID+");'><input type='button' onclick='tjbc(this);' value='保存'></td>");
    };
    
    /* 修改取消 */
    function upXg(x,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p){
        alert("<td>"+a+"</td><td>"+b+"</td><td>"+c+"</td><td>"+d+"</td><td>"+e+"</td><td>"+f+"</td><td>"+g+"</td><td>"+h+"</td><td>"+i+"</td><td>"+j+"</td><td>"+k+"</td><td>"+l+"</td><td>"+m+"</td><td>"+n+"</td><td>"+o+"</td><td>"+p+"</td><td><input type='button' name='sc' onclick='del(this);' value='删除'><input type='button' name='xg' onclick='upd(this);' value='修改'></td>");
        var tr = $(x).parent().parent();
        tr.html("<td>"+a+"</td>"+"<td>"+b+"</td><td>"+c+"</td><td>"+d+"</td><td>"+e+"</td><td>"+f+"</td><td>"+g+"</td><td>"+h+"</td><td>"+i+"</td><td>"+j+"</td><td>"+k+"</td><td>"+l+"</td><td>"+m+"</td><td>"+n+"</td><td>"+o+"</td><td>"+p+"</td><td><input type='button' name='sc' onclick='del(this);' value='删除'><input type='button' name='xg' onclick='upd(this);' value='修改'></td>");
    }
    
    /* 修改保存 */
    function tjbc(bc){
        alert(254454);
        /* var addTr = $(this).parent().parent().parent().find("td").eq(0).find("input").val();
        // 到达自己本身的父类的父类的父类，在自己本身的祖父中找到第一个td 找到input获取其中的值 */
        var tr = $(bc).parent().parent();
        var id = tr.find("td").eq(0).text();
        var merType = tr.find("td").eq(1).find("input").val();
        var commo = tr.find("td").eq(2).find("input").val();
        var money = tr.find("td").eq(3).find("input").val();
        var describe = tr.find("td").eq(4).find("input").val();
        var origin = tr.find("td").eq(5).find("input").val();
        var netContent = tr.find("td").eq(6).find("input").val();
        var packingMethod = tr.find("td").eq(7).find("input").val();
        var brand = tr.find("td").eq(8).find("input").val();
        var qGp = tr.find("td").eq(9).find("input").val();
        var storageMethod = tr.find("td").eq(10).find("input").val();
        var number = tr.find("td").eq(11).find("input").val();
        var upFrameTime = tr.find("td").eq(12).find("input").val();
        var merId = tr.find("td").eq(13).find("input").val();
        var specificationID = tr.find("td").eq(14).find("input").val();
        var suptID = tr.find("td").eq(15).find("input").val();
        
        var panDuan = true;
        $.ajax({
            type: "get",  // 请求方式(post或get)
            async:false,  //默认true(异步请求),设置为false(同步请求)
            url:"<%=request.getContextPath()%>/supSer?sup=4", // 发送请求的地址
            dataType:"json",
            data:{"id":id,"commodity":commo,"merType":merType,"money":money,"describe":describe,"origin":origin,"netContent":netContent,"packingMethod":packingMethod,"brand":brand,"qGp":qGp,"storageMethod":storageMethod,"number":number,"upFrametTime":upFrameTime,"merId":merId,"specificationID":specificationID,"suptID":suptID,"state":0},   // 传参数
            success:function(res){
                /*  if(res == "1")
                 {
                     alert("修改失败");
                 }else{ */
                     tr.html("<td>"+id+"</td><td>"+merType+"</td><td>"+commo+"</td><td>"+money+"</td><td>"+describe+"</td><td>"+origin+"</td><td>"+netContent+"</td><td>"+packingMethod+"</td><td>"+brand+"</td><td>"+qGp+"</td><td>"+storageMethod+"</td><td>"+number+"</td><td>"+upFrameTime+"</td><td>"+merId+"</td><td>"+specificationID+"</td><td>"+suptID+"</td><input type='button' name='sc' onclick='del(this);' value='删除'><input type='button' name='xg' onclick='upd(this);' value='修改'></td>");
                /*  } */
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                alert("修改失败(get)error");
            }
        });
    }
    
</script>
<body>
    <form>
        <table border="2" id="table">
            <tr>
                <th>供应商商品编号</th>
                <th>商品类型</th>
                <th>商品名称</th>
                <th>商品价格</th>
                <th>商品描述</th>
                <th>商品产地</th>
                <th>商品净含量</th>
                <th>商品包装</th>
                <th>商品品牌</th>
                <th>商品保质期</th>
                <th>商品储藏方法</th>
                <th>商品数量</th>
                <th>商品上架时间</th>
                <th>商品编号</th>
                <th>商品规格编号</th>
                <th>供应商商品规格编号</th>
                <th>操作</th>
            </tr>
        </table>
        <input id="zui" type="button" value="添加"/>
    </form>
</body>
</html>