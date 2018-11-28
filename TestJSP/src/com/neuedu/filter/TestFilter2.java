package com.neuedu.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TestFilter2 implements Filter {

	public TestFilter2() {
		System.out.println("TestFilter2被创建！");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Filterinit2()方法被调用！");
	}

	public void destroy() {
		System.out.println("Filterdestroy2()方法被调用！");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// place your code here
		// 预处理
		System.out.println("2预处理！");
		// pass the request along the filter chain
		chain.doFilter(request, response);

		// 后处理
		System.out.println("2后处理！");
	}

}
