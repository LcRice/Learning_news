<%@page import="com.neuedu.util.CookieUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" language="javaScript">
	function myfunction() {
		if (form1.username.value == "" && form1.password.value == "") {
			alert("用户名和密码不能为空！")
			return false;
		} else if (form1.username.value == "") {
			alert("用户名不能为空！")
			return false;
		} else if (form1.password.value == "") {
			alert("密码不能为空！")
			return false;
		} else {
			return true;
		}
	}
</script>
<style type="text/css">
div {
	width: 500px;
	height: 300px;
}

.main_login {
	position: absolute;
	top: 50%;
	left: 50%;
	-webkit-transform: translate(-50%, -50%);
	-moz-transform: translate(-50%, -50%);
	-ms-transform: translate(-50%, -50%);
	-o-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
}
</style>
</head>
<body>


<%
	String username = "";
	String password = "";
	
	
	String userInfo = CookieUtil.findCookie(request, "userInfo");    //user1#1
	if(userInfo!=null){
		
		String[] userInfos = userInfo.split("#");
		
		username = userInfos[0];
	    password = userInfos[1];
		
	}
%>

	<center>
		<h2 style="color: red;">登陸</h2>
		<div class="main_logon">
			<form action="UserLoginServlet" name="form1" method="post" onSubmit = "return myfunction()">
				<table>
					<tr>
						<td>用户名：</td>
						<td><input type="text" name="username"></td>
					</tr>
					<tr>
						<td>密 码：</td>
						<td><input type="password" name="password"></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="member" id = "member"><font size="1px" color="gray">记住用户名密码</font></td>
						<td><input type="checkbox" name="autoLogin" id = "autoLogin"><font size="1px" color="gray">七天免登录</font></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input
							style="margin-right: 50px;" type="submit" value="登陸"
							>
							<input type="button" value="注冊" onclick="location='user_register.jsp'"></td>
					</tr>
				</table>
			</form>
		</div>
	</center>
</body>
</html>