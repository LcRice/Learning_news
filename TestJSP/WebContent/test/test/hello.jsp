<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WTF</title>
<script language="javascript">
	var timer = null;
	function end() {
		clearTimeout(timer)
	}
	function start() {
		var time = new Date();
		var hours = time.getHours();
		var minutes = time.getMinutes();
		minutes = ((minutes < 10) ? "0" : "") + minutes;
		var seconds = time.getSeconds();
		seconds = ((seconds < 10) ? "0" : "") + seconds;
		var clock = hours + ":" + minutes + ":" + seconds;
		document.forms[0].display.value = clock;
		timer = setTimeout("start()", 1000)
	}
</script>

</head>
<body background="images/4.jpg" onload="start()" onunload="end()"
	style="background-size: 100%">
	<div style="float: right;">
		<form name="form1">
			北京时间：<input type="text" name="display" size="10">
		</form>
	</div>
	<center>
		<h2 style="color: red">Hello</h2>
	</center>
	<hr>
	<h4 style="color: blue">Jsp</h4>
	<h5>
		<%
			String a = "abc";
			out.print("Jsp脚本=" + a);
			int b = 1;
		%>
	</h5>
	<br>
	<h5>
		Jsp表达式=<%=b + b%>
	</h5>
	<br>
	<%!int c = 2;

	int add(int b, int c) {
		return b + c;
	}%>

	<h5>
		Jsp声明=<%=add(b, c)%>
	</h5>

	<h5>
		当前时间为
		<%
		Date date = new Date();
		out.print(date);
	%>
	</h5>
	<br />
	<center>
		<%
			out.print("服务器时间：" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		%>
	</center>
	<br>
	<%
		for (int i = 0; i < 20; i++) {
			if (i % 2 == 0) {
				out.print("<hr color = 'red' width = '" + i + "%'></hr>");
			} else if (i % 3 == 0) {
				out.print("<hr color = 'green' width = '" + i + "%'></hr>");
			} else {
				out.print("<hr color = 'blue' width = '" + i + "%'></hr>");
			}
		}
	%>


	<%
		for (int i = 60; i < 80; i++) {
			if (i % 2 == 0) {
				out.print("<hr color = 'red' width = '" + (100 - i) + "%'></hr>");
			} else if (i % 3 == 0) {
				out.print("<hr color = 'green' width = '" + (100 - i) + "%'></hr>");
			} else {
				out.print("<hr color = 'blue' width = '" + (100 - i) + "%'></hr>");
			}
		}
	%>

	<center>
		<table border="1">
			<%
				int count = 1;
				for (int j = 0; j < 10; j++) {
			%>
			<tr bgcolor=<%=j % 2 == 0 ? "#FF0066" : "#00FFFF"%>>
				<%
					for (int i = 0; i < 10; i++) {
				%>
				<td width='50' height='50' style="text-align: center"><%=count++%></td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>
		</table>
	</center>

	<br />

	<a href="www.baidu.com"><img alt="" src="images/4.jpg"></a>

	<br />
	<form action="get.jsp" method = "get">
		用户名：<input type="text" size=10 name=username>
		<input type = "submit" value = "提交">
	</form>
</body>
</html>