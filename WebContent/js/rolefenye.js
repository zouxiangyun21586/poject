function urls() { //拆分取url中?后面的参数各和值,组成对象返回
		var url = location.href;// 获得全路径名
		var pars = url.split("?"); //url.substring((url.indexOf("?") + 1), url.length);
		if (pars.length == 2) {
			var parArr = pars[1].split("&");
			var parObj = new Object();
			for ( var i in parArr) {
				var s = parArr[i].split("=");
				parObj[s[0]] = s[1];
			}
			return parObj
		}else {
			return null;
		}
		
	}

	function getPar(name) {//根据参数名得到值
		var obj = urls();
		if (null == obj) {
			return '';
		}else {
			var v = obj[name];
			return v;
		}
	}
	function isErr() {//特殊参数直接提供一个方法获取值
		var par = getPar('err');
		if ("undefined" != par) {
			if (par === '1') {
				document.getElementById("errStr").innerHTML = '用户名错误';
			} else if (par === '2') {
				document.getElementById("errStr").innerHTML = '密码错误';
			} else if (par === '3') {
				document.getElementById("errStr").innerHTML = '用户名或密码错误';

			}
		}
	}

	/**
	 * 分页
	 * @param pageCount 总共页数
	 * @param pageNow 当前页数
	 * @param pageCode 要显示的页码数,以英文逗号隔开,如:1,2,3,4
	 * @param seachData 搜索的数据,如果没有搜索就传""    name=9&url=9
	 */
	function page(pageCount,pageNow,pageCode,seachData){
		var pageContent = "";
			if(parseInt(pageNow)>1){
				pageContent = "<a class='layui-btn layui-btn-sm' href='role.jsp?"+seachData+"&pageNow=1'>首页</a>"+
				"<a class='layui-btn layui-btn-sm' href='role.jsp?"+seachData+"&pageNow="+(pageNow-1)+"'>上一页</a>";
			}else if(parseInt(pageNow)==1){
				pageContent = "&nbsp;";
			}
			if (null != pageCode && "" != pageCode) {
				var pageCodeArr = pageCode.split(",");
				for (var i in pageCodeArr) {
					if (pageCodeArr[i] !== "") {
						pageContent +=  "<a href='role.jsp?"+seachData+"&pageNow="+pageCodeArr[i]+"'><span class='layui-badge-rim' style='height:27px;width:20px;line-height:28px;background-color:#fff;color:#000;' id='ye"+pageCodeArr[i]+"'>"+pageCodeArr[i]+"</span></a>" ;
					}
				}
			}
			if(parseInt(pageNow)!=parseInt(pageCount)){
				
			pageContent += "<a class='layui-btn layui-btn-sm' href='role.jsp?"+seachData+"&pageNow="+(parseInt(pageNow)+1)+"'>下一页</a>"+
					"<a class='layui-btn layui-btn-sm' href='role.jsp?"+seachData+"&pageNow="+pageCount+"'>尾页</a>";
			}else{
				pageContent += "&nbsp;";
			}
			pageContent += "&nbsp;&nbsp;&nbsp;&nbsp;<a class='layui-btn layui-btn-sm'>第["+pageNow+"]页</a>" +
					"<a class='layui-btn layui-btn-sm'>共["+pageCount+"]页</a>";
			if(parseInt(pageCount) > 0){
				$("#page").show();
				$("#page").html(pageContent);
			}else{
				$("#page").hide();
			}
	}