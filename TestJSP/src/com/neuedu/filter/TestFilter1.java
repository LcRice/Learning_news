package com.neuedu.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TestFilter1 implements Filter {

    public TestFilter1() {
    	System.out.println("TestFilter1��������");
    }

    public void init(FilterConfig fConfig) throws ServletException {
    	System.out.println("Filterinit1()���������ã�");
    }
    
	public void destroy() {
		System.out.println("Filterdestroy1()���������ã�");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		//Ԥ����
		System.out.println("1Ԥ����");
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		//����
		System.out.println("1����");
	}

	

}
