		var i=0;
		$.ajax({     
	        type: "get",//请求方式,默认GET
	        url: "powerServlet?id=0",     
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
	        	//element.render('nav');
	        },error: function(XMLHttpRequest, textStatus, errorThrown) {
	     	   alert("失败");
	           alert(XMLHttpRequest.status);//200客户端请求已成功
	           alert(XMLHttpRequest.readyState);//4 响应内容解析完成，可以在客户端调用了
	           alert(textStatus);//parsererror
	        }
	     });	
	     
	});
	function zi(id,i){
		$.ajax({     
	        type: "get",//请求方式,默认GET
	        url: "powerServlet?id="+id,     
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
	        	element.render('nav');
	        },error: function(XMLHttpRequest, textStatus, errorThrown) {
	     	   alert("失败");
	           alert(XMLHttpRequest.status);//200客户端请求已成功
	           alert(XMLHttpRequest.readyState);//4 响应内容解析完成，可以在客户端调用了
	           alert(textStatus);//parsererror
	        }
	     });
	}
//tree数据结构
var json = [
        { title: "节点1", value: "jd1", data: [
            { title: "节点1.1", value: "jd1.1", data: [] },
            { title: "节点1.2", value: "jd1.2", data: [] },
            { title: "节点1.3", value: "jd1.3", data: [] },
            { title: "节点1.4", value: "jd1.4", data: [] }
            ]}, {title: "节点2", value: "jd2", data: [
                { title: "节点2.1", value: "jd2.1", data: [] },
                {title: "节点2.2", value: "jd2.2", data: [
                    { title: "节点2.2.1", value: "jd2.2.1", data: [] },
                    { title: "节点2.2.2", value: "jd2.2.2", data: [] },
                    { title: "节点2.2.3", value: "jd2.2.3", data: [] },
                    { title: "节点2.2.4", value: "jd2.2.4", data: [] }
                ]},
            { title: "节点2.3", value: "jd2.3", data: [] },
            { title: "节点2.4", value: "jd2.4", data: [] }
            ]}, 
        { title: "节点3", value: "jd3", data: [] },
        { title: "节点4", value: "jd4", data: [] }
];