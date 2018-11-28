package com.neuedu.service.impl;

import java.util.List;

import com.neuedu.dao.NewsTypeDAO;
import com.neuedu.dao.impl.NewsTypeDAOImpl;
import com.neuedu.entity.NewsType;
import com.neuedu.service.NewsTypeService;

public class NewsTypeServiceImpl implements NewsTypeService {
	
	private NewsTypeDAO newsTypeDAO = new NewsTypeDAOImpl();
	
	@Override
	public int findAllLine() {
		return newsTypeDAO.findAllLine();
	}

	@Override
	public List<NewsType> findAllType() {
		return newsTypeDAO.findAllType();
	}

	@Override
	public NewsType findNewsTypeById(int typeid) {
		return newsTypeDAO.findNewsTypeById(typeid);
	}

}
