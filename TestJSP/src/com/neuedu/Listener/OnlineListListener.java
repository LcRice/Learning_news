package com.neuedu.Listener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.neuedu.entity.User;

public class OnlineListListener implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener,
		HttpSessionAttributeListener, ServletRequestListener, ServletRequestAttributeListener {

	private ServletContext application = null;

	public OnlineListListener() {
		// TODO Auto-generated constructor stub
	}

	// requests��������
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("request ����");
	}

	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		System.out.println("request ����");
	}

	// request�޸�
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
	}

	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
	}

	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
	}

	// session����������
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.out.println("session ����");
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		List<String> online = (List<String>) this.application.getAttribute("online");
		// ȡ�õ�ǰ�û���
		String username = (String) se.getSession().getAttribute("username");
		// �����û������б���ɾ��
		online.remove(username);
		// ��ɾ������б��������õ�application������
		this.application.setAttribute("online", online);
		System.out.println("session ����");
	}

	// session�޸�
	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		List<String> online = (List<String>) this.application.getAttribute("online");
		if ("username".equals(se.getName())) {
			// ����ǰ�û�����ӵ��б���
			online.add((String) se.getValue());
		}
		// ����Ӻ���б��������õ�application������
		this.application.setAttribute("online", online);
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
	}

	// application����������
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		this.application = sce.getServletContext();
		// ����һ���б����ԣ����ڱ��������û���
		this.application.setAttribute("online", new LinkedList<String>());
		System.out.println("application ����");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("application ����");
	}

	// application�޸�
	public void attributeAdded(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
	}

	public void attributeReplaced(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
	}

	public void attributeRemoved(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
	}
}
