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
<style>
.wrap{  
	width: 62px;  
	white-space: nowrap;  
	text-overflow: ellipsis;  
	overflow: hidden;  
}  
 <style>
        * {
            margin: 0;
            padding: 0;
        }
        .head {
        	position: fixed;
            top: 71px;
            right: 18px;
            width: 300px;
            height: 218px;
            background-color: gray;
        }
        #target{
            max-width: 300px;
            max-height: 300px;
        }

        #preview-pane {
            width: 190px;
            height: 190px;
            overflow: hidden;
            border: 1px solid #009688;
        }
        #preview-pane .preview-container {
            width: 100%;
            height: 100%;
        }
        #preview-pane .preview-container img{
            max-width: 100%;
            max-height: 100%;

        }
</style>
<body>
	<div class="layui-tab" lay-filter="demo" style="margin: 20px 30px 20px 20px;">
  <ul class="layui-tab-title">
    <li class="layui-this">添加大图</li>
    <li>添加小图</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
		 <!-- 头像 -->
      <div class="head" >
          <img src=""  id="target" alt="裁剪区" />
          <input type="file" id="file" onchange="changeFile()" style="display: none;"/>
      </div>
      <!-- 预览盒子 -->
      <div id="preview-pane">
          <div class="preview-container">
              <img src="" class="jcrop-preview" alt="预览图"  id="Preview"/>
          </div>
      </div>
  
      <!-- 画板 -->
      <canvas id="myCan" width="200" height="200" style="display:none;"></canvas>
    <br/>
    <span class="layui-badge-rim" id="pictruename">文件路径</span>
    <br/>
    <button type="button" class="layui-btn layui-btn-normal" onClick="openBrowse()"><i class="layui-icon">&#xe654;</i> 选择图片</button>
    <br/>
    <button onClick="uploadFile()" class="layui-btn"><i class="layui-icon">&#xe67c;</i> 上传图片</button>
	</div>
    <div class="layui-tab-item" align="center">
    	<div class="layui-upload">
		  <button type="button" class="layui-btn layui-btn-normal" id="testList"><i class="layui-icon">&#xe654;</i> 选择图片</button>
		  <button type="button" class="layui-btn" id="testListAction"><i class="layui-icon">&#xe67c;</i> 立即添加</button>
		  <div class="layui-upload-list">
		    <table class="layui-table">
		      <thead>
		        <tr><th>预览</th>
		        <th>文件名</th>
		        <th>大小</th>
		        <th>状态</th>
		        <th>操作</th>
		      </tr></thead>
		      <tbody id="demoList"></tbody>
		    </table>
		  </div>
		</div> 
	</div>
  </div>
</div>
</body>
<script src="../src/js/jquery-2.2.4.min.js"></script>
<script src="../plugins/layui/layui.js"></script>
<script src="../jcrop/js/jquery.Jcrop.js"></script>
 <script type="text/javascript">

        // 定义一些使用的变量
        var     jcrop_api,//jcrop对象
                boundx,//图片实际显示宽度
                boundy,//图片实际显示高度
                realWidth,// 真实图片宽度
                realHeight, //真实图片高度

                // 使用的jquery对象
                $target = $('#target'),
                $preview = $('#preview-pane'),
                $pcnt = $('#preview-pane .preview-container'),
                $pimg = $('#preview-pane .preview-container img'),

                xsize = $pcnt.width(),
                ysize = $pcnt.height();



        //1、打开浏览器
        function openBrowse(){
            var ie=navigator.appName=="Microsoft Internet Explorer" ? true : false; 
            if(ie){ 
                document.getElementById("file").click(); 
            }else{
                var a=document.createEvent("MouseEvents");
                a.initEvent("click", true, true);  
                document.getElementById("file").dispatchEvent(a);
            } 
        }

        //2、从 file 域获取 本地图片 url 
        function getFileUrl(sourceId) { 
            var url; 
            if (navigator.userAgent.indexOf("MSIE")>=1) { // IE 
            url = document.getElementById(sourceId).value; 
            } else if(navigator.userAgent.indexOf("Firefox")>0) { // Firefox 
            url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0)); 
            } else if(navigator.userAgent.indexOf("Chrome")>0) { // Chrome 
            url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0)); 
            } else if(navigator.userAgent.indexOf("Safari")>0) { // Chrome 
            url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0)); 
            } 
            return url; 
        } 
        //选择文件事件
        function changeFile() {
            var url = getFileUrl("file");//根据id获取文件路径
            preImg(url);
            return false;
        }

        //3、将本地图片 显示到浏览器上 
        function preImg(url) { 

            console.log('url===' + url);
            //图片裁剪逻辑
            if(jcrop_api)//判断jcrop_api是否被初始化过
            {
                jcrop_api.destroy();
            }

            //初始化预览div内容
            initPreview();
            var p = document.getElementById('Preview');
            p.src = url;

            //初始化图片
            initTarget();
            var image = document.getElementById('target');
            image.onload=function(){//图片加载是一个异步的过程
                    //获取图片文件真实宽度和大小
                    var img = new Image();
                    img.onload=function(){
                        realWidth = img.width;
                        realHeight = img.height;
                      
                        //获取图片真实高度之后
                        initJcrop();//初始化Jcrop插件
                        initCanvas();//初始化Canvas内容
                    };
                    img.src = url;
            };
            image.src = url;
        } 

        //初始化Jcrop插件
        function initJcrop(){
        	$('#pictruename').text($('#file').val());
            console.log('init',[xsize,ysize]);
            $target.removeAttr("style");//清空上一次初始化设置的样式
            $target.Jcrop({
              onChange: updatePreview,
              onSelect: updatePreview,
              aspectRatio: xsize / ysize
            },function(){
            //初始化后回调函数
            // 获取图片实际显示的大小
            var bounds = this.getBounds();
            boundx = bounds[0];//图片实际显示宽度
            boundy = bounds[1];//图片实际显示高度

            // 保存jcrop_api变量
            jcrop_api = this;
              
            });  
        }

        
        //更新显示预览内容
        function updatePreview(c){
            if (parseInt(c.w) > 0)
            {
                var rx = xsize / c.w;
                var ry = ysize / c.h;

                $pimg.css({
                    maxWidth:  Math.round(rx * boundx) + 'px',
                    maxHeight: Math.round(ry * boundy) + 'px',
                      width: Math.round(rx * boundx) + 'px',
                    height: Math.round(ry * boundy) + 'px',
                    marginLeft: '-' + Math.round(rx * c.x) + 'px',
                    marginTop: '-' + Math.round(ry * c.y) + 'px'
                });

                //更新canvas画板内容
                var img=document.getElementById("target");
                var ct=document.getElementById("myCan");
                var ctx=ct.getContext("2d");
                //清空画板
                ctx.clearRect(0,0, ct.width, ct.height); 
                //.drawImage(图像对象,原图像截取的起始X坐标,原图像截取的起始Y坐标,原图像截取的宽度,原图像截取的高度，绘制图像的起始X坐标,绘制图像的起始Y坐标,绘制图像所需要的宽度,绘制图像所需要的高度);
                ctx.drawImage(img, c.x/boundx * realWidth,c.y/boundy * realHeight, c.w/boundx * realWidth, c.h/boundy * realHeight,0,0, ct.width, ct.height);
            }
        }

        //初始化预览div内容
        function initTarget(){
            $target.removeAttr("style");//清空上一次初始化设置的样式
            $target.css({
                  maxWidth:  '100%',
                maxHeight: '100%'
              });
        }
        //初始化预览div内容
        function initPreview(){
            $pimg.removeAttr("style");//清空上一次初始化设置的样式
            $pimg.css({
                  maxWidth:  xsize + 'px',
                maxHeight: ysize + 'px'
              });
        }

        //初始化canvas画板内容
        function initCanvas(){
            //更新canvas画板内容
            var img= document.getElementById("target");
            var ct= document.getElementById("myCan");
            var ctx = ct.getContext("2d");
           
            var myCanWidth = $('#myCan').width();
            var myCanHeight = $('#myCan').height();

            //清空画板
            ctx.clearRect(0,0, ct.width, ct.height); 

             //.drawImage(图像对象,原图像截取的起始X坐标,原图像截取的起始Y坐标,原图像截取的宽度,原图像截取的高度，绘制图像的起始X坐标,绘制图像的起始Y坐标,绘制图像所需要的宽度,绘制图像所需要的高度);
            var dWidth = realWidth;//绘制实际宽度
            var dHeight = realHeight;//绘制实际高度
            if(dWidth > myCanWidth)
            {
                dHeight = myCanWidth / dWidth *  dHeight;
                dWidth = myCanWidth;
            }
            if(dHeight > myCanHeight)
            {
                dWidth = myCanHeight / dHeight * dWidth ;
                dHeight = myCanHeight;
            }
            ctx.drawImage(img,0,0, realWidth, realHeight, 0,0,  dWidth, dHeight);
        }

        //文件上传
        function uploadFile(){
            //获取裁剪完后的base64图片url,转换为blob
            var data=document.getElementById("myCan").toDataURL();
            var formData=new FormData();
            formData.append("imageName",dataURLtoBlob(data));
            var httprequest= null;
            if (window.XMLHttpRequest) {
                httprequest = new XMLHttpRequest();
            } else {
                httprequest = new ActiveXObject('MicroSoft.XMLHTTP');
            }
            var apiurl= ""; //上传图片的api接口，自行填写
            httprequest.open('POST',apiurl,true);
            httprequest.send(formData);
            httprequest.onreadystatechange= function () {
                
                if(httprequest.readyState == 4 ){
                    
                    if(httprequest.status == 200)
                    {
                        var json=JSON.parse(httprequest.responseText);
                        console.log(json);
                        
                    }else
                    {
                        alert('获取数据错误,错误代码：' + httprequest.status + '错误信息：' + httprequest.statusText);
                    }
                }
            };
        }
        
        //把base64位的toDataURL图片转换成blob
        function dataURLtoBlob(dataurl) {  
            var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],  
                    bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);  
            while (n--) {  
                u8arr[n] = bstr.charCodeAt(n);  
            }  
            return new Blob([u8arr], { type: mime });  
        } 
layui.use(['upload','form','element'], function(){
	  var $ = layui.jquery,
	  upload = layui.upload;
	  
	  //小图上传
	  var demoListView = $('#demoList')
	  ,uploadListIns = upload.render({
	    elem: '#testList',
	    url: '',
	    accept: 'images',
	    size:1024,
	    multiple: true,
	    auto: false,
	    bindAction: '#testListAction',
	    choose: function(obj){   
	      var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
	      //读取本地文件
	      obj.preview(function(index, file, result){
	        var tr = $(['<tr id="upload-'+ index +'">'
	          ,"<td style='width:62px;'><img style='width:60px;height:30px;' src='"+ result +"' class='layui-upload-img'></td>"
	          ,'<td><div class="wrap" title="'+file.name+'">'+file.name+'</div></td>'
	          ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
	          ,'<td>等待上传</td>'
	          ,'<td>'
	            ,'<a href="javascript:;" class="demo-reload layui-hide">重传 | </a>'
	            ,'<a href="javascript:;" class="demo-delete">删除</a>'
	          ,'</td>'
	        ,'</tr>'].join(''));
	        
	        //单个重传
	        tr.find('.demo-reload').on('click', function(){
	          obj.upload(index, file);
	        });
	        
	        //删除
	        tr.find('.demo-delete').on('click', function(){
	          delete files[index]; //删除对应的文件
	          tr.remove();
	          uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
	        });
	        
	        demoListView.append(tr);
	      });
	    }
	    ,done: function(res, index, upload){
	      if(res.code == 0){ //上传成功
	        var tr = demoListView.find('tr#upload-'+ index)
	        ,tds = tr.children();
	        tds.eq(3).html('<span style="color: #5FB878;">上传成功</span>');
	        tds.eq(4).html(''); //清空操作
	        return delete this.files[index]; //删除文件队列已经上传成功的文件
	      }
	      this.error(index, upload);
	    }
	    ,error: function(index, upload){
	      var tr = demoListView.find('tr#upload-'+ index)
	      ,tds = tr.children();
	      tds.eq(3).html('<span style="color: #FF5722;">上传失败</span>');
	      tds.eq(4).find('.demo-reload').removeClass('layui-hide'); //显示重传
	    }
	  });
});
    </script>
</html>