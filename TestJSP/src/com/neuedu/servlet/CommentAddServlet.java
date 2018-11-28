package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Comment;
import com.neuedu.entity.News;
import com.neuedu.entity.User;
import com.neuedu.service.NewsService;
import com.neuedu.service.impl.NewsServiceImpl;

public class CommentAddServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���������������
		response.setContentType("text/html;charset=utf-8");	

		//��ȡout�������
		PrintWriter out = response.getWriter();		

		//��ȡsession����
	    HttpSession session = request.getSession();	

	   /*	//��ȡ�ַ�����
	   	String charSet = this.getServletContext().getInitParameter("charSet");
	   	
	   	//�����ַ�����
	  	request.setCharacterEncoding(charSet); */
	  
	  	//��������
	  	//String content = request.getParameter("content");
	  	String content = request.getParameter("content");
	  	//String content = CKEditorUtil.getTextFromHtml((String)request.getAttribute("content"));
	  	
  		int score = Integer.parseInt(request.getParameter("score"));
  		int newsid = Integer.parseInt(request.getParameter("newsid"));
  		
  		//��ȡ������ӳɹ���ļӷ�
  		int addCommentScore = Integer.parseInt(this.getInitParameter("addCommentScore"));
  		
  		//���������ʵ����
  		Comment comment = new Comment();

  		comment.setContent(content);
  		comment.setScore(score);
  		comment.setPubtime(new Date());

  		//���news����
  		News news = new News();
  		news.setNewsid(newsid);
  		comment.setNews(news);
  		
  		//��session���Է�Χ��ȡ����ǰ��¼���û�
  		User user = (User)session.getAttribute("user");
  		
  		//���user����
  		comment.setUser(user);
  		
  		//���ҵ������
  		NewsService newsService = new NewsServiceImpl();
  		
  		//����ҵ��㷽��
  		if(newsService.addComment(comment, user, addCommentScore)){
  			out.println("<script>alert('����������ӳɹ�');location='CommentQueryServlet?newsid=" + newsid + "'</script>");   
  		}else{
  			out.println("<script>alert('�����������ʧ��');history.back()</script>");			
  		}
	  		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}