<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<form action="draw_line.jsp" method="post">
			画<input type="text" size="6px" name="count">条线 <br />
			<input type="radio" name="postive" value="postive" checked="checked"/>正序 
			<input type="radio" name="postive" value="revese "/>反序 
			<br>
			<input type="submit" value="绘制" style = "margin-right:40px">
			<input type="reset" value="重置">
		</form>
	</center>
</body>
</html>