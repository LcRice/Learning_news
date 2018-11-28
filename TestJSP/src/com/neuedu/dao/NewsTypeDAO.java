package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.NewsType;

public interface NewsTypeDAO {
	//��������
	int findAllLine();
	
	//������������
	List<NewsType> findAllType();
	
	//����id����������
	NewsType findNewsTypeById(int typeid);
	
}
