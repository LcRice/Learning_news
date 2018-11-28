<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

second.jsp

<%
	int a = 1;
	a ++;
	out.print("a=" + a);
%>
<br/>

username = ${param.username}

password = ${requestScope.password}


