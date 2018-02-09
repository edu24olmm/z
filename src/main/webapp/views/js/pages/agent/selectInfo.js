$(function() {
	$('#keepList').datagrid({
		url : '/z/getKeepList.do',
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
			title : '描述',
			align : 'left',
			width : 1800
		}
		] ] 
	});
})
