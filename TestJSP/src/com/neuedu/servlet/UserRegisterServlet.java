package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.neuedu.dbutil.DBManager;
import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import com.neuedu.service.impl.UserServiceImpl;
import com.neuedu.util.StringUtil;

public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ���������������
		response.setContentType("text/html;charset=utf-8");

		// ��ȡout�������
		PrintWriter out = response.getWriter();

		// ��ȡsession����
		HttpSession session = request.getSession();

		// ��ȡapplication����
		ServletContext application = this.getServletContext();

		// �������ļ��ж�ȡ�ַ�����
		String charSet = this.getServletContext().getInitParameter("charSet");

		// �������ļ��ж�ȡ�ļ�����
		String fileType = this.getServletContext().getInitParameter("fileType");

		// �����ַ�����
		request.setCharacterEncoding(charSet);

		// ����1---�����ϴ����---ʵ����SmartUpload����
		SmartUpload smartUpload = new SmartUpload("utf-8");
		System.out.println("1");
		try {
			// ����2---��ʼ���ϴ����---����initialize()����
			smartUpload.initialize(getServletConfig(), request, response);
			System.out.println("2");

			// ���2---����ļ���С
			smartUpload.setMaxFileSize(100 * 1024);
			System.out.println("3");

			// ���3---����ļ���ʽ
			smartUpload.setAllowedFilesList(fileType);// ������ļ�����
			System.out.println("4");

			// smartUpload.setDeniedFilesList("html");//��������ļ�����

			// ����3---�ϴ��ļ�������������ʱ�ڴ���---����upload()����
			smartUpload.upload();

		} catch (SmartUploadException e1) {
			e1.printStackTrace();
			out.print("<script>alert('�ļ��ϴ�ʧ��');history.back();</script>");
		} catch (SecurityException e) {
			out.print("<script>alert('�ļ���С���ܳ���100k���ļ����ͱ���Ϊjpg����');history.back();</script>");
		}
		System.out.println("5");

		// ��֤��֤��
		String valCode = smartUpload.getRequest().getParameter("valCode");
		String valCodeSession = (String) session.getAttribute("valCodeInSession");

		if (!valCode.equalsIgnoreCase(valCodeSession)) {
			out.println("<script>alert('��֤�����벻��ȷ��������������');history.back();</script>");
			return;
		}

		// ��������
		String username = "";
		String password = "";
		String photo = "";
		String gender = "";
		String job = "";
		String[] interests = null;
		String interest = "";
		try {
			username = smartUpload.getRequest().getParameter("username");
			password = smartUpload.getRequest().getParameter("password");
			photo = smartUpload.getRequest().getParameter("photo");
			// �ж�photo�Ƿ�Ϊ�ϴ��ļ�
			if ("-1.gif".equals(photo)) {
				try {
					// ����4---��ȡ�ϴ��ļ�
					SmartFile smartFile = smartUpload.getFiles().getFile(0);
					// ����5---׼���ϴ��ļ��Ĵ洢·�����ļ���---��֤�ļ���Ψһ
					String serverFilePath = request.getRealPath("/image/photo");

					String serverFilename = StringUtil.convertFilename(smartFile.getFileName());
					photo = serverFilename;

					// ����6---�����ϴ��ļ�
					smartFile.saveAs(serverFilePath + "/" + serverFilename);
				} catch (SecurityException e) {
					out.print("<script>alert('�ļ���С���ܳ���100k���ļ����ͱ���Ϊjpg����');history.back();</script>");
				}
			}

			gender = smartUpload.getRequest().getParameter("gender");
			job = smartUpload.getRequest().getParameter("job");
			interests = smartUpload.getRequest().getParameterValues("interest");

			if (interests != null) {
				for (String s : interests) {
					interest += s + " ";
				}
				interest.trim();
			}
		} catch (Exception e) {
			out.print("�Ϊ�գ���<a href='user_register.jsp'>��������</a>");
		}

		UserService userService = new UserServiceImpl();

		int registerScore = Integer.parseInt(this.getInitParameter("registerScore"));

		User user = new User(username, password, registerScore, photo, gender, job, interest, new Date());

		if (userService.checkUsername(username)) {
			user = userService.register(user);
			if (user != null) {
				session.setAttribute("user", user);

				if (application.getAttribute("onlineCount") == null) {
					application.setAttribute("onlineCount", 1);
				} else {
					application.setAttribute("onlineCount", (Integer) application.getAttribute("onlineCount") + 1);
				}

				response.sendRedirect("user_register_result.jsp");
			}
		} else {
			out.println("<script> alert('�Ñ����Ѿ����ڣ�Ո����ע��');history.back();</script>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
