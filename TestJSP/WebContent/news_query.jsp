<%@page import="com.neuedu.util.StringUtil"%>
<%@page import="com.neuedu.entity.News"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>
  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

 <script>

 	//检查用户积分是否够用
	function checkScore(userscore, download){
		
		if(userscore < download){

			alert("您只剩下" + userscore + "分，该附件需要" + download + "分，无法下载");

			return false;

		}else{
			
			if(!confirm("下载该附件将扣除您" + download + "分，确定下载吗")){
				return false;
			}
		}
		
		//弹出下载成功的新窗口
		window.open("news_download_result.jsp");
		
		return true;
	}
	
   </script>
   
</head>
<body>


<c:if test="${requestScope.newsPage==null }">
	<%
	response.sendRedirect("NewsQueryServlet?currentPage=1");
	%>
</c:if>


<c:if test="${requestScope.newsPage!=null }">

	<table border="1" cellpadding="1" cellspacing="0">
	
	<tr><th>用户名</th><th>新闻类型</th><th>新闻主题</th><th>新闻内容</th><th>发布时间</th><th>下载</th></tr>
	
	<c:set var="row" value="0"/>
		
	<c:forEach items="${newsPage.dataList}" var="news">
		
		 <tr bgcolor='${row==0 ? "#d0d0d0" : "#ffffff"  }'>
		 
		 	<td align="center">
					<%-- <img src="image/photo/${news.user.photo }" width="50" height="50" />
					<br/> --%>
					${news.user.username }
				</td>
		
		
				<td>${news.newstype.typename }</td>
		
				<%-- <td>${news.title }</td>--%>
				<td><a href="CommentQueryServlet?newsid=${news.newsid}">${news.title }</a></td>
				
				<td>${news.content}</td>
				
				<td>${myfn:convertDatetime(news.pubtime) }</td>
				
				<td align="center">
				
					<c:forEach items="${news.attachment }" var="attachment">
						<a href="NewsDownloadServlet?attachment=${attachment.attachment}&truename=${attachment.truename}&download=${attachment.download}&attachmentid=${attachment.attachmentid}" onclick="return checkScore(${sessionScope.user.score}, ${attachment.download})">
						
							<img src="icon/${myfn:getIconFilename(attachment.attachment, pageContext.servletContext) }"/>

						</a>
						
						${myfn:getFilesize(attachment.attachment, pageContext.servletContext) }
						
						<br/>
						扣${attachment.download }分,已${attachment.downloadcount }次
						
						<br/>
					</c:forEach>
				
				
				
						<%-- <a href="NewsDownloadServlet?attachment=${news.attachment }&truename=${news.truename}"> --%>
						
						
					
				</td>
				
		  </tr>
		    
		 <c:set var="row" value="${1-row }"/>
		    
 	</c:forEach>

	<tr>
		<td colspan="6" align="center">
			共${newsPage.totalCount }条记录&nbsp;&nbsp;&nbsp;
			每页${newsPage.pageSize }条记录&nbsp;&nbsp;&nbsp;
                                    第【${newsPage.currentPage}】页/共${newsPage.totalPage }页
		</td>
	</tr>
	
	<tr>
		<td colspan="6" align="center">
		
			<c:if test="${newsPage.currentPage==1 }">
				首页&nbsp;&nbsp;&nbsp;上一页
			</c:if>
			<c:if test="${newsPage.currentPage!=1 }">
				<a href="NewsQueryServlet?currentPage=1">首页</a>&nbsp;&nbsp;&nbsp;
				<a href="NewsQueryServlet?currentPage=${newsPage.currentPage-1}">上一页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
			<c:if test="${newsPage.currentPage==newsPage.totalPage }">
				下一页&nbsp;&nbsp;&nbsp;尾页
			</c:if>
			<c:if test="${newsPage.currentPage!=newsPage.totalPage }">
				<a href="NewsQueryServlet?currentPage=${newsPage.currentPage+1}">下一页</a>&nbsp;&nbsp;&nbsp;
				<a href="NewsQueryServlet?currentPage=${newsPage.totalPage }">尾页</a>&nbsp;&nbsp;&nbsp;
			</c:if>
			
		</td>
	</tr>
	
	<tr>
		<td colspan="6" align="center">
			<select name="currentPage" onchange="location='NewsQueryServlet?currentPage=' + this.value">
				<c:forEach var="i" begin="1" end="${newsPage.totalPage}" step="1">
					<option value="${i}" ${i==newsPage.currentPage ? "selected" : ""}>${i}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	
	<tr>
		<td colspan="6" align="center">
		
			<c:set var="pageNumber" value="5"/>
			
			<c:forEach var="i" begin="${newsPage.begin}" end="${newsPage.end}" step="1">
				
				<c:if test="${i==newsPage.currentPage}">
					${i}
				</c:if>
				
				<c:if test="${i!=newsPage.currentPage}">
					<a href="NewsQueryServlet?currentPage=${i}">${i}</a>
				</c:if>
	
			</c:forEach>
		</td>
	</tr>
	
	
   </table>

</c:if>

</body>
</html>