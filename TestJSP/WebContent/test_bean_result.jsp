<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="user" scope="request" class="com.neuedu.entity.User" />

<jsp:setProperty property="username" name="user" param="password"/>

<jsp:getProperty property="username" name="user"/>

<jsp:getProperty property="password" name="user"/>
</body>
</html>