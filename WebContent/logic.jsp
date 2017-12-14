<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath() %>/jquery-3.2.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
/* 页面加载完出现 */
	$(document).ready(function(){
	    $.ajax({
	        type: "get",  // 请求方式(post或get)
	        async:false,  //默认true(异步请求),设置为false(同步请求)
	        url:"<%=request.getContextPath() %>/supdao?sup=5", // 发送请求的地址
	        dataType:"json",
	        success:function(res){
	            for (var i = 0; i < res.length; i++) {
	                var js = res[i];
	                $("#table").append("<tr id='jia'><td>"+js.id+"</td><td>"+js.name+"</td><td><input type='button' name='sc' onclick='del(this);' value='删除'><input type='button' name='xg' onclick='upd(this);' value='修改'></td></tr>");
	            }
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown){
	            alert("后台出现错误");
	        }
	    });
	    
	    
	    /* 添加 */
	    $("#zui").click(function(){
	        $("tr:last").after("<tr id='jia'>"+"<td><input type='text' id='id'></td>"+"<td><input type='text' id='name'></td>"+
	        "<td><input type='button' value='取消' onclick='cancel(this);'><input type='button' onclick='baoCun(this);' value='保存'></td>"+"</tr>");
	    });
	});
	
	/* 保存 */
	function baoCun(bc){
	    /* var addTr = $(this).parent().parent().parent().find("td").eq(0).find("input").val();
	    // 到达自己本身的父类的父类的父类，在自己本身的祖父中找到第一个td 找到input获取其中的值 */
	    var tr = $(bc).parent().parent();
	    var id = tr.find("td").eq(0).find("input").val();
	    var name = tr.find("td").eq(1).find("input").val();
	    var panDuan = true;
	    $.ajax({
	        type: "post",  // 请求方式(post或get)
	        async:false,  //默认true(异步请求),设置为false(同步请求)
	        url:"<%=request.getContextPath() %>/supdao", // 发送请求的地址
	        dataType:"json",
	        data:{"id":id}, // 传参数
	        success:function(res){
	            if(res == "1"){
	                alert("添加ID已存在,请重新输入");
	                panDuan = false;
	            }
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown){
	            alert("保存失败(get)error");
	        }
	    });
	    /* 判断ID是否重复 */
	    if(panDuan){
	        $.ajax({
	            type: "get",  // 请求方式(post或get)
	            async:false,  //默认true(异步请求),设置为false(同步请求)
	            url:"<%=request.getContextPath() %>/supdao?sup=2", // 发送请求的地址
	            dataType:"json",
	            data:{"id":id,"commodity":name},   // 传参数
	            success:function(res){
	                 if(res == "1")
	                 {
	                     alert("保存失败");
	                 }else{
	                    tr.html("<td>"+id+"</td><td>"+name+"</td><td><input type='button' name='sc' onclick='del(this);' value='删除'><input type='button' name='xg' onclick='upd(this);' value='修改'></td>");
	                 }
	            },
	            error:function(XMLHttpRequest, textStatus, errorThrown){
	                alert("保存失败(post)error");
	            }
	        });
	    }
	}
	    	
	
	/* 取消 */
	function cancel(cc){ // 有多个值时会把前面全删掉再删除选择删除的那个值
	    $(cc).parent().parent().remove(); // 删除tr
	}
	
	/* 删除值 (删除数据库中保存好的值) */
	function del(dlt){
	    var a = $(dlt).parent().parent() // 自己本身的父类的父类 tr
	    var va=a.find("td").eq(0).text(); // 获取到第一个td(id)的文本内容
	    if(confirm('确定删除么')){
	        $.ajax({
	            url:"<%=request.getContextPath() %>/supdao?sup=3",
	            type:"get",
	            async:false,
	            dataType:"json",
	            data:{"supDel":va}, // data是服务器返回的数据
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
	    var name = tr.find("td").eq(1).text();
	    tr.html("<td>"+id+"</td>"+"<td><input type='text' id='name' value="+name+"></td>"+
	    "<td><input type='button' value='取消' onclick='upXg(this,"+id+","+name+");'><input type='button' onclick='tjbc(this);' value='保存'></td>");
	};
	
	/* 修改取消 */
	function upXg(x,y,a){
	    alert("<td>"+y+"</td>"+"<td>"+a+"</td><td><input type='button' name='sc' onclick='del(this);' value='删除'><input type='button' name='xg' onclick='upd(this);' value='修改'></td>");
	    var tr = $(x).parent().parent();
	    tr.html("<td>"+y+"</td>"+"<td>"+a+"</td><td><input type='button' name='sc' onclick='del(this);' value='删除'><input type='button' name='xg' onclick='upd(this);' value='修改'></td>");
	}
	
	/* 添加保存 */
	function tjbc(bc){
	    /* var addTr = $(this).parent().parent().parent().find("td").eq(0).find("input").val();
	    // 到达自己本身的父类的父类的父类，在自己本身的祖父中找到第一个td 找到input获取其中的值 */
	    var tr = $(bc).parent().parent();
	    var id = tr.find("td").eq(0).text();
	    var name = tr.find("td").eq(1).find("input").val();
	    var panDuan = true;
	    $.ajax({
	        type: "post",  // 请求方式(post或get)
	        async:false,  //默认true(异步请求),设置为false(同步请求)
	        url:"<%=request.getContextPath() %>/supdao?sup=4", // 发送请求的地址
	        dataType:"json",
	        data:{"id":id,"commodity":name},   // 传参数
	        success:function(res){
	             if(res == "1")
	             {
	                 alert("修改失败");
	             }else{
	                tr.html("<td>"+id+"</td><td>"+name+"</td><input type='button' name='sc' onclick='del(this);' value='删除'><input type='button' name='xg' onclick='upd(this);' value='修改'></td>");
	             }
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown){
	            alert("修改失败(post)error");
	        }
	    });
	}
</script>
<body>
    <form>
        <table border="2" id="table">
            <tr>
                <th>编号</th>
                <th>商品信息</th>
                <th>操作</th>
            </tr>
            <c:forEach var="sl" items="${selist}" varStatus="la">
                <tr>
                    <td>${sl.id}</td>
                    <td>${sl.name}</td>
                    <td><input type="button" name="sc" onclick="del(this);" value="删除"><input type="button" name="xg" onclick="upd(this);" value="修改"></td>
                </tr>
            </c:forEach>
        </table>
        <input id="zui" type="button" value="添加"/>
    </form>
</body>
</html>