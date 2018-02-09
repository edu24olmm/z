
<html>
<head>
<%
java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
     "yyyy-MM-dd HH:mm:ss");
   java.util.Date currentTime = new java.util.Date();
   String time = simpleDateFormat.format(currentTime).toString();   //放到页面的head中}
   %>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/views/css/easyui/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/views/css/easyui/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/views/css/tencr.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/views/css/main.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/views/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/views/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/views/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/views/js/Constant.js"></script>



<title>越努力越幸运</title>
<script type="text/javascript">
	$(function(){
// 		open1("首页", "../toDo.html");
	});

    function open1(plugin, url){
        if ($('#tt').tabs('exists', plugin)){
            $('#tt').tabs('select', plugin);
            var tab = $('#tt').tabs('getSelected');
            $('#tt').tabs('update', {
            	 tab: tab,
                 options: {
                	 title:plugin,
                     content: '<iframe src="'+ url+ '" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>'
                 }
            });
            
        } else {
        	$('#tt').tabs('add',{
                title:plugin,
                content: '<iframe src="'+ url+ '" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>',
                closable:true,
                extractor:function(data){
                    return data;
                }
            });
        }
    }
    
    /**
    * 时间对象的格式化
    */
	Date.prototype.format = function(format) {
		format="yyyy-MM-dd hh:mm:ss";
		var o = {
			"M+" : this.getMonth() + 1,
			"d+" : this.getDate(),
			"h+" : this.getHours(),
			"m+" : this.getMinutes(),
			"s+" : this.getSeconds(),
			"q+" : Math.floor((this.getMonth() + 3) / 3),
			"S" : this.getMilliseconds()
		};

		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}

		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	}
</script>
<script type="text/javascript">
history.forward();
</script>
</head>
<body class="easyui-layout" style="text-align: left;">
	<div data-options="region:'north'" style="background: #FFF;height:100px">
		<div class="top">
            <a class="logo" href=""><img src='<%=request.getContextPath()%>/views/img/ghhychina/logo.png'/></a>
            <div class="topRt clearfix">
                <span class="welcome">欢迎你，小伙伴!</span>
<!--                 <a href="#" class="reKey" onclick="open1('修改密码', '/modPwdInput.html')">修改密码</a> -->
                <p class="date" id="datep"><%=time %></p>
                <input class="exit" type="submit" value="退出" onclick="javascript:window.location.href='../../../ghhychina.login.html'"/>
            </div>
        </div>
	</div>

	<div data-options="region:'west',split:true,title:'功能导航'" style="width: 250px; padding: 5px;">
		<ul class="easyui-tree">
			<li iconCls="icon-shouye"><a href="#" onclick="open1('首页','')">首页</a></li>
			<li iconCls="icon-jijuguanli"><span>任务管理</span>
				<ul>
					<li iconCls="icon-qudaoguanli" style="display:none;">
						<a href="#" onclick="open1('任务管理','/z/toTaskList.do')">任务管理</a></li>
				</ul>
			</li>
			<li iconCls="icon-quanxianguanli"><span>信息查询</span>
				<ul>
					<li iconCls="icon-rizhiguanli" style="display:none;">
						<a href="#" onclick="open1('信息查询','/z/toSelect.do')">信息查询</a></li>
				</ul>
			</li>
		</ul>
	</div>

	<div data-options="region:'south',border:false" style="height:30px; background: #A9FACD; padding: 5px;">Copyright @ 北京刷量刘哥　版权所有  <spring:message code="ghhychina.icp"></spring:message></div>
	<div data-options="region:'center'">
		<div id="tt" class="easyui-tabs" fit="true" border="false" plain="true"></div>
	</div>
</body>
</html>
