	var layer = layui.layer, form = layui.form, element = layui.emlement;
	var websocket = null;
	// 判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://localhost:8080/projectYr/websocket");
	} else {
		alert('当前浏览器 不支持')
	}

	// 连接发生错误的回调方法
	websocket.onerror = function() {
		layui.use('layer', function() {
		layer.msg("连接服务器发生错误", {
			icon : 2
		});
		});
	};

	// 连接成功建立的回调方法
	websocket.onopen = function() {
		layui.use('layer', function() {
		layer.msg("连接服务器成功", {
			icon : 1
		});
		});
	}

	// 接收到消息的回调方法
	websocket.onmessage = function(event) {
		setMessageInnerHTML(event.data);
	}

	// 连接关闭的回调方法
	websocket.onclose = function() {
		layui.use('layer', function() {
		layer.msg("服务器连接关闭", {
			icon : 3
		});
		});
	}
	
	// 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	window.onbeforeunload = function() {
		closeWebSocket();
	}

	// 将消息显示在网页上
	function setMessageInnerHTML(innerHTML) {
		var mydate = new Date();
		var t=mydate.toLocaleString();
		document.getElementById('message').innerHTML += '<span class="layui-badge-rim" style="border:1px solid #ff0000;color:#ff0000;font-size: 14px;">'+t+'</span>'
		+' <span class="layui-badge-rim" style="border:1px solid #129688;color:#129688;font-size: 14px;">'+$('#ip').val()+'</span><br/><a style="font-size:18px;">'+innerHTML 
		+'</a><br/><hr class="layui-bg-green">';
	}

	// 关闭WebSocket连接
	function closeWebSocket() {
		websocket.close();
	}

	// 发送消息
	function send() {
		layui.use('layedit', function() {
			var layedit = layui.layedit;
			var message = layedit.getContent(index);
			websocket.send(message);
		});
	}
