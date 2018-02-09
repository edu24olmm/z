<!DOCTYPE html>
<html>
<head>
<title>代理商信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/views/css/easyui/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/views/css/easyui/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/views/css/tencr.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/views/css/main.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/views/js/jquery.min.js?versionnumber=20140609"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/views/js/jquery.easyui.min.js?versionnumber=20140609"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/views/js/easyui-lang-zh_CN.js?versionnumber=20140609"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/views/js/Constant.js?versionnumber=20140609"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/views/js/upload_js/swfupload.js?versionnumber=20140609"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/views/js/upload_js/swfupload.queue.js?versionnumber=20140609"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/views/js/upload_js/handlers.js?versionnumber=20140609"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/views/js/pages/agent/agents.js?versionnumber=20140609">
</script>
<style type="text/css">
.div_tabs {
	padding: 10px 30px;
	width: 100%;
}

.tdwidth {
	width: 170px;
	text-align: left;
}
</style>

</head>
<body>
	<div id="selectAgentsBar" style="display: none;">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add', plain:true" onclick="openAddAgents()" id="openAddAgents">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit', plain:true" onclick="toUpdateUser()" id="toUpdateUser">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit', plain:true"  id="deleteTask">删除任务并删除刷量信息</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit', plain:true"  id="deleteKeep">保留任务只删除刷量信息</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit', plain:true"  id="addSellement">生成结算数据</a>
	</div>

	<table id="agentsList" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<thead>
			<tr align="center">
			</tr>
		</thead>
	</table>


	<!-- 添加用户 -->
	   <div id="addUserDiv" class="easyui-dialog" style="width:450px;height:380px;padding:10px 20px"  
            closed="true" buttons="#addUserDiv-buttons">  
        <form id="addUserFm" method="post" novalidate>  
            <div class="fitem">  
                <label>任务描述:</label>  
                <input class="easyui-validatebox textbox" type="text" id="taskName" name="taskName" value="123"></input>
            </div>  
             <div class="fitem">  
                <label>每日最高限额:</label>  
                 <input class="easyui-validatebox textbox" type="text" id="topOfEveryDay" name="topOfEveryDay" validType="length[1,5]" value="656"></input>
            </div> 
            <div class="fitem">  
                <label>注册率:</label>  
                <input class="easyui-validatebox textbox" type="text" id="registerRate" name="registerRate" validType="length[1,3]"></input>
            </div>  
            <div class="fitem">  
                <label>keep1:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep1" name="keep1" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep2:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep2" name="keep2" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep3:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep3" name="keep3" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep4:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep4" name="keep4" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep5:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep5" name="keep5" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep6:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep6" name="keep6" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep7:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep7" name="keep7" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep8:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep8" name="keep8" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep9:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep9" name="keep9" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep10:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep10" name="keep10" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep11:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep11" name="keep11" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep12:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep12" name="keep12" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep13:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep13" name="keep13" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep14:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep14" name="keep14" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep15:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep15" name="keep15" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep16:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep16" name="keep16" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep17:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep17" name="keep17" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep18:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep18" name="keep18" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep19:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep19" name="keep19" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep20:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep20" name="keep20" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep21:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep21" name="keep21" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep22:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep22" name="keep22" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep23:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep23" name="keep23" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep24:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep24" name="keep24" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep25:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep25" name="keep25" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep26:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep26" name="keep26" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep27:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep27" name="keep27" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep28:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep28" name="keep28" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep29:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep29" name="keep29" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep30:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep30" name="keep30" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep31:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep31" name="keep31" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>price:</label>  
                <input class="easyui-validatebox textbox" type="text" id="price" name="price" validType="length[1,3]"></input>
            </div>  
        </form>  
		<div id="dlg-buttons1">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="saveAgents1">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addUserDiv').dialog('close')">取消</a>
		</div>
	</div>
	
	
	 <!-- 修改 -->
    <div id="upateUserDiv" class="easyui-dialog" style="width:420px;height:380px;padding:10px 20px"  
            closed="true" buttons="#upateUserDiv-buttons">  
        <form id="updateUserFm" method="post" novalidate>  
            <div class="fitem">  
                <label>任务描述:</label>  
                <input type="hidden" id="id_" name="id_" ></input>
                <input class="easyui-validatebox textbox" type="text" id="taskName" name="taskName" ></input>
            </div>  
             <div class="fitem">  
                <label>每日最高限额:</label>  
                 <input class="easyui-validatebox textbox" type="text" id="topOfEveryDay" name="topOfEveryDay" validType="length[1,5]" ></input>
            </div> 
             <div class="fitem">  
                <label>注册率:</label>  
                <input class="easyui-validatebox textbox" type="text" id="registerRate" name="registerRate" validType="length[1,3]"></input>
            </div> 
            <div class="fitem">  
                <label>keep1:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep1" name="keep1" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep2:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep2" name="keep2" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep3:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep3" name="keep3" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep4:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep4" name="keep4" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep5:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep5" name="keep5" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep6:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep6" name="keep6" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep7:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep7" name="keep7" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep8:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep8" name="keep8" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep9:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep9" name="keep9" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep10:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep10" name="keep10" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep11:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep11" name="keep11" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep12:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep12" name="keep12" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep13:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep13" name="keep13" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep14:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep14" name="keep14" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep15:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep15" name="keep15" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep16:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep16" name="keep16" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep17:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep17" name="keep17" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep18:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep18" name="keep18" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep19:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep19" name="keep19" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep20:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep20" name="keep20" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep21:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep21" name="keep21" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep22:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep22" name="keep22" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep23:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep23" name="keep23" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep24:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep24" name="keep24" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep25:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep25" name="keep25" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep26:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep26" name="keep26" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep27:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep27" name="keep27" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep28:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep28" name="keep28" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep29:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep29" name="keep29" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep30:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep30" name="keep30" validType="length[1,3]"></input>
            </div>  
             <div class="fitem">  
                <label>keep31:</label>  
                <input class="easyui-validatebox textbox" type="text" id="keep31" name="keep31" validType="length[1,3]"></input>
            </div>  
            <div class="fitem">  
                <label>price:</label>  
                <input class="easyui-validatebox textbox" type="text" id="price" name="price" validType="length[1,3]"></input>
            </div>  
        </form>  
        <div id="dlg-buttons1">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="updateAgents">修改</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#upateUserDiv').dialog('close')">取消</a>
		</div>
    </div>  
	
</body>
<script type="text/javascript" src='<%=request.getContextPath()%>/views/js/imageTrans/coos.ui.rotate.js?versionnumber=20140609'></script>
</html>
