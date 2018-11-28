<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

sessionID=<%=session.getId() %>
<br/>

<%
	Cookie[] cookies = request.getCookies();

	if(cookies!=null){
		for(Cookie c:cookies){
			out.print("name="+c.getName()+",value="+c.getValue()+"<br/>");
		}
	}else{
		out.print("没有cookie");
	}
%>

<a href = "cookie_add.jsp">添加cookie</a>
<a href = "cookie_delete.jsp">删除cookie</a>
</body>
</html>