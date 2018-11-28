<%@page import="java.util.List"%>
<%@page import="com.neuedu.entity.NewsType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>

	//瀹屽杽
	function checkData(){
		
		var attachment = document.getElementById("attachment");
		var downloadscore = document.getElementById("downloadscore");
		
		if(attachment.value!=""){   //閫夋嫨浜嗛檮浠�
			
			if(downloadscore.value=="" || isNaN(downloadscore.value)){
				alert("下载积分输入错误");
				return false;
			}
		}
		
		return true;
	}
	
	//娣诲姞鏇村鏂囦欢
	function addMore(){
		
		//鍒涘缓鏂囦欢杈撳叆妗�
		var txtFile = document.createElement("input");
		txtFile.type = "file";
		txtFile.name = "attachment";
		
		//鍒涘缓span
		var spanTip = document.createElement("span");
		spanTip.innerText = "下载积分";

		//鍒涘缓绉垎杈撳叆妗�
		var txtDownloadscore = document.createElement("input");
		txtDownloadscore.type = "text";
		txtDownloadscore.name = "downloadscore";
		
		//鍒涘缓鍒犻櫎鎸夐挳
		var btnDelete = document.createElement("input");
		btnDelete.type = "button";
		btnDelete.value = "删除";
		
		//鍒涘缓鎹㈣鏍囩
		var br = document.createElement("br");
		
		//鑾峰彇div缁勪欢
		var divAddMore = document.getElementById("divAddMore");
		
		//娣诲姞鍒癲iv涓�
		divAddMore.appendChild(txtFile);
		divAddMore.appendChild(spanTip);
		divAddMore.appendChild(txtDownloadscore);
		divAddMore.appendChild(btnDelete);
		divAddMore.appendChild(br);
		
		//璁剧疆鎸夐挳鐨勫崟鍑讳簨浠�
		btnDelete.onclick = function(){
			divAddMore.removeChild(txtFile);
			divAddMore.removeChild(spanTip);
			divAddMore.removeChild(txtDownloadscore);
			divAddMore.removeChild(btnDelete);
			divAddMore.removeChild(br);
		}
		
	}
</script>
</head>
<body>

<%-- <c:if test="${sessionScope.user==null}">

<%

		//鍦╯esson灞炴€ц寖鍥翠腑淇濆瓨鐧诲綍鍚庤杩斿洖鐨刄RL
		//session.setAttribute("prevURL", "news_add".jsp");
			
		//鎴彇鐧诲綍鍚庤杩斿洖鐨刄RL
		String uri = request.getRequestURI();
 		String prevURL = uri.substring(uri.lastIndexOf("/") + 1);
 
		//鍦╯esson灞炴€ц寖鍥翠腑淇濆瓨鐧诲綍鍚庤杩斿洖鐨刄RL
		session.setAttribute("prevURL", prevURL);
%>
	    <script>
	    	alert("鎮ㄥ皻鏈櫥褰曪紝璇峰厛鐧诲綍"); 		   //寮规
	    	location.href = "user_login.jsp";  //瀹㈡埛绔烦杞�  href灞炴€ф槸榛樿灞炴€�
	    </script>
	    
</c:if> --%>


<c:if test="${sessionScope.user!=null}">

<form action="NewsAddServlet" method="post" enctype="multipart/form-data" onsubmit="return checkData()">

	<h3 align="center">添加新闻</h3>
	<table cellpadding="1" cellspacing="0" align="center">
		
		<tr>
			<td>新闻类型</td>
			<td>
				<select name="typeid">
					<c:forEach var="newsType" items="${applicationScope.list}">
						<option value="${newsType.typeid }">${newsType.typename}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
			<td>新闻标题</td>
			<td><input type="text" name="title"/></td>
		</tr>
		
		<tr>
			<td>新闻内容</td>
			<td><textarea rows="5" cols="20" name="content"></textarea></td>
		</tr>
		
		<tr>
			<td>新闻附件</td>
			<td>
				<input type="file" name="attachment" id="attachment"/>
				下载积分
				<input type="text" name="downloadscore" id="downloadscore"/>
				<input type="button" value="添加" onclick="addMore()"/>
				<div id="divAddMore"></div>
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center"><input type="submit" value="添加"/> </td>
		</tr>
		
		
	</table>

</form>

</c:if>

</body>
</html>