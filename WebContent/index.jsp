<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<c:set var="username" value="${username}"/>
<c:if test="${empty username}"><c:redirect url="login.jsp"/></c:if>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>某宝-后台</title>
<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet"
	href="plugins/font-awesome/css/font-awesome.min.css" media="all" />
<link rel="stylesheet" href="src/css/app.css" media="all" />
<link rel="stylesheet" href="src/css/themes/default.css" media="all"
	id="skin" kit-skin />
</head>

<body class="kit-theme">
	<div class="layui-layout layui-layout-admin kit-layout-admin">
		<div class="layui-header">
			<div class="layui-logo" style="font-size: 35px;">某<i class="layui-icon" style="font-size: 35px;" id="x"><script src="js/pmd.js"></script></i>宝</div>
			<div class="layui-logo kit-logo-mobile" style="font-size: 35px;">宝</div>
			<ul class="layui-nav layui-layout-right kit-nav">
			<li class="layui-nav-item"><a href="javascript:;" onclick="$('#news').fadeIn();"><i class="layui-icon">&#xe63f;</i> 发送信息</a></li>
				<li class="layui-nav-item"><a href="javascript:;"> <i
						class="layui-icon">&#xe63f;</i> 皮肤
				</a>
					<dl class="layui-nav-child skin">
                        <dd><a href="javascript:;" data-skin="default" style="color:#393D49;"><i class="layui-icon">&#xe658;</i> 默认</a></dd>
                        <dd><a href="javascript:;" data-skin="orange" style="color:#ff6700;"><i class="layui-icon">&#xe658;</i> 橘子橙</a></dd>
                        <dd><a href="javascript:;" data-skin="green" style="color:#00a65a;"><i class="layui-icon">&#xe658;</i> 原谅绿</a></dd>
                        <dd><a href="javascript:;" data-skin="pink" style="color:#FA6086;"><i class="layui-icon">&#xe658;</i> 少女粉</a></dd>
                        <dd><a href="javascript:;" data-skin="blue.1" style="color:#00c0ef;"><i class="layui-icon">&#xe658;</i> 天空蓝</a></dd>
                        <dd><a href="javascript:;" data-skin="red" style="color:#dd4b39;"><i class="layui-icon">&#xe658;</i> 枫叶红</a></dd>
                    </dl>
                </li>
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="./images/0.jpg" class="layui-nav-img"> ${username}
				</a>
					<dl class="layui-nav-child">
						<dd id="gr">
							<a href="javascript:;" id="grxx"><span>个人信息</span></a>
						</dd>
						
						<dd>
							<a href="javascript:;"><span>安全管理</span></a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="outservlet" id="return"><i
						class="fa fa-sign-out" aria-hidden="true"></i> 注销</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black kit-side">
			<div class="layui-side-scroll">
				<div class="kit-side-fold">
					<i class="fa fa-navicon" aria-hidden="true" style="line-height:35px;"></i>
				</div>
				<div class="layui-nav layui-nav-tree"  lay-filter="test" id="nvaul" kit-navbar></div>
				<!-- <ul class="layui-nav layui-nav-tree" id="nvaul" style="width:100%;" kit-navbar></ul> -->
			</div>
		</div>
		<div class="layui-body" id="container">
			<div style="padding: 15px;">
				<i class="layui-icon layui-anim layui-anim-rotate layui-anim-loop">&#xe63e;</i>
				请稍候...
			</div>
		</div>
		<ul class="layui-fixbar">
			<li class="layui-icon" id="im" style="background-color: #009688;">&#xe63a;<span id="news" class="layui-badge-dot"></span></li>
		</ul>
		<div class="layui-footer" align="center">
			2017 &copy; <a href="#">moubao.com</a> MIT license
		</div>
	</div>
	<script src="plugins/layui/layui.js"></script>
	<script src="src/js/jquery-2.2.4.min.js"></script>
	<script>
	layui.use(['tree','element','form','layer'], function(){
		var element = layui.element;
	$(document).ready(function(){
		blink($('#x'));
		var i=0;
		$.ajax({     
	        type: "get",//请求方式,默认GET
	        url: "powerServlet?id=0&i=1",     
	        dataType: "json",
	        success: function(data) {
	        	var dataObj=data;
	        	var fu="";
	        	$.each(dataObj, function(index, item){
	        		fu+="<li class='layui-nav-item'>"
	   	            	+"<a href='javascript:;'><i class='layui-icon'>"+item.icon+"</i> <span>"+item.name+"</span></a>"
	   	            	+"<dl class='layui-nav-child' id='fu"+item.id+"'></dl></li>";
	        		zi(item.id,i);//执行添加子节点
   	            	i++;
	        	});
	        	$('#nvaul').append(fu);
	        	element.render();
	        },error: function(XMLHttpRequest, textStatus, errorThrown) {
	        	layer.msg('父节点取值失败!',{icon: 2});
	        }
	     });	
	});
	function zi(id,i){
		$.ajax({     
	        type: "get",//请求方式,默认GET
	        url: "powerServlet?i=1&id="+id,     
	        dataType: "json",
	        success: function(data) {
	        	var dataObj=data;
	        	var zi="";
	        	$.each(dataObj, function(index, item){
        			zi+='<dd><a href="javascript:;" kit-target data-options="{url:\''+item.url
        				+'\',icon:\''+item.icon+'\',title:\''+item.name+'\',id:\''+item.id+'\'}"><i class="layui-icon">'
        				+item.icon+'</i><span> '+item.name+'</span></a></dd>';
        			
	        	});
	        	$('#fu'+id).append(zi);
	        },error: function(XMLHttpRequest, textStatus, errorThrown) {
	        	layer.msg('子节点取值失败!',{icon: 2});
	        }
	     });
	}
	function blink(selector){    
		$(selector).fadeOut('slow', function(){     
			$(this).fadeIn('slow', function(){        
				blink(this);      
			});    
		});  
	}
	function TIM(){
		layer.open({
			title:'SOCKETR',
			type : 2,
			anim : 2,
			shade : false,
			maxmin : false,
			resize : false,
			scrollbar : false,
			content : ['news/index.jsp','no'],
			area : [ '500px', '400px' ],
			shadeClose : true,
			success : function(layero, index) {
				$('#news').fadeOut();
				$('#im').fadeOut();
			},cancel: function(index, layero){ 
				$('#im').fadeIn();
			}
		});
	}
	$('#grxx').click(function(){
		layer.open({
			title : '个人信息',
			type : 2,
			anim : 2,
			shade : false,
			maxmin : false,
			resize : false,
			scrollbar : false,
			content : 'grxx.jsp',
			area : [ '250px', '300px' ],
			offset : 'rt',
			shadeClose : true,
			success : function(layero, index) {
				$('#gr').fadeOut();
			},cancel: function(index, layero){ 
				$('#gr').fadeIn();
			}
		});
	});
		var message;
		layui.config({
			base : 'src/js/',
			version : '1.0.1'
		}).use(
				[ 'app', 'message' ],
				function() {
					var app = layui.app, $ = layui.jquery, layer = layui.layer;
					message = layui.message;
					app.set({
						type : 'iframe'
					}).init();
					$('#im').click(function() {
						TIM();
					});
					$('dl.skin > dd').on('click', function() {
						var $that = $(this);
						var skin = $that.children('a').data('skin');
						switchSkin(skin);
					});
					var setSkin = function(value) {
						layui.data('kit_skin', {
							key : 'skin',
							value : value
						});
					}, getSkinName = function() {
						return layui.data('kit_skin').skin;
					}, switchSkin = function(value) {
						var _target = $('link[kit-skin]')[0];
						_target.href = _target.href.substring(0, _target.href
								.lastIndexOf('/') + 1)
								+ value
								+ _target.href.substring(_target.href
										.lastIndexOf('.'));
						setSkin(value);
					}, initSkin = function() {
						var skin = getSkinName();
						switchSkin(skin === undefined ? 'default' : skin);
					}();
				});
	});	
	</script>
</body>
</html>