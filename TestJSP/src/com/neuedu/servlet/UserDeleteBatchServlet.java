package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.service.UserService;
import com.neuedu.service.impl.UserServiceImpl;

public class UserDeleteBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] userids = request.getParameterValues("userids");

		// ���������������
		response.setContentType("text/html;charset=utf-8");

		// ��ȡout�������
		PrintWriter out = response.getWriter();

		if(userids==null){
			out.print("<script>alert('server�� ����ѡ��һ���û���');history.back();</script>");
			return;
		}
		UserService userService = new UserServiceImpl();

		if (userService.deleteUserBatch(userids)) {
			out.print("<script>alert('�û�����ɾ���ɹ���');location = 'UserQueryServlet';</script>");
		} else {
			out.print("<script>alert('�û�����ɾ��ʧ�ܣ�');history.back();</script>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
