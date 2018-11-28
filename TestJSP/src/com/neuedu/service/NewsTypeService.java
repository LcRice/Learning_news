package com.neuedu.service;

import java.util.List;

import com.neuedu.entity.NewsType;

public interface NewsTypeService {
		//查找总行
		int findAllLine();
		
		//查找新闻名称
		List<NewsType> findAllType();
		
		//根据id查找类型名
		NewsType findNewsTypeById(int typeid);
}
