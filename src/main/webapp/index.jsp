<html>
<head>
<title>越努力越幸运</title>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/views/css/ghhychina/login.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/views/css/easyui/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/views/css/easyui/icon.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/views/js/jquery.easyui.min.js"/></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/views/js/easyui-lang-zh_CN.js"/></script>
<script type="text/javascript">
	$(function(){
		var input = document.getElementById('input_test');
		input.onfocus = function() {
			if (this.value == '请输入用户名') {
				this.value = '';
			}
			;
		};

		input.onblur = function() {
			if (!this.value) {
				this.value = '请输入用户名';
			}
			;
		};

		document.getElementById('input_test1').onfocus = function() {
				document.getElementById('input_test1').style.display = "none"; 
				document.getElementById("input_test11").style.display = "";
				document.getElementById("input_test11").focus();
		};

		document.getElementById("input_test11").onblur = function() {

			if (!this.value) {
				document.getElementById('input_test11').style.display = "none"; 
				document.getElementById("input_test1").style.display = "";
			}
			;
		};

		var input = document.getElementById('input_test2');
		input.onfocus = function() {
			if (this.value == '请输入验证码') {
				this.value = '';
				this.maxlength = '4';
			}
			;

		};

		input.onblur = function() {
			if (!this.value) {
				this.value = '请输入验证码';
				this.maxlength = '6';
			}
			;
		};

	});
</script>
<script type="text/JavaScript">

</script>
</head>

<body>

	<div class="main">
		<div class="main_tbj">

			<div class="main_tbj_top"></div>
			<div class="main_tbj_top2">
			</div>
			<div class="main_tbj_n">
				<div class="main_dl">
					<div class="dlxx">
						<img src="./views/img/index_06.png" width="262"
							height="79" />
					</div> 
					<form id="loginForm" class="formList clearfix"
						action="/z/toMain.do" method="post">
						<div class="dlxx_name">
							<input type="text" class="easyui-validatebox" id="input_test"
								name="username" value="请输入用户名" data-options="required:true"
								autocomplete="off" />
						</div>
						<div class="dlxx_name">
							<input type="text" class="easyui-validatebox" id="input_test1"
								value="请输入密码" data-options="required:true"
								onkeydown="LoginSubmit()" /> <input type="password"
								class="easyui-validatebox" id="input_test11" name="password"
								data-options="required:true" style="display: none;" />
						</div>
						<div class="dlxx_yzm">
							<input type="text" class="easyui-validatebox" id="input_test2"
								value="请输入验证码" maxlength="4" name="securityCode"
								onkeydown="LoginSubmit(event)" autocomplete="off" />
						</div>
						<div class="dlxx_yzm_img">
							<img src='./views/img/index_yzm.png' width="93" height="37" /> 
							<!--  <img id="imgObj" class="check" align="middle" alt="看不清，换一张"
								title="看不清，换一张" src="/securityCode.html"
								onclick="changeImg()" width="93" height="37"
								style="float: left;" /> 
								-->
								
								<span id="securityCodeSpan"
								style="color: red; text-align: center; padding: 5px 8px; font-size: 16px; float: left; display: block;">
								<c:if
									test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}">
	                   		
						</c:if>
							</span>
						</div>
						<div class="dl">
							<a href="javascript:;" onclick="sub()"></a>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="d">
			<div class="dh">
				<div class="dh_box">
					<div class="dh_box_1">
					</div>
					<div class="x">&nbsp;</div>
				</div>
			</div>
		</div>
	</div>

	<script>
    	
    	function sub(){
    		document.forms[0].submit();
    		//$('#loginForm').submit();
    	}
    	
    	$('#loginForm').submit(function(){
    		if(!$('#loginForm').form('validate')){
    			return false;
    		}
			var code = $("#input_test2").val();
			if(code == null || code == ""){
				$("#securityCodeSpan").html("验证码不能为空");
				return false;
			}
			
			var flag = null;
			$.ajax({
				type : 'POST',
				url : "<c:url value='/' />validSecurityCode.html",
				data : {
					securityCode : code
				},
				dataType : 'text',
				async:false,
				cache : false,
				traditional : true,
				success : function(data) {
					flag = data;	
				}
			});
			if(flag == '0'){
				$("#securityCodeSpan").html("验证码错误");
				return false;
			}
			
    	});
		function clearForm() {
			$('#loginForm').form('clear');
		}
		
		function changeImg(){
			$("#imgObj").attr("src",$("#imgObj").attr("src")+"?temp="+new Date());
		}
		
		//首页输入密码后默认回车提交登录事件
		function LoginSubmit(evt) {
			if (evt.keyCode == 13) {
				evt.keyCode = 9;
				evt.returnValue = false;
				sub();
			}
		}
	</script>
</body>
</html>
