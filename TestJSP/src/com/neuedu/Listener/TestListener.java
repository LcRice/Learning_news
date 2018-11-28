package com.neuedu.Listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class TestListener implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener,
		HttpSessionAttributeListener, ServletRequestListener, ServletRequestAttributeListener {

	private static String url;
	private static Map<String, Integer> map = new HashMap<>();

	public TestListener() {
		// TODO Auto-generated constructor stub
	}

	// requests��������
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)sre.getServletRequest();;
		System.out.println("1111");
		url = req.getRequestURI();
		String key = null;
		int value = 0;
		int i = 0;
		if (map.size() != 0) {
			Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
			for (Map.Entry<String, Integer> entry : entrySet) {
				// ȡ��key
				key = entry.getKey();
				// ȡ��value
				value = entry.getValue();
				if (key.equals(url)) {
					value = value + 1;
					i = i + 1;
				}
			}
		}
		if (i == 0) {
			map.put(url, 1);
			System.out.println("��ӳɹ�");
		}

		Set<String> set = map.keySet();
		for (String key1 : set) {

			// ����keyȡ��value
			int value1 = map.get(key1);

			System.out.println("key=" + key1 + " value=" + value1);
		}
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
		System.out.println("session ����");
	}

	// session�޸�
	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
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
		System.out.println("application ����");
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream("d:/obj.txt"));

			System.out.println(ois.readObject());
			while(ois.readObject()!=null){
			map = (Map<String, Integer>) ois.readObject();
			
				Set<String> set = map.keySet();
				for (String key : set) {
					int value = map.get(key);
					System.out.println("key=" + key + " value=" + value);
				}
			}
			System.out.println("application �������");

			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("application ����");
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("d:/obj.txt"));

			oos.writeObject(map);
			oos.writeObject(null);
			System.out.println("application �������");
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
