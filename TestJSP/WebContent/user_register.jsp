<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkUsernameOnly() {
		var username = document.getElementById("username");

		var usernameResult = document.getElementById("usernameResult");
		//if(username.value=="")
		//if(username.value.length==0){
		if (username.value.length<5||username.value.length>10) {
			usernameResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户名需在5-10字符之间</font>";
		} else {
			usernameResult.innerHTML = "<img src = 'image/yes.gif'/><font color='green' size='1px'>用户名可用</font>";
			//	}
		}
	}

	function checkPassword() {
		var password = document.getElementById("password");
		var passwordResult = document.getElementById("passwordResult");
		if (password.value.length<6||password.value.length>16) {
			passwordResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>密码需在6-16字符之间</font>";
		} else {
			passwordResult.innerHTML = "<img src = 'image/yes.gif'/>";
		}

	}

	function checkPassword2() {
		var password = document.getElementById("password");
		var password2 = document.getElementById("password2");
		var password2Result = document.getElementById("password2Result");
		if (password != password2) {
			password2Result.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>两次密码输入不一致</font>";
		} else {
			password2Result.innerHTML = "<img src = 'image/yes.gif'/>";
		}
	}
	//表单级校验---提交时对每个字段检查
	function checkData() {

		var username = document.getElementById("username");

		var password = document.getElementById("password");
		var password2 = document.getElementById("password2");

		var file = document.getElementById("file").value;
		
		if (username.value.length<5||username.value.length>10) {
			alert("用户名需在5-10字符之间");
			return false;
		}

		else if (password.value.length == 0) {
			alert("密码不能为空");
			return false;
		}

		else if (password.value != password2.value) {
			alert("两次密码输入不一致");
			return false;
		} 
		else if(file.length==0){
			alert("头像为空，请选择上传");
			return false;
		}else{
			return true;
		}
	}

	var xhr = new XMLHttpRequest();

	function checkUsername() {

		var username = document.getElementById("username");

		var usernameResult = document.getElementById("usernameResult");
		//if(username.value=="")
		//if(username.value.length==0){
		if (username.value.length<5||username.value.length>10) {
			usernameResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户名需在5-10字符之间</font>";
		} else {
			xhr.open("get", "CheckUsernameServlet?username=" + encodeURI(username.value), true);

			xhr.send(null);

			xhr.onreadystatechange = checkUsernameResult;
		}
	}

	function checkUsernameResult() {
		var usernameResult = document.getElementById("usernameResult");
		if (xhr.readyState == 4) {
			
			if (xhr.status == 200) {
				if (xhr.responseText == "success") {
					usernameResult.innerHTML = "<img src = 'image/yes.gif'/><font color='green' size='1px'>用户名可用</font>";
				} else {
					usernameResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户已存在</font>";
				}
			} else {
				usernameResult.innerText = "调用失败";
			}
		}
	}
	
	var xhr1 = new XMLHttpRequest();
	
	function checkValCode() {

		var valCode = document.getElementById("valCode");

		xhr1.open("get", "CheckValCodeServlet?valCode=" + encodeURI(valCode.value), true);

		xhr1.send(null);

		xhr1.onreadystatechange = checkValCodeResult;
		
	}

	function checkValCodeResult() {
		var valCodeResult = document.getElementById("valCodeResult");
		if (xhr1.readyState == 4) {
			
			if (xhr1.status == 200) {
				if (xhr1.responseText == "success") {
					valCodeResult.innerHTML = "<img src = 'image/yes.gif'/><font color='green' size='1px'>验证码正确</font>";
				} else {
					valCodeResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>验证码输入错误，请重新输入</font>";
				}
			} else {
				valCodeResult.innerText = "调用失败";
			}
		}
	}
	
	function photoUpdate() {
		var photoUpdate = document.getElementById("photoUpdate");
		
		var photo = document.getElementsByName("photo");
		
		var flag = null;
		for(var i = 0;i<photo.length;i++){
			if(photo[i].checked){
				flag = photo[i].value;
			}
		}
		if(flag == "-1.gif"){
			photoUpdate.style.visibility = "visible";
		}else{
			photoUpdate.style.visibility = "hidden";
		}
	}
</script>
<style type="text/css">
div {
	width: 600px;
	height: 250px;
}

.main_register {
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

	<center>
		<h2 style="color: red;">注冊</h2>
		<div class="main_register" style="width: 600px">
			<form action="UserRegisterServlet" name="form1" method="post" onsubmit="return checkData();" enctype="multipart/form-data">
				<table>
					<tr>
						<td align="right">用戶名：</td>
						<td align="left" width="500px">
						<input type="text" name="username" id="username" onblur="checkUsername()">
						<span id="usernameResult"></span></td>
					</tr>
					<tr>
						<td align="right">密 碼：</td>
						<td align="left"><input type="text" name="password" id="password" onblur="checkPassword()"> 
						<span id="passwordResult"></span></td>
					</tr>
					<tr>
						<td align="right">确认密碼：</td>
						<td align="left"><input type="text" name="password" id="password2" onblur="cheakPassword2()"> 
						<span id="password2Result"></span></td>
					</tr>
					<tr>
						<td align="right">頭像：</td>
						<td><input type="radio" name="photo" value="1.gif" checked="checked" onclick="photoUpdate()">
								<img alt="" src="image/photo/1.gif">
							<input type="radio" name="photo" value="2.gif" onclick="photoUpdate()">
								<img alt="" src="image/photo/2.gif"> 
							<input type="radio" name="photo" value="3.gif" onclick="photoUpdate()">
								<img alt="" src="image/photo/3.gif"> 
							<input type="radio" name="photo" value="4.gif" onclick="photoUpdate()">
								<img alt="" src="image/photo/4.gif">
							<input type="radio" name="photo" value="5.gif" onclick="photoUpdate()">
								<img alt="" src="image/photo/5.gif">
							<br/>
							
							<input type="radio" name="photo" value ="-1.gif" onclick="photoUpdate()">自定义
							<span id = "photoUpdate" style="visibility: hidden;"><input type="file" name="file" id="file"></span>
						</td>
					</tr>
					<tr>
						<td align="right">性別：</td>
						<td align="left"><input type="radio" name="gender" value="男"
							checked />男 <input type="radio" name="gender" value="女" />女</td>
					</tr>
					<tr>
						<td align="right">職業：</td>
						<td align="left"><select name="job">
								<option value="程序员" checked>程序员</option>
								<option value="美工">美工</option>
								<option value="项目经理">项目经理</option>
						</select></td>
					</tr>
					<tr>
						<td align="right">興趣愛好：</td>
						<td align="left"><input type="checkbox" name="interest"
							value="唱歌" />唱歌 <input type="checkbox" name="interest"
							value="跳舞" />跳舞 <input type="checkbox" name="interest"
							value="跑步" />跑步 <input type="checkbox" name="interest"
							value="游泳" />游泳</td>
					</tr>
					<tr>
						<td>驗證碼</td>
						<td><input type="text" name="valCode" id = "valCode" onblur = "checkValCode()"> 
						<img src="ValCodeServlet" id="imgValCode" onclick="this.src=this.src+'?'" /> 
						<input type="button" value="更新" onclick="document.getElementById('imgValCode').src=document.getElementById('imgValCode').src+'?'" />
							<span id = "valCodeResult"></span>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input
							style="margin-right: 50px;" type="submit" value="注冊" /><input
							type="reset" value="重置"></td>
					</tr>

				</table>
			</form>
		</div>
	</center>
</body>
</html>