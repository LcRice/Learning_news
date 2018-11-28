<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function addFile() {
	var txtFile = document.createElement("input");
	txtFile.type="file";
	txtFile.name="file";
	
	var btnDelete = document.createElement("input");
	btnDelete.type="button";
	btnDelete.value="删除";
	
	var br = document.createElement("br");
	
	var divAddFile = document.getElementById("divAddMore");
	
	divAddFile.appendChild(txtFile);
	divAddFile.appendChild(btnDelete);
	divAddFile.appendChild(br);
	
	btnDelete.onclick = function(){
		divAddMore.removeChild(txtFile);
		divAddMore.removeChild(btnDelete);
		divAddMore.removeChild(br);
	}
}

</script>
</head>
<body>
	<form action="UploalMultiServlet" method = "post" enctype="multipart/form-data">
		<input type = "file" name = "file">
		<input type = "button" value = "添加" onclick="addFile()"/>
				
		<br>
		<div id="divAddMore"></div>
		<input type = "submit" value = "提交">
	</form>
</body>
</html>