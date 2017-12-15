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
			<div class="layui-logo" style="font-size: 35px;">某<i class="layui-icon" style="font-size: 35px;" id="x">&#xe62c;</i>宝</div>
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
				<ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar"
					kit-navbar>
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="javascript:;"><i class="layui-icon">&#xe614;</i><span>
								系统管理</span></a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'user/user.jsp',icon:'&#xe613;',title:'账号管理',id:'1'}"><i
									class="layui-icon">&#xe613;</i><span> 账号管理</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'role/role.jsp',icon:'&#xe612;',title:'角色管理',id:'2'}"><i
									class="layui-icon">&#xe612;</i><span> 角色管理</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe857;',title:'权限管理',id:'3'}"><i
									class="layui-icon">&#xe857;</i><span> 权限管理</span></a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a class="" href="javascript:;"><i
							class="layui-icon">&#xe857;</i><span> 营销管理</span></a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe857;',title:'商品管理',id:'4'}"><i
									class="layui-icon">&#xe857;</i><span> 商品管理</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe63c;',title:'订单管理',id:'5'}"><i
									class="layui-icon">&#xe63c;</i><span> 订单管理</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe63c;',title:'退单管理',id:'6'}"><i
									class="layui-icon">&#xe63c;</i><span> 退单管理</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe612;',title:'买家',id:'7'}"><i
									class="layui-icon">&#xe612;</i><span> 买家</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe612;',title:'卖家',id:'8'}"><i
									class="layui-icon">&#xe612;</i><span> 卖家</span></a>
							</dd>
							
						</dl>
					</li>
					<li class="layui-nav-item"><a class="" href="javascript:;"><i
							class="layui-icon">&#xe698;</i><span> 采购管理</span></a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe63c;',title:'采购订单',id:'9'}"><i
									class="layui-icon">&#xe63c;</i><span> 采购订单</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe63c;',title:'入库单',id:'10'}"><i
									class="layui-icon">&#xe63c;</i><span> 入库单</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe63c;',title:'采购退货单',id:'11'}"><i
									class="layui-icon">&#xe63c;</i><span> 采购退货单</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe68e;',title:'供应商管理',id:'12'}"><i
									class="layui-icon">&#xe68e;</i><span> 供应商管理</span></a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item"><a class="" href="javascript:;"><i
							class="layui-icon">&#xe68e;</i><span> 仓储管理</span></a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe68e;',title:'仓库管理',id:'13'}"><i
									class="layui-icon">&#xe68e;</i><span> 仓库管理</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe6b2;',title:'库存管理',id:'14'}"><i
									class="layui-icon">&#xe6b2;</i><span> 库存管理</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe6b2;',title:'库位管理',id:'15'}"><i
									class="layui-icon">&#xe6b2;</i><span> 库位管理</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe6b2;',title:'库存节点管理',id:'16'}"><i
									class="layui-icon">&#xe6b2;</i><span> 库存节点管理</span></a>
							</dd>
						</dl>	
					</li>
					<li class="layui-nav-item"><a class="" href="javascript:;"><i
							class="layui-icon">&#xe631;</i><span> 售后管理</span></a>
							<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe6b2;',title:'售后申请审核',id:'17'}"><i
									class="layui-icon">&#xe6b2;</i><span> 售后申请审核</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe61a;',title:'售后收货',id:'18'}"><i
									class="layui-icon">&#xe61a;</i><span> 售后收货</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe642;',title:'售后质检',id:'19'}"><i
									class="layui-icon">&#xe642;</i><span> 售后质检</span></a>
							</dd>
							</dl>
					</li>
					<li class="layui-nav-item"><a class="" href="javascript:;"><i
							class="layui-icon">&#xe63c;</i><span> 财务单据</span></a>
							<dl class="layui-nav-child">
								<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe857;',title:'销售订单收款单管理',id:'20'}"><i
									class="layui-icon">&#xe857;</i><span> 销售订单收款单管理</span></a>
								</dd>
								<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe857;',title:'销售订单退款单管理',id:'21'}"><i
									class="layui-icon">&#xe857;</i><span> 销售订单退款单管理</span></a>
								</dd>
								<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe857;',title:'采购订单付款单管理',id:'22'}"><i
									class="layui-icon">&#xe857;</i><span> 采购订单付款单管理</span></a>
								</dd>
								<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe857;',title:'采购订单退款单管理',id:'23'}"><i
									class="layui-icon">&#xe857;</i><span> 采购订单退款单管理</span></a>
								</dd>
								<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe857;',title:'采购预付款查询',id:'24'}"><i
									class="layui-icon">&#xe857;</i><span> 采购预付款查询</span></a>
								</dd>
							</dl>
					</li>
					<li class="layui-nav-item">
							<a href="javascript:;" kit-target
									data-options="{url:'picture/picture.jsp',icon:'&#xe64a;',title:'图片管理',id:'99'}"><i
									class="layui-icon">&#xe64a;</i><span> 图片管理</span></a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" kit-target
									data-options="{url:'a.jsp',icon:'&#xe640;',title:'回收站',id:'100'}"><i
									class="layui-icon">&#xe640;</i><span> 回收站</span></a>
						</li>
				</ul>
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
	$(document).ready(function(){
		blink($('#x'));
	});
	function blink(selector){    
		$(selector).fadeOut('slow', function(){     
			$(this).fadeIn('slow', function(){        
				blink(this);      
			});    
		});  
	}
	function TIM(){
		layer.open({
			title : 'TIM',
			type : 2,
			anim : 2,
			shade : false,
			maxmin : false,
			resize : false,
			scrollbar : false,
			content : 'xiaoxi.jsp',
			area : [ '300px', '400px' ],
			offset : 'rb',
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
	</script>
</body>
</html>