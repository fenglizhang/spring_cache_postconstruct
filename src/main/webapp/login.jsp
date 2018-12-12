<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录 -管理系统</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
	var basepath='<%=basePath%>';
</script>
<script type="text/javascript"
	src="<%=basePath%>static/js/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>static/js/easyui/base64.js"></script>
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>static/js/easyui/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>static/js/easyui/themes/icon.css" />

</head>
<body>
	<div style="margin-left: 400px; margin-top: 250px;">
		<div>
			<form id="LoginForm" method="post"	action="<%=basePath%>login/loginAdmin">
				<input type="hidden" name="username" id="userNamela"> 
				<input type="hidden" name="password" id="pwdla">
			</form>
			<form>
				<table class="tableStyle">
					<tr>
						<td class="title">用户名：</td>
						<td class="content"><input type="text" class="easyui-textbox"
							id="userName" maxlength="18" /></td>
					</tr>
					<tr>
						<td class="title">密&nbsp;&nbsp;&nbsp;码：</td>
						<td class="content"><input type="password"
							class="easyui-textbox" id="pwd" /></td>
					</tr>
					<tr>
						<td><font color="red"> ${error}</font></td>
					</tr>
				</table>
				<br />
				<div class="serbut" style="width: 170px; text-align: right">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-ok" onclick="login()">登陆</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-clear" onclick="clearForm()">清空</a>
				</div>
			</form>
		</div>
		<div style="margin-top: 200px;">
			<font color="red">管理系统</font> Version 1.0 由wool科技提供技术支持！ <br />
		</div>
	</div>
	<script type="text/javascript">
		function login() {
			var username = $("#userName");
			var password = $("#pwd");
			var base = new Base64();//加密算法对象。
			if (username.val() == "") {
				$.messager.alert("提示", "请输入用户名！");
				username.focus();
				return false;
			} else if (password.val() == "") {
				$.messager.alert("提示", "请输入密码！");
				password.focus();
				return false;
			} else {
				var name = base.encode($("#userName").val());//对用户名加密
				var pwd = base.encode($("#pwd").val());//密码加密
				//alert(name+"---"+pwd);
				//将加密后的数据付给input,并提交
				$("#userNamela").val(name);
				$("#pwdla").val(pwd);
				$("#LoginForm").submit();
			}
		}

		$(window).keydown(function(event) {
			var code = event.keyCode || event.which || event.charCode;
			if (code == 13) {
				login();
			}
		});
	</script>

</body>
</html>