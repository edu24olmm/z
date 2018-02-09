<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="minimum-scale=1.0,maximum-scale=1.0,user-scalable=no, initial-scale=1"; charset=utf-8">

<title>map</title>
<style>
* {
	padding: 0;
	margin: 0;
}

.tel_box {
	width: 300px;
	margin: 20px auto 0;
}

.tel_input {
	width: 298px;
	height: 40px;
	border: 1px solid #58698d;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	margin-top: 10px;
	font-size: 16px;
}

.tel_btn {
	width: 298px;
	height: 40px;
	background: #58698d;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	text-align: center;
	line-height: 40px;
	color: #fff;
	margin-top: 15px;
}

.tel_tips {
	margin-top: 10px;
	color: #369;
	text-align: center;
	font-size: 58px;
}
</style>
<script type="text/javascript">
	//工具类-判断是否为空
	function isEmpty(info) {
		if (info == null || info == undefined || ("" + info) == "") {
			return true;
		}
		return false;
	}
	//工具类-根据手机号得到对应的运营商
	function getMobileType(phone) {
		if (isMobile(phone))
			return "01";
		else if (isUnicom(phone))
			return "02";
		else if (isTelecom(phone))
			return "03";
		else
			return "";
	}
	//工具类-判断是否为移动
	/**
	 * weizhang 20130718 新增 移动183号段 校验
	 */
	function isMobile(str) {
		var regu = /^1(34|(3[5-9]|47|5[0-2]|5[789]|8[23478]))\d{8}$/;
		return regu.test(str);
	}

	// 工具类-判断是否为电信
	function isTelecom(str) {
		var regu = /^(133|153|180|181|189)\d{8}$/;
		return regu.test(str);
	}

	// 工具类-判断是否为联通
	function isUnicom(str) {
		var regu = /^(13[0-2]|15(5|6)|185|186|145)\d{8}$/;
		return regu.test(str);
	}

	function sub() {
		var num = document.getElementById("phonenum").value;
		if (num == '手机号码' || num == null || num == '') {
			alert("请输入有效的手机号");
			return;
		}
		if (isEmpty(getMobileType(num))) {
			alert("请输入有效的手机号");
			return false;
		}
		document.verif.submit();
	}
</script>
</head>
<body>
<!-- 	<form action="showsaleslip.do" method="post" id="verif" name="verif"> -->
		<div class="tel_box">
			<p class="tel_title">phoneType:<%=request.getAttribute("phoneType") %></p>
			<br />
			<p class="tel_title">isScreenOn:<%=request.getAttribute("isScreenOn") %></p>
			<br />
			<p class="tel_title">time:<%=request.getAttribute("time") %></p>
			<br />
			<a href="<%=request.getAttribute("loStr")%>">
			<button>
				<p class="tel_tips">地图标点</p>
			</button>
			</a>
		</div>
<!-- 	</form> -->
</body>
</html>