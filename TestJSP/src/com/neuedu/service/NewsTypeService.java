package com.neuedu.service;

import java.util.List;

import com.neuedu.entity.NewsType;

public interface NewsTypeService {
		//��������
		int findAllLine();
		
		//������������
		List<NewsType> findAllType();
		
		//����id����������
		NewsType findNewsTypeById(int typeid);
}
