package com.neuedu.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class CharacterEncodingFilter implements Filter {

	String charSet;
	
	public void init(FilterConfig fConfig) throws ServletException {
		charSet = fConfig.getInitParameter("charSet");
	}
	
	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		request.setCharacterEncoding(charSet);
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	


}
