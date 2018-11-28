<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	InputStream is = request.getInputStream();

	BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
	
	String line = null;
	
	while((line = br.readLine())!=null){
		out.print(line+"<br/>");
	}

	br.close();
%>
</body>
</html>