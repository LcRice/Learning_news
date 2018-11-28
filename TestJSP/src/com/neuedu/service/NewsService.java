package com.neuedu.service;

import java.util.List;

import com.neuedu.entity.Comment;
import com.neuedu.entity.News;
import com.neuedu.entity.User;
import com.neuedu.vo.NewsPage;

public interface NewsService {

	//�������
	boolean addNews(News news, User user, int addNewsScore);
	
	
	//��ȡȫ���������б�
	//List<News> getNewsList();
	
	//��ȡ��ҳͳ����ϢVO
	NewsPage getNewsPage(int currentPage, int pageSize);
	
	
	//�������Ÿ���
	void downloadAttachment(User user, int attachmentid, int downloadscore);

	
	//��ȡ���������б�
	List<Comment> getCommentList(int newsid);	

	//�����������
	boolean addComment(Comment comment, User user, int addCommentScore);

	
}