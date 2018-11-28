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

		// 设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		// 获取out输出对象
		PrintWriter out = response.getWriter();

		if(userids==null){
			out.print("<script>alert('server： 至少选择一个用户！');history.back();</script>");
			return;
		}
		UserService userService = new UserServiceImpl();

		if (userService.deleteUserBatch(userids)) {
			out.print("<script>alert('用户批量删除成功！');location = 'UserQueryServlet';</script>");
		} else {
			out.print("<script>alert('用户批量删除失败！');history.back();</script>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
