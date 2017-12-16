<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加图片</title>
<link rel="stylesheet" href="../jcrop/css/jquery.Jcrop.css">
<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
</head>
<body>
	<br />
	<div class="layui-container" width="100%" height="100%" align="center">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md8" style="width: 100%;">
				<form class="layui-form layui-form-pane" action="#" method="get">
					<div class="layui-form-item">
						<label class="layui-form-label" style="width:150px;">文件名</label>
						<div class="layui-input-inline" style="width:240px;">
							<input type="text" name="username" autocomplete="off"
								placeholder="请输入文件名" class="layui-input"> 
						</div>
						<button type="button" class="layui-btn layui-btn-normal" id="test1"><i class="layui-icon">&#xe654;</i> 选择图片</button>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label" style="width:150px;">图片链接(base64)</label>
						<div class="layui-input-inline" style="width:240px;">
							<input type="text" name="url" autocomplete="off"
								placeholder="文件base64" class="layui-input"> 
						</div>
						<button type="button" class="layui-btn" id="upload"><i class="layui-icon">&#xe67c;</i> 立即添加</button>
					</div>
					<hr class="layui-bg-green">
					<div class="layui-upload">
					  <div class="layui-upload-list">
					    <img class="layui-upload-img" id="img" style="width:520px;height:290px;">
					  </div>
					</div> 
				</form>
			</div>
		</div>
	</div>
</body>
<script src="../src/js/jquery-2.2.4.min.js"></script>
<script src="../plugins/layui/layui.js"></script>
<script src="../jcrop/js/jquery.Jcrop.js"></script>
<script>
layui.use('upload', function(){
	  var $ = layui.jquery,
	  upload = layui.upload;
	  var uploadInst = upload.render({
	    elem: '#test1',
	    url: '',
	    size:1024,
	    auto: false,
	    bindAction: '#upload',
	    choose: function(obj){
	    	 obj.preview(function(index, file, result){
		        $('#img').attr('src', result);
		        $("input[name='username']").val(file.name);
		        $("input[name='url']").val(result);
		        $('#img').Jcrop();
		      });
	    }
	});
});
</script>
</html>