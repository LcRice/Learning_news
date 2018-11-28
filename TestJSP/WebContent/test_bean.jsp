<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- <jsp:useBean id="user" scope="request" class="com.neuedu.entity.User"></jsp:useBean>

<jsp:setProperty property="username" name="user" value="TYUT"/>

<jsp:forward page="test_bean_result.jsp"></jsp:forward> --%>

<form action="test_bean_result.jsp" method="post">
	username:<input type="text" name="username" /><br/>
	password:<input type="password" name="password" /><br/>
	<input type="submit" value="submit">
</form> 
</body>
</html>