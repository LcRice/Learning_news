<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var xhr = new XMLHttpRequest();

	function callAjaxServlet() {

		var username = document.getElementById("username");

		xhr.open("get","TestAjaxServlet?username=" + encodeURI(username.value), true);

		xhr.send(null);

		xhr.onreadystatechange = callAjaxServletResult;
	}

	function callAjaxServletResult() {
		var text = document.getElementById("text");
		if (xhr.readyState == 4) {
			alert(xhr.status);
			if (xhr.status == 200) {
				if (xhr.responseText == "success") {
					text.innerText = "成功";
				}
				else{
					text.innerText = "失败";
				}
			} else {
				text.innerText = "调用失败";
			}
		}
	}
</script>
</head>
<body>

	<input type="text" id="username">
	<input type="button" value="使用Ajax调用Serclet"
		onclick="callAjaxServlet()" />
	<br />
	<span id="text"></span>
</body>
</html>