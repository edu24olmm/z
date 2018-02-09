$(function() {
	$('#agentsList').datagrid({
		url : '/z/getTaskList.do',
		method:'post',
		fit : true,// 高度自动充满
		cache : false,
		dataType: 'jsonp',//here
		autoRowHeight : true,
		striped : true,
		remoteSort : false,
		singleSelect : true,
		showFooter : false,
		pagination : true,
		pagePosition : 'bottom',
		pageNumber : 1,
		pageSize: config.pageSize,
		pageList: config.pageList,
		rownumbers : true,
		toolbar : '#selectAgentsBar',
		columns : [ [
		 {
			field : 'id_',
			title : 'ID',
			align : 'center',
			width : 30
		}, {
			field : 'taskName',
			title : '任务描述',
			align : 'center',
			width : 180
		}, {
			field : 'topOfEveryDay',
			title : '每日最高限额',
			align : 'center',
		}, {
			field : 'registerRate',
			title : '注册率',
			align : 'center',
			width : 47
		}, {
			field : 'keep1',
			title : 'keep1',
			align : 'center',
			width : 47
		}, {
			field : 'keep2',
			title : 'keep2',
			width : 47,
			align : 'center'
		}, {
			field : 'keep3',
			title : 'keep3',
			width : 47,
			align : 'center'
		}, {
			field : 'keep4',
			title : 'keep4',
			width : 47,
			align : 'center'
		}, {
			field : 'keep5',
			title : 'keep5',
			align : 'center',
			width : 47
		}, {
			field : 'keep6',
			title : 'keep6',
			align : 'center',
			width : 47
		}, {
			field : 'keep7',
			title : 'keep7',
			align : 'center',
			width : 47
		}, {
			field : 'keep8',
			title : 'keep8',
			align : 'center',
			width : 47
		}, {
			field : 'keep9',
			title : 'keep9',
			align : 'center',
			width : 47
		}, {
			field : 'keep10',
			title : 'keep10',
			align : 'center',
			width : 47
		}, {
			field : 'keep11',
			title : 'keep11',
			align : 'center',
			width : 47
		}, {
			field : 'keep12',
			title : 'keep12',
			align : 'center',
			width : 47
		}, {
			field : 'keep13',
			title : 'keep13',
			align : 'center',
			width : 47
		}, {
			field : 'keep14',
			title : 'keep14',
			align : 'center',
			width : 47
		}, {
			field : 'keep15',
			title : 'keep15',
			align : 'center',
			width : 47
		}, {
			field : 'keep16',
			title : 'keep16',
			align : 'center',
			width : 47
		}, {
			field : 'keep17',
			title : 'keep17',
			align : 'center',
			width : 47
		}, {
			field : 'keep18',
			title : 'keep18',
			align : 'center',
			width : 47
		}, {
			field : 'keep19',
			title : 'keep19',
			align : 'center',
			width : 47
		}, {
			field : 'keep20',
			title : 'keep20',
			align : 'center',
			width : 47
		}, {
			field : 'keep21',
			title : 'keep21',
			align : 'center',
			width : 47
		}, {
			field : 'keep22',
			title : 'keep22',
			align : 'center',
			width : 47
		}, {
			field : 'keep23',
			title : 'keep23',
			align : 'center',
			width : 47
		}, {
			field : 'keep24',
			title : 'keep24',
			align : 'center',
			width : 47
		}, {
			field : 'keep25',
			title : 'keep25',
			align : 'center',
			width : 47
		}, {
			field : 'keep26',
			title : 'keep26',
			align : 'center',
			width : 47
		}, {
			field : 'keep27',
			title : 'keep27',
			align : 'center',
			width : 47
		}, {
			field : 'keep28',
			title : 'keep28',
			align : 'center',
			width : 47
		}, {
			field : 'keep29',
			title : 'keep29',
			align : 'center',
			width : 47
		}, {
			field : 'keep30',
			title : 'keep30',
			align : 'center',
			width : 47
		}, {
			field : 'keep31',
			title : 'keep31',
			align : 'center',
			width : 47
		}, {
			field : 'registerRate',
			title : 'registerRate',
			align : 'center',
			width : 47
		}
		] ] 
	});
	
	$("#updateAgents").click(function() {
		$('#updateUserFm').submit();
	});
	
	
	$("#updateUserFm").form({
		url : '/z/updateTask.do',
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(data) {
			if (data != null) {
				$('#upateUserDiv').dialog('close'); // close the dialog
				$('#agentsList').datagrid('reload');
				$.messager.show({
					title : '操作提示',
					msg : "修改成功!"
				});
			} else {
				$.messager.alert("操作提示", "修改失败，请稍后修改！", "info");
			}
		}
	});

	$("#deleteKeep").click(function() {
		var row = $('#agentsList').datagrid('getSelected');
		if (row) {
			 $.messager.confirm('删除', '确定要删除吗？',function(r){
				 if(r){
					 var url = "/z/deleteKeep.do";
					 var data = {
						id_ : row.id_	 
					 };
					 $.post(url,data,function(data){
						 if(true){
							 $('#agentsList').datagrid('reload'); 
							 $.messager.alert('删除','删除,成功','info'); 
						 }else{
							 $.messager.alert('删除','删除,失败!','info');
						 }
					 });
				 }
			 });
		}else{
			$.messager.alert('删除','请先选择','info'); 
		}
	});
	
	$("#deleteTask").click(function() {
		var row = $('#agentsList').datagrid('getSelected');
		if (row) {
			 $.messager.confirm('删除', '确定要删除吗？',function(r){
				 if(r){
					 var url =  "/z/deleteTask.do";
					 var data = {
						id_ : row.id_	 
					 };
					 $.post(url,data,function(data){
						 if(true){
							 $('#agentsList').datagrid('reload'); 
							 $.messager.alert('删除','删除,成功','info'); 
						 }else{
							 $.messager.alert('删除','删除,失败!','info');
						 }
					 });
				 }
			 });
		}else{
			$.messager.alert('删除','请先选择','info'); 
		}
	});
	
//	生成结算数据
	$("#addSellement").click(function() {
		var row = $('#agentsList').datagrid('getSelected');
		
		if (row) {
			
			if(row.price<=0){
				$.messager.alert('出错金额不正确','请修改金额！','error'); 
				return;
			}
			
			$.messager.confirm('结算', '确定要生成结算数据吗？',function(r){
				if(r){
					var url =  "/z/addSellement.do";
					var data = {
							id_ : row.id_,	 
							taskName : row.taskName,	 
							price : row.price	 
					};
					$.post(url,data,function(data){
						if(true){
							$('#agentsList').datagrid('reload'); 
							$.messager.alert('结算','生成,成功','info'); 
						}else{
							$.messager.alert('结算','生成,失败!','info');
						}
					});
				}
			});
		}else{
			$.messager.alert('结算','请先选择','info'); 
		}
	});

	
	//保存事件
	$("#saveAgents1").click(function() {
		$('#addUserFm').form('submit',{
		    url:'/z/addTask.do',   
		    onSubmit: function(){   
		       return $(this).form('validate');
		    },   
		    success:function(data){
		    	
		        if(data!=null){
                    $('#agentsList').datagrid('reload');    // reload the MerPlugin data
                    $.messager.show({
						title : '操作提示',
						msg : "添加成功!"
					});
                    $('#addUserDiv').dialog('close');
		        }else{
		        	$.messager.alert('添加','添加失败，请稍后重试','info');
		        }
		    }
		});
	});
	
	$.extend($.fn.validatebox.defaults.rules, {  
		moblie: {   
	        validator: function(value,param){   
	            return /^((13[0-9])|(15[0-9])|(18[0-9]))[0-9]{8}$/.test(value);   
	        },   
	        message: '手机号格式不正确'  
	    },
	    equals: {   
	        validator: function(value,param){  
	            return value < $(param[0]).val();   
	        },   
	        message: '有效期时间设置有误'  
	    },
	    checkNum: {   
	        validator: function(value,param){   
	            return /^\d+(\.\d+)?$/.test(value);   
	        },   
	        message: '请输入数字或小数'  
	    },
	    checkFax:{
	    	validator:function(value,param){
	    		return /^(\d{3}-\d{8})$|^(\d{4}-\d{7})$/.test(value);
	    	},
	    	message:'传真输入有误'
	    }, 
	    agentAccount : {
	    	validator : function(value){
	    		var reg = /^[0-9A-Za-z]+$/;
	    		if(reg.test(value)){
	    			return true;
	    		}else{
	    			return false;
	    		}
	    	},
	    	message : '登录名只能是字母或数字'
	    }
	});
	
});

function compareDate(start,end){
	startDate=$(start).datetimebox("getValue");
	endDate=$(end).datetimebox("getValue");
	startDate = startDate.replace(/-/g, "/");
	endDate = endDate.replace(/-/g, "/");
	startDate = new Date(startDate);
	endDate = new Date(endDate);
	var time=startDate.getTime()-endDate.getTime();
	if(time > 0){//如果起始日期大于截止起始日期则返回false
		$.messager.alert("操作提示", "起始日期不能大于截止日期!", "info");
		return false;
	}
	return true;
}

function initData(){
	var form = $('#addAgentsForm');
	$('#enddate0').datebox('setValue','2099-12-30');
	$('#enddate1').datebox('setValue','2099-12-30');
	$('#enddate2').datebox('setValue','2099-12-30');
	$('#enddate3').datebox('setValue','2099-12-30');
	
	form[0]['proid0'].value = form[0]['proid0'].defaultValue;
	form[0]['proid1'].value = form[0]['proid1'].defaultValue;
	form[0]['proid2'].value = form[0]['proid2'].defaultValue;
	form[0]['proid3'].value = form[0]['proid3'].defaultValue;
	form[0]['proname0'].value = form[0]['proname0'].defaultValue;
	form[0]['proname1'].value = form[0]['proname1'].defaultValue;
	form[0]['proname2'].value = form[0]['proname2'].defaultValue;
	form[0]['proname3'].value = form[0]['proname3'].defaultValue;

}


function pitchOn(thiz){
	if(thiz.checked)
	{
		//选中：获取id
		var startdate = '#startdate' + thiz.id;
		var enddate = '#enddate' + thiz.id;
		var countType = '#type' + thiz.id;
		var countValue = '#value' + thiz.id;
		var money='#money'+thiz.id;
		var nf = '#nf' + thiz.id;
		var form = $('#addAgentsForm');
		//var proid = 'proid'+ thiz.id;
		//var productname = 'proname'+thiz.id;
		form[0]['proid0'].value = form[0]['proid0'].defaultValue;
		form[0]['proid1'].value = form[0]['proid1'].defaultValue;
		form[0]['proid2'].value = form[0]['proid2'].defaultValue;
		form[0]['proid3'].value = form[0]['proid3'].defaultValue;
		form[0]['proname0'].value = form[0]['proname0'].defaultValue;
		form[0]['proname1'].value = form[0]['proname1'].defaultValue;
		form[0]['proname2'].value = form[0]['proname2'].defaultValue;
		form[0]['proname3'].value = form[0]['proname3'].defaultValue;
		
		$(startdate).datebox({
			disabled : false,
			required : true
		});
		$(enddate).datebox({
			disabled : false,
			required : true
		});
		$(countType).combobox({
			disabled : false,
			panelHeight : 'auto',
			required : true
		});
		$(countValue).numberbox({
			disabled : false,
			required : true,
			min:0,   
		    precision:2
		});
		if($(money)!=null){
			$(money).numberbox({
				disabled : false,
				required : true,
				min:0,   
			    precision:2
			});
		}
		if($(nf) != null)
		{
			$(nf).numberbox({
				disabled : false,
				required : true,
				min:0,
			    precision:2
			});
		}
	}
	if(!thiz.checked)
	{
		//选中：获取id
		var startdate = '#startdate' + thiz.id;
		var enddate = '#enddate' + thiz.id;
		var countType = '#type' + thiz.id;
		var countValue = '#value' + thiz.id;
		var money='#money'+thiz.id;
		var nf = '#nf' + thiz.id;
		$(startdate).datebox({
			disabled : true,
			required : false
		});
		$(enddate).datebox({
			disabled : true,
			required : false
		});
		$(countValue).numberbox({
			disabled : true,
			required : false,
			min:0,   
		    precision:2
		});
		$(countType).combobox({
			disabled : true,
			panelHeight : 'auto',
			required : false
		});
		if($(money)!=null){
			$(money).numberbox({
				disabled : true,
				required : false,
				min:0,   
			    precision:2
			});
		}
		if($(nf) != null)
		{
			$(nf).numberbox({
				disabled : true,
				required : false,
				min:0,   
			    precision:2
			});
		}
	}
}

function onLoadSuccess(){
	var target = $(this);
    var val = target.combobox("getValue");
    if(val == null || val == ''){
    	 var data = target.combobox("getData");
 	    var options = target.combobox("options");
 	    if(data && data.length>0){
 	        var fs = data[0];
 	        target.combobox("setValue",fs[options.valueField]);
 	    }
    }
}

function openAddAgents(){  
	$('#addUserFm').form('clear');  
    $('#addUserDiv').dialog('open').dialog('setTitle','添加账户');  
    $('#taskName').val("");
    $('#topOfEveryDay').val("656");
    $('#registerRate').val("0");
    $('#keep1').val("8");
    $('#keep2').val("30");
    $('#keep3').val("3");
    $('#keep4').val("2");
    $('#keep5').val("1");
    $('#keep6').val("2");
    $('#keep7').val("15");
    $('#keep8').val("2");
    $('#keep9').val("2");
    $('#keep10').val("1");
    $('#keep11').val("2");
    $('#keep12').val("1");
    $('#keep13').val("1");
    $('#keep14').val("8");
    $('#keep15').val("6");
    $('#keep16').val("1");
    $('#keep17').val("2");
    $('#keep18').val("2");
    $('#keep19').val("1");
    $('#keep20').val("2");
    $('#keep21').val("3");
    $('#keep22').val("1");
    $('#keep23').val("2");
    $('#keep24').val("2");
    $('#keep25').val("2");
    $('#keep26').val("3");
    $('#keep27').val("3");
    $('#keep28').val("7");
    $('#keep29').val("9");
    $('#keep30').val("8");
    $('#keep31').val("8");
    
    $("#defalutCheck").prop("checked",true);
}


function toUpdateUser(){
	$('#upateUserDiv').dialog('open').dialog('setTitle','修改');  
	var row = $('#agentsList').datagrid('getSelected'); 
	$("#updateUserFm").form("clear");
    if (row){  
        $('#updateUserFm').form('load',row);
        $('#upateUserDiv').dialog('open').dialog('setTitle','修改');  
    }else{
    	$.messager.alert('修改','请先选择要修改的信息'); 
    }
}
