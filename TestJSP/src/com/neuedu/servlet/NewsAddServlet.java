package com.neuedu.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.neuedu.entity.Attachment;
import com.neuedu.entity.News;
import com.neuedu.entity.NewsType;
import com.neuedu.entity.User;
import com.neuedu.service.NewsService;
import com.neuedu.service.impl.NewsServiceImpl;
import com.neuedu.util.StringUtil;

public class NewsAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ���������������
		response.setContentType("text/html;charset=utf-8");

		// ��ȡout�������---��Ҫ�������ʱ�Ӵ˾�
		PrintWriter out = response.getWriter();

		// ��ȡsession����---��Ҫsession����ʱ�Ӵ˾�
		HttpSession session = request.getSession();

		/*
		 * //�������ļ��ж�ȡ�ַ����� String charSet =
		 * this.getServletContext().getInitParameter("charSet");
		 * 
		 * //�����ַ����� //request.setCharacterEncoding(charSet);
		 */

		// ****************�ļ��ϴ���ʼ********************************

		// �������ļ��ж�ȡ�ļ���С�����������
		int maxFileSize = Integer.parseInt(this.getInitParameter("maxFileSize"));
		String deniedFileList = this.getInitParameter("deniedFileList");

		// ��ʼ�������б�*********
		List<Attachment> attachments = new ArrayList<>();

		// ����1---�����ϴ����---ʵ����SmartUpload����
		SmartUpload smartUpload = new SmartUpload("utf-8");

		// ����2---��ʼ���ϴ����---����initialize()����
		smartUpload.initialize(getServletConfig(), request, response);

		/*// ���2---�ļ���С
		smartUpload.setMaxFileSize(maxFileSize * 1024 * 1024);

		// ���3---�ļ�����
		try {
			smartUpload.setDeniedFilesList(deniedFileList);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/

		// ����3---�ϴ��ļ�������������ʱ�ڴ���---����upload()����
		try {
			smartUpload.upload();
		} catch (SmartUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// ���صĿ۳�����*********
		String[] downloadscores = smartUpload.getRequest().getParameterValues("downloadscore");

		boolean flag = true;

		// ����4---��ȡÿһ���ϴ��ļ� *********
		for (int i = 0; i < smartUpload.getFiles().getCount(); i++) {

			SmartFile smartFile = smartUpload.getFiles().getFile(i);

			// ���1---�ļ��ǿ�
			if (!smartFile.isMissing()) {

				// ����5---׼���ϴ��ļ��Ĵ洢·�����ļ���---��֤�ļ���Ψһ
				String serverFilePath = this.getServletContext().getRealPath("/attachment");

				// �Զ�����Ŀ¼
				File dir = new File(serverFilePath);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				if (smartFile.getSize() > maxFileSize * 1024 * 1024) {
					out.print("<script>alert('��" + (i + 1) + "���ļ���С���ܳ���" + maxFileSize * 1024 * 1024 + "k');</script>");
					flag = false;
					continue;
				}

				if (deniedFileList.contains(smartFile.getFileExt())) {
					out.println("<script>alert('��" + (i + 1) + "���ļ����Ͳ�����" + deniedFileList + "');</script>");
					flag = false;
					continue;
				}

				String serverFilename = StringUtil.convertFilename(smartFile.getFileName());

				// ����6---�����ϴ��ļ�
				try {
					smartFile.saveAs(serverFilePath + "/" + serverFilename);
				} catch (SmartUploadException e) {
					e.printStackTrace();
					out.println("<script>alert('��" + (i + 1) + "���ļ��ϴ�ʧ��');</script>");
				}

				// ��������丽������***************
				Attachment attachment = new Attachment();
				attachment.setAttachment(serverFilename); // ����attachment������ֵΪ�µ��ļ���
				attachment.setTruename(smartFile.getFileName()); // ԭʼ�ļ���
				attachment.setDownload(Integer.parseInt(downloadscores[i]));// ���صĿ۳�����
				attachment.setDownloadcount(0);

				// ��ӵ������б���***************
				attachments.add(attachment);
			}

		}
		if (flag) {
			out.println("<script>alert('ȫ���ļ��ϴ��ɹ�');</script>");
		} else {
			out.println("<script>alert('�����ļ��ϴ��ɹ�');</script>");
		}

		// ****************�ļ��ϴ�����********************************

		// ��������
		int typeid = Integer.parseInt(smartUpload.getRequest().getParameter("typeid"));
		String title = smartUpload.getRequest().getParameter("title");
		String content = smartUpload.getRequest().getParameter("content");

		// ��ȡ������ųɹ��ļӷ�
		int addNewsScore = Integer.parseInt(this.getInitParameter("addNewsScore"));

		// ��session���Է�Χ�л�ȡ��ǰ�û�����
		User user = (User) session.getAttribute("user");

		// ���������newsʵ����
		News news = new News();
		news.setTitle(title);
		news.setContent(content);
		news.setPubtime(new Date());

		// news.setTypeid(typeid);

		// news�����������newstype����
		NewsType newsType = new NewsType();
		newsType.setTypeid(typeid);
		news.setNewstype(newsType);

		// news.setUserid(user.getUserid());
		news.setUser(user);

		// ��丽���б�********
		news.setAttachment(attachments);

		/*
		 * news.setAttachment(attachment); news.setTruename(truename);
		 * news.setDownloadscore(downloadscore);
		 * 
		 * //���ش���Ĭ��Ϊ0 news.setDownloadcount(0);
		 */

		// ���ҵ�����
		NewsService newsService = new NewsServiceImpl();

		// ����ҵ�񷽷�---�������
		if (newsService.addNews(news, user, addNewsScore)) {
			out.println(
					"<script>if(confirm('������ӳɹ����Ƿ�������')){location='news_add.jsp'}else{location='news_query.jsp'}</script>");
		} else {
			out.println("<script>alert('�������ʧ�ܣ�����������');history.back()</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}