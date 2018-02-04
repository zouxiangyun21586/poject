<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="/projectYr/js/jquery-3.2.1.js"></script>
	<script src="../plugins/layui/layui.js"></script>
	<script src="<%=request.getContextPath() %>/js/fenye.js"></script>
	<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
</head>
<script type="text/javascript">
/* 页面加载完出现 */
    $(document).ready(function(){
    	getAd();
        $("#sel").click(function (){
        	getAd();
        });
    });
    
    function getAd(){
    	layui.use([ 'layer', 'form' ], function() {
            var form = layui.form;
            form.render('select');
            $.ajax({
                type: "get",  // 请求方式(post或get)
                async:false,  //默认true(异步请求),设置为false(同步请求)
                url:"<%=request.getContextPath()%>/supSer?sup=1", // 发送请求的地址
                scriptCharset: 'utf-8',
                dataType:"json",
                data:{"pageNow":getPar("pageNow"),"type":"list","select":$('#select').val()},
                success:function(res){
                    var pageContent = page(res.pageCount,res.pageNow,res.pageCode);
                    var list =  res.list;
                    var tobody_limt = "";
                    $("#tobody_limt").empty();
                    for(var i in list){
                        if("0"==list[i].auditStatus){ // 未提交
                             tobody_limt += "<tr id='zui'><td>"+list[i].suptId+
                                            "</td><td style='display:none;'>"+list[i].account_id+
                                            "</td><td style='display:none;'>"+list[i].merId+
                                            "</td><td>"+list[i].account+
                                            "</td><td>"+list[i].commo+
                                            "</td><td>"+list[i].money+
                                            "</td><td>"+list[i].number+
                                            "</td><td>"+list[i].netContent+
                                            "</td><td>"+list[i].month+
                                            "</td><td>"+list[i].brand+
                                            "</td><td>"+list[i].origin+
                                            "</td><td>"+list[i].typeName+
                                            "</td><td>"+list[i].storageMethod+
                                            "</td><td>"+list[i].packingMethod+
                                            "</td><td style='display:none;'>"+list[i].describe+
                                            "</td><td>"+list[i].upFrameTime+
                                            "</td><td style='display:none;'>"+list[i].auditStatus+
                                            "</td><td><button class='layui-btn layui-btn-xs' name='sj' onclick='sjia(this);'>上架商品</button><button class='layui-btn layui-btn-xs' id='upd' name='xg' onclick='upd("+list[i].merId+")'>修改商品</button></td></tr>";
                        }else if("1"==list[i].auditStatus){ // 未审核
                            tobody_limt +=  "<tr id='zui'><td>"+list[i].suptId+
				                            "</td><td style='display:none;'>"+list[i].account_id+
				                            "</td><td style='display:none;'>"+list[i].merId+
				                            "</td><td>"+list[i].account+
				                            "</td><td>"+list[i].commo+
				                            "</td><td>"+list[i].money+
				                            "</td><td>"+list[i].number+
				                            "</td><td>"+list[i].netContent+
				                            "</td><td>"+list[i].month+
				                            "</td><td>"+list[i].brand+
				                            "</td><td>"+list[i].origin+
				                            "</td><td>"+list[i].typeName+
				                            "</td><td>"+list[i].storageMethod+
				                            "</td><td>"+list[i].packingMethod+
				                            "</td><td style='display:none;'>"+list[i].describe+
				                            "</td><td>"+list[i].upFrameTime+
				                            "</td><td style='display:none;'>"+list[i].auditStatus+
                                            "</td><td><button class='layui-btn layui-btn-xs' name='cx' onclick='cancel(this);'>撤销商品</button></td></tr>";
                        }else if("2"==list[i].auditStatus){ // 已审核            
                             tobody_limt += "<tr id='zui'><td>"+list[i].suptId+
				                            "</td><td style='display:none;'>"+list[i].account_id+
				                            "</td><td style='display:none;'>"+list[i].merId+
				                            "</td><td>"+list[i].account+
				                            "</td><td>"+list[i].commo+
				                            "</td><td>"+list[i].money+
				                            "</td><td>"+list[i].number+
				                            "</td><td>"+list[i].netContent+
				                            "</td><td>"+list[i].month+
				                            "</td><td>"+list[i].brand+
				                            "</td><td>"+list[i].origin+
				                            "</td><td>"+list[i].typeName+
				                            "</td><td>"+list[i].storageMethod+
				                            "</td><td>"+list[i].packingMethod+
				                            "</td><td style='display:none;'>"+list[i].describe+
				                            "</td><td>"+list[i].upFrameTime+
				                            "</td><td style='display:none;'>"+list[i].auditStatus+
                                            "</td><td><button class='layui-btn layui-btn-xs' name='xj' onclick='xiajia(this);'>下架商品</button></td></tr>";
                        }
                    }
                    $("#tobody_limt").append(tobody_limt);
               }
           });
       });
   };
    
   
    //撤销
   function cancel(cencel){
       if(confirm("确定撤销商品?")){
           var tr = $(cencel).parent().parent();
           var suptId = tr.find("td").eq(0).text();
           var account_id = tr.find("td").eq(1).text();
           var mer_id = tr.find("td").eq(2).text();
           var account = tr.find("td").eq(3).text();
           var commo = tr.find("td").eq(4).text();
           var money = tr.find("td").eq(5).text();
           var number = tr.find("td").eq(6).text();
           var netContent = tr.find("td").eq(7).text();
           var Month = tr.find("td").eq(8).text();
           var brand = tr.find("td").eq(9).text();
           var origin = tr.find("td").eq(10).text();
           var merType = tr.find("td").eq(11).text();
           var storageMethod = tr.find("td").eq(12).text();
           var packingMethod = tr.find("td").eq(13).text();
           var describe = tr.find("td").eq(14).text();
           var upFrameTime = tr.find("td").eq(15).text();
           var auditStatus = tr.find("td").eq(16).text();
           
           $.ajax({
               url:"<%=request.getContextPath()%>/supSer?sup=3",
               scriptCharset: 'utf-8',
               type:"get",
               cache:false,
               async:true,
               dataType:"text",
               data:{"merId":mer_id,"account_id":account_id},
               success:function(result){
                  tr.html("<tr id='zui'><td>"+suptId+
                            "</td><td style='display:none;'>"+account_id+
                            "</td><td style='display:none;'>"+mer_id+
                            "</td><td>"+account+
                            "</td><td>"+commo+
                            "</td><td>"+money+
                            "</td><td>"+number+
                            "</td><td>"+netContent+
                            "</td><td>"+Month+
                            "</td><td>"+brand+
                            "</td><td>"+origin+
                            "</td><td>"+merType+
                            "</td><td>"+storageMethod+
                            "</td><td>"+packingMethod+
                            "</td><td style='display:none;'>"+describe+
                            "</td><td>"+upFrameTime+
                            "</td><td style='display:none;'>"+auditStatus+
		                    "<td><button class='layui-btn layui-btn-xs' name='sj' onclick='sjia(this);'>上架商品</button><button class='layui-btn layui-btn-xs' id='upd' name='xg'  onclick='upd("+mer_id+")'>修改商品</button></td>");
                  			location.reload();
               },
               error:function(XMLHttpRequest, textStatus, errorThrown)
               {
                   alert("撤销审核商品失败");
                   alert(XMLHttpRequest.status);
    	           alert(XMLHttpRequest.readyState);
    	           alert(textStatus);
               }
           });
       }
   }
   
    //下架
    function xiajia(xj){
        if(confirm("确定下架商品?")){
        	var tr = $(xj).parent().parent();
        	var suptId = tr.find("td").eq(0).text();
            var account_id = tr.find("td").eq(1).text();
            var mer_id = tr.find("td").eq(2).text();
            var account = tr.find("td").eq(3).text();
            var commo = tr.find("td").eq(4).text();
            var money = tr.find("td").eq(5).text();
            var number = tr.find("td").eq(6).text();
            var netContent = tr.find("td").eq(7).text();
            var Month = tr.find("td").eq(8).text();
            var brand = tr.find("td").eq(9).text();
            var origin = tr.find("td").eq(10).text();
            var merType = tr.find("td").eq(11).text();
            var storageMethod = tr.find("td").eq(12).text();
            var packingMethod = tr.find("td").eq(13).text();
            var describe = tr.find("td").eq(14).text();
            var upFrameTime = tr.find("td").eq(15).text();
            var auditStatus = tr.find("td").eq(16).text();
            
            $.ajax({
                url:"<%=request.getContextPath()%>/supSer?sup=6",
                scriptCharset: 'utf-8',
                type:"get",
                cache:false,
                async:true,
                data:{"mer_id":mer_id},
                dataType:"text",
                success:function(result){
                    $.each(result,function(index,item){
                        var Downtime = item.upFrameTime;
                        tr.html("<tr id='zui'><td>"+suptId+
                                "</td><td style='display:none;'>"+account_id+
                                "</td><td style='display:none;'>"+mer_id+
                                "</td><td>"+account+
                                "</td><td>"+commo+
                                "</td><td>"+money+
                                "</td><td>"+number+
                                "</td><td>"+netContent+
                                "</td><td>"+Month+
                                "</td><td>"+brand+
                                "</td><td>"+origin+
                                "</td><td>"+merType+
                                "</td><td>"+storageMethod+
                                "</td><td>"+packingMethod+
                                "</td><td style='display:none;'>"+describe+
                                "</td><td>"+upFrameTime+
                                "</td><td style='display:none;'>"+auditStatus+
		                        "<td><button class='layui-btn layui-btn-xs' name='sj' onclick='sjia(this);'>上架商品</button><button class='layui-btn layui-btn-xs' name='xg' id='upd' onclick='upd("+mer_id+")'>修改商品</button></td>");
		                        location.reload();
                    });
                },
                error:function(XMLHttpRequest, textStatus, errorThrown)
                {
                	alert("下架商品失败");
                	alert(XMLHttpRequest.status);
     	            alert(XMLHttpRequest.readyState);
     	            alert(textStatus);
                }
            });
        }
    }
    
    //上架
    function sjia(sj){
    	if(confirm("确定上架商品?")){
	    	var tr = $(sj).parent().parent();
	    	var suptId = tr.find("td").eq(0).text();
	        var account_id = tr.find("td").eq(1).text();
	        var mer_id = tr.find("td").eq(2).text();
	        var account = tr.find("td").eq(3).text();
	        var commo = tr.find("td").eq(4).text();
	        var money = tr.find("td").eq(5).text();
	        var number = tr.find("td").eq(6).text();
	        var netContent = tr.find("td").eq(7).text();
	        var Month = tr.find("td").eq(8).text();
	        var brand = tr.find("td").eq(9).text();
	        var origin = tr.find("td").eq(10).text();
	        var merType = tr.find("td").eq(11).text();
	        var storageMethod = tr.find("td").eq(12).text();
	        var packingMethod = tr.find("td").eq(13).text();
	        var describe = tr.find("td").eq(14).text();
	        var upFrameTime = tr.find("td").eq(15).text();
	        var auditStatus = tr.find("td").eq(16).text();
	        
	        $.ajax({
	            url:"<%=request.getContextPath()%>/supSer?sup=7",
	            type:"get",
	            cache:false,//取消缓存
	            async:true,//是否异步请求,修改false就表示同步,true表示异步
	            data:{"acc_id":account_id,"mer_id":mer_id,"sup_id":suptId},
	            dataType:"text",
	            success:function(result){
	            	tobody_limt +=  "<tr id='zui'><td>"+suptId+
					                "</td><td style='display:none;'>"+account_id+
					                "</td><td style='display:none;'>"+mer_id+
					                "</td><td>"+account+
					                "</td><td>"+commo+
					                "</td><td>"+money+
					                "</td><td>"+number+
					                "</td><td>"+netContent+
					                "</td><td>"+Month+
					                "</td><td>"+brand+
					                "</td><td>"+origin+
					                "</td><td>"+merType+
					                "</td><td>"+storageMethod+
					                "</td><td>"+packingMethod+
					                "</td><td style='display:none;'>"+describe+
					                "</td><td>"+upFrameTime+
					                "</td><td style='display:none;'>"+auditStatus+
					                "</td><td><button class='layui-btn layui-btn-xs' name='cx' onclick='cancel(this);'>撤销商品</button></td></tr>";
					                location.reload();
	            },
	            error:function(XMLHttpRequest, textStatus, errorThrown)
	            {
	                alert("上架失败!!");
	                alert(XMLHttpRequest.status);
	 	            alert(XMLHttpRequest.readyState);
	 	            alert(textStatus);
	            }
	        });
    	}
    }
    
    layui.use([ 'layer', 'element' ], function() {
    	var laypage = layui.laypage,
        layer = layui.layer;
        $('#zui').click(function(){
            layer.open({
                anim: 2,
                title : '添加商品',
                type: 2, //窗口类型
                resize:false,//禁止拉伸
                maxmin:false,//最大化,最小化
                shade: [0.3,'#000'],
                area: ['570px', '750px'],//窗口宽高
                content: ['jump.jsp','no']
            });
        });
    });
    
    function upd(uu){
    	alert(uu);
    	layer.open({
            title : '修改商品',
            type : 2,
            anim : 2,
            maxmin : false,
            resize : false,
            scrollbar : true,
            //页面路径
            content : '../supSer?sup=5&&state=0&&merId='+uu,
            area : [ '500px', '490px' ],//宽高
            shadeClose : true
        });
    };
        
</script>
<body>
<br/>
    <div class="layui-container" width="100%" height="100%" align="center">
		<div class="layui-row layui-col-space15">
		    <div class="layui-col-md8"  style="width: 100%;">
				<button class="layui-btn layui-btn-sm" style="float:left;" id="zui">
				    <i class="layui-icon">&#xe654;</i> 添加商品
				</button>
				<button class="layui-btn layui-btn-normal" style="float:right;margin-left:0px;" id="sel">
				    <i class="layui-icon">&#xe615;</i> 搜索
				</button>
				<input type="text" class="layui-input" placeholder="请输入要查询的供应商账号" style="float:right;width:250px;" id = "select" name="select"/>
				<div class='layui-form layui-form-pane' style="float:right;">
					<table id="table" class="layui-table" style="background-color:#ffffff6b;">
						<thead>
						  <tr>
							<th>编号</th>
							<td style="display: none;">账户ID</td>
							<td style="display: none;">商品ID</td>
							<td>账户名</td>
							<th>商品名称</th>
							<th>价格</th>
							<th>数量</th>
							<th>净含量</th>
							<th>保质期</th>
							<th>品牌</th>
							<th>出产地</th>
							<th>类型</th>
							<th>储藏方法</th>
							<th>包装</th>
							<td style="display: none;">商品描述</td>
							<th>上架时间</th>
							<th style="display: none;">供应状态</th>
							<th>操作</th>
						  </tr>
						</thead>
					  <tbody id="tobody_limt">
					     </tbody>
					</table>    
					<div id="page" class="page"></div>
				</div>
		    </div>
		</div>
    </div>
</body>
</html>