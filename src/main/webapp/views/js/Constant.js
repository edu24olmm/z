/**
 * 添加门店及pos需要的全局变量
 */
var fieldsetShopIndex = 0;
var fieldsetPosIndex = 1;

/**
 * 时间对象的格式化
 */
Date.prototype.format = function(format) {
	format = "yyyy-MM-dd hh:mm:ss";
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
};
Date.prototype.formatYYYYMMDD = function(format) {
	format = "yyyy-MM-dd";
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
};
Date.prototype.formatHHMM = function(format) {
	format = "yyyy-MM-dd hh:mm:ss";
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
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
	return format.substring(11, 16);
};

Date.prototype.formatYYYYMMDDHHMM = function(format) {
	format = "yyyy-MM-dd hh:mm:ss";
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
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
	return format.substring(0, 16);
};
/**
 * 格式化金额
 * 
 * @param num
 *            要格式化的金额(单位:分)
 * @param n
 *            保留小数位
 * @returns
 */
function formatNum(num, digit) {
	num = num * 0.01;
	num = String(num.toFixed(digit));
	var re = /(-?\d+)(\d{3})/;
	while (re.test(num))
		num = num.replace(re, "$1,$2");
	return num;
}

function formatNumTo(num){
	return formatNum(num, 2);
}

//扩展easyui表单的验证
$.extend($.fn.validatebox.defaults.rules, {
	//验证汉字
	CHS: {
		validator: function (value) {
			return /^[\u0391-\uFFE5]+$/.test(value);
		},
		message: '只能输入汉字'
	},
	//固定电话或手机号
	mobileOrTele: {//value值为文本框中的值
		validator: function (value) {
			var reg = /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;
			return reg.test(value);
		},
		message: '输入固定电话或手机号码格式不准确.'
	},
	//移动手机号码验证
	mobile: {//value值为文本框中的值
		validator: function (value) {
//			var reg = /^1[3|4|5|8|9]\d{9}$/;
			var reg = /^1\d{10}$/;
			return reg.test(value);
		},
		message: '输入手机号码格式不准确.'
	},
	checkName : {//value值为文本框中的值
		validator: function (value) {
			var reg = /^1[3|4|5|8|9]\d{9}$/;
			var regx = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(reg.test(value) || regx.test(value)){
				return true;
			}
			return false;
		},
		message: '请输入正确的手机号或邮箱号.'
	},
	checkMobile : {//value值为文本框中的值
		validator: function (value) {
			var reg = /^1[3|4|5|8|7|9]\d{9}$/;
			if(reg.test(value)){
				return true;
			}
			return false;
		},
		message: '请输入正确的手机号.'
	},
	checkEmail : {//value值为文本框中的值
		validator: function (value) {
			var regx = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(regx.test(value)){
				return true;
			}
			return false;
		},
		message: '请输入正确的邮箱号.'
	},
	//数字验证
	numberV : {
		validator: function (value) {
			var reg = /^\d{1,10}$/;
			return reg.test(value);
		},
		message: '输入编号格式不准确.请输入数字类型'
	},
	numberVs : {
		validator: function (value) {
			var reg = /^[\d]{7,7}$/;
			return reg.test(value);
		},
		message: '请输入长度为7的数字类型'
	},
	//国内邮编验证
	zipcode: {
		validator: function (value) {
			var reg = /^[1-9]\d{5}$/;
			return reg.test(value);
		},
		message: '邮编必须是非0开始的6位数字.'
	},
	//用户账号验证(只能包括 _ 数字 字母) 
	account: {//param的值为[]中值
		validator: function (value, param) {
			if (value.length < param[0] || value.length > param[1]) {
				$.fn.validatebox.defaults.rules.account.message = '用户名长度必须在' + param[0] + '至' + param[1] + '范围';
				return false;
			} else {
				if (!/^[\w]+$/.test(value)) {
					$.fn.validatebox.defaults.rules.account.message = '用户名只能数字、字母、下划线组成.';
					return false;
				} else {
					return true;
				}
			}
		}, message: ''
	},
	telphone: {   
        validator: function(value,param){   
        	if(/^(\d{3}|\d{4})-(\d{8}|\d{7})$/.test(value)){
        		return true;
        	}else{
        		$.fn.validatebox.defaults.rules.account.message = '号码格式不正确,如071(0)-1234567(8)';
        		 return false;   
        	}
           
        },   
        message: '号码格式不正确,如071(0)-1234567(8)'  
    },
	money: {   
		validator: function(value,param){   
			return /^\d{0,15}\.{0,1}(\d{1,2})?$/.test(value);
		},   
		message: '最多两位小数'  
	},
	cardNo: {   
		validator: function(value,param){
			val = value.length;
			var isNum = isNaN(value);
			var istrue = true;
			if(isNum == true){
				istrue = false;
			}
			return val >= 9 && val <= 22 && istrue;
		},   
		message: '结算账号应为9-22位的数字.'  
	},
	IDNumber: {   
		validator: function(value,param){   
			val = value.length;
			return val == 15 || val == 18;
		},   
		message: '身份证号应为15或18位.'  
	},
	areaCode: {
		validator: function (value,param) {
			var reg = /^\d{4}$/;
			//alert("areaCode valid:"+reg.test(value));
			return reg.test(value);
		},
		message: '请输入长度为4的数字类型区号,不足4位的前面补0'
	},
	userpassword: {
		validator: function (value,param) {
			var reg = /^(?!\d+$)(?!\D+$)[a-zA-Z\d]+$/;
			return reg.test(value);
		},
		message: '密码必须包含数字和字母且长度为8到20'
	}
});

var config = {
		companyName : '',
		companyFullName : '',
		loadMsg : '数据加载中，请稍候',
		pageList : [ 50, 100, 150, 200, 250 ],
		pageSize : 50,
		tableHeight : 400,
		numValidType : 'length[1,6]',
		inputHeight : '20px'
	};

function compareDate(start,end){
	startDate=$(start).datetimebox("getValue");
	endDate=$(end).datetimebox("getValue");
	startDate = startDate.replace(/-/g, "/");
	endDate = endDate.replace(/-/g, "/");
	startDate = new Date(startDate);
	endDate = new Date(endDate);
	var time=startDate.getTime()-endDate.getTime();
	if(time > 0){//如果起始日期大于截止起始日期则返回false
	alert("起始日期不能大于截止日期");
		return false;
	}
	return true;
}


function compareDate(start,end,name){
	startDate=$(start).datetimebox("getValue");
	endDate=$(end).datetimebox("getValue");
	startDate = startDate.replace(/-/g, "/");
	endDate = endDate.replace(/-/g, "/");
	startDate = new Date(startDate);
	endDate = new Date(endDate);
	var time=startDate.getTime()-endDate.getTime();
	if(time > 0){//如果起始日期大于截止起始日期则返回false
	alert(name + "起始日期不能大于截止日期");
		return false;
	}
	return true;
}

var banklist = [{
	id : 2,
	name : "中国银行"
}, {
	id : 3,
	name : "中国建设银行"
}, {
	id : 4,
	name : "中国工商银行"
}, {
	id : 5,
	name : "中国农业银行"
}, {
	id : 6,
	name : "华夏银行"
}, {
	id : 7,
	name : "招商银行"
}, {
	id : 8,
	name : "交通银行"
}, {
	id : 9,
	name : "中国光大银行"
}, {
	id : 10,
	name : "兴业银行"
}, {
	id : 11,
	name : "中国民生银行"
}, {
	id : 12,
	name : "中国进出口银行"
}, {
	id : 13,
	name : "深圳发展银行"
}, {
	id : 14,
	name : "上海浦东发展银行"
}, {
	id : 15,
	name : "广州市商业银行"
}, {
	id : 16,
	name : "北京银行"
}, {
	id : 17,
	name : "石家庄市商业银行"
}, {
	id : 18,
	name : "厦门国际银行"
}, {
	id : 19,
	name : "重庆市商业银行"
}, {
	id : 20,
	name : "哈尔滨市商业银行"
}, {
	id : 21,
	name : "西安市商业银行"
}, {
	id : 22,
	name : "武汉市商业银行"
}, {
	id : 23,
	name : "南京市商业银行"
}, {
	id : 24,
	name : "长沙市商业银行"
}, {
	id : 25,
	name : "成都市商业银行"
}, {
	id : 26,
	name : "郑州市商业银行"
}, {
	id : 27,
	name : "济南市商业银行"
}, {
	id : 28,
	name : "湖北银行"
}];
var merbanklist = [{
	id : 2,
	name : "中国银行"
}, {
	id : 3,
	name : "中国建设银行"
}, {
	id : 4,
	name : "中国工商银行"
}, {
	id : 5,
	name : "中国农业银行"
}, {
	id : 28,
	name : "湖北银行"
}];
var monthlist = [{
	id : 0.5,
	name : '0.5'
},{
	id : 1,
	name : '1'
},{
	id : 2,
	name : '2'
},{
	id : 3,
	name : '3'
},{
	id : 4,
	name : '4'
},{
	id : 5,
	name : '5'
},{
	id : 6,
	name : '6'
}];
var legalcertTypelist = [{
	id : '0',
	name : "身份证"
},{
	id : '1',
	name : '户口本'
},{
	id : '2',
	name : '护照'
},{
	id : '3',
	name : '其他'
}];
var merAttributelist = [{
	id : '0',
	name : '一般商户'
},{
	id : 1,
	name : '优质商户'
},{
	id : 2,
	name : '连锁商户'
}];
var yesnolist = [{
	id : '0',
	name : '是'
},{
	id : 1,
	name : '否'
}];
var gradelist = [{
	id : 1,
	name : '5(差)'
},{
	id : 2,
	name : '4'
},{
	id : 3,
	name : '3'
},{
	id : 4,
	name : '2'
},{
	id : 5,
	name : '1(优)'
}];
var expandTypelist = [{
	id : '0',
	name : '直营'
}, {
	id : 1,
	name : '代理商'
},{
	id : 2,
	name : '主动上门客户'
},{
	id : 3,
	name : '伙伴拓展'
}];
var merProlist = [{
	id : '0',
	name : '自然人'
}, {
	id : 1,
	name : '个体户'
}, {
	id : 2,
	name : '企业'
}
];
var merTypelist = [{
	id : '0',
	name : '普通商户'
}, {
	id : 1,
	name : '连锁商户'
},
/*{
	id : 2,
	name : '增值业务商户'
},*/{
	id : 3,
	name : '增值业务大商户'
}];
var clearAccTypelist = [{
	id : '0',
	name : '对公'
}, {
	id : 1,
	name : '对私'
}];
var openDistrictlist = [{
	id : '0',
	name : '商业区'
}, {
	id : 1,
	name : '工业区'
},{
	id : 2,
	name : '住宅区'
}];

var openArealist = [{
	id : '0',
	name : '城区'
}, {
	id : 1,
	name : '郊区'
},{
	id : 2,
	name : '边远地区'
}];
var openProlist = [{
	id : '0',
	name : '自有'
}, {
	id : 1,
	name : '租用'
}];
/**
 * 图片放大函数
 */
function big(imgId){
	var id = "#" + imgId;
	var imgWidth=$(id).width();
	var imgHeight=$(id).height();
	//重新设置img的width和height
	$(id).width(imgWidth * 1.1);
	$(id).height(imgHeight * 1.1);
	//让图片居中显示
	//var boxWidth = $(id).parent().width();
	//var margin=(boxWidth-$(id).width())/2;
	//$(id).css("margin-left",margin);
	var boxHieght = 500;
	var margin=(boxHieght-$(id).height())/2;
	$(id).css("margin-top",10);
}
/**
 * 图片缩小函数
 */
function small(imgId){
	var id = "#" + imgId;
	var imgWidth=$(id).width();
	var imgHeight=$(id).height();
	//重新设置img的width和height
	$(id).width(imgWidth * 0.9);
	$(id).height(imgHeight * 0.9);
	//让图片居中显示
	//var boxWidth = $(id).parent().width();
	//var margin=(boxWidth-$(id).width())/2;
	//$(id).css("margin-left",margin);
	var boxHieght = 500;
	var margin=(boxHieght-$(id).height())/2;
	$(id).css("margin-top",10);
}

var clearingFormList = [{
	id : '0',
	name : "提现"
}, {
	id : 1,
	name : "T+N结算"
}];
var isSetRisk = [{
	id : '0',
	name : "否"
}, {
	id : '1',
	name : "是"
}];

$.ajaxSetup({ 
	contentType:"application/x-www-form-urlencoded;charset=utf-8", 
	complete:function(XMLHttpRequest,textStatus){ 
		var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
		if(sessionstatus=="timeout"){ 
			// 如果超时就处理 ，指定要跳转的页面
			window.location.replace(ctx + "/timeout.jsp"); 
		} 
	} 
});


function getNowDate(){
		var curr_time = new Date();
		var year = curr_time.getFullYear();
		var month = curr_time.getMonth()+1 + "";
		var day = curr_time.getDate() + "";
		if(month.length <= 1){
		   month = "0"+month;
		}
		if(day.length <= 1){
			day = "0"+day;
		}
		var strDate = year + "-" + month +"-"+day;
		
		return strDate;
}


