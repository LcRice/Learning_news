package com.neuedu.service;

import java.util.List;

import com.neuedu.entity.Comment;
import com.neuedu.entity.News;
import com.neuedu.entity.User;
import com.neuedu.vo.NewsPage;

public interface NewsService {

	//添加新闻
	boolean addNews(News news, User user, int addNewsScore);
	
	
	//获取全部的新闻列表
	//List<News> getNewsList();
	
	//获取分页统计信息VO
	NewsPage getNewsPage(int currentPage, int pageSize);
	
	
	//下载新闻附件
	void downloadAttachment(User user, int attachmentid, int downloadscore);

	
	//获取新闻评论列表
	List<Comment> getCommentList(int newsid);	

	//添加新闻评论
	boolean addComment(Comment comment, User user, int addCommentScore);

	
}