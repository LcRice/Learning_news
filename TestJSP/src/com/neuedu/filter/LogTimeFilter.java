package com.neuedu.filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogTimeFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		long begin = System.currentTimeMillis();
		// place your code here
		// ���������������
		response.setContentType("text/html;charset=utf-8");

		// ��ȡout�������
		PrintWriter out = response.getWriter();

		// ת��
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// ��ȡsession����
		HttpSession session = req.getSession();

		String address = req.getRemoteAddr();// ��ַ

		Date date = new Date();// ʱ��

		String method = req.getMethod();// ����ͷ

		String url = req.getRequestURI();

		int code = resp.getStatus();
		
		System.out.println(address + " ; " + method + " ; " + date + " ; " + url + " ; "+code);

		long end = System.currentTimeMillis();
		
		System.out.println("time=" + (end - begin) + "ms");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:/4.txt")));

		String s = address + " ; " + method + " ; " + date + " ; " + url + " ; "+ code + " ; "+ (end - begin);

		bw.write(s);
		bw.newLine(); // ����
		bw.flush();
		
		bw.close();
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

}
