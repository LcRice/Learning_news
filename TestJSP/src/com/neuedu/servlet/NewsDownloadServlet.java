package com.neuedu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.neuedu.entity.User;
import com.neuedu.service.NewsService;
import com.neuedu.service.impl.NewsServiceImpl;

public class NewsDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		
		//��������
		String attachment = request.getParameter("attachment");
		String truename = request.getParameter("truename");
		
		//��ȡ��ʵ·��
		String filepath = request.getServletContext().getRealPath("/attachment");
		
		//����1---�����ϴ��������
		SmartUpload smartUpload = new SmartUpload("utf-8");

		//����2---��ʼ���ϴ��������
		smartUpload.initialize(getServletConfig(), request, response);

		//����3---���ö����ݵĴ���ʽ---��ֹ���ֱ����ʾ�ļ�����
		smartUpload.setContentDisposition(null);   

		//����4---�����ļ�
	    try {
			smartUpload.downloadFile(filepath + "/" + attachment, null, truename);
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}  
	    
	    //****************�������ص�ҵ����******************
	    
		//�������ػ��ֺ����ű��
		int download = Integer.parseInt(request.getParameter("download"));	
		int attachmentid = Integer.parseInt(request.getParameter("attachmentid"));	

		//��ȡsession����
		HttpSession session = request.getSession();
			
		//��ȡ��ǰ��¼�û�
		User user = (User)session.getAttribute("user");

		//���ҵ�����
		NewsService newsService = new NewsServiceImpl();
				
		//����ҵ�񷽷�			
		newsService.downloadAttachment(user, attachmentid, download);
		
	 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}