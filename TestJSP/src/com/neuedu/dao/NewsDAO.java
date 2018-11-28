package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Attachment;
import com.neuedu.entity.Comment;
import com.neuedu.entity.News;

public interface NewsDAO {

	//��������
	boolean insertNews(News news);
	
	//��ѯ�������ű��
	int findMaxNewsid();
	
	//�������Ŷ�Ӧ�ĸ���
    boolean insertAttachment(Attachment attachment);
		
	
	//��ѯ��ҳ�������б�
	List<News> findNewsList(int currentPage, int pageSize);
	
	//��ѯ�ܼ�¼��
	int findTotalCount();
		
	//�������ű�Ų�ѯ���Ŷ�Ӧ�ĸ����б�
	List<Attachment> findAttachmentList(int newsid);
	
	
	//���¸��������ش���
	boolean updateDownloadcount(int attachmentid);
		
	
	
	//��ѯ���������б�
	List<Comment> findCommentList(int newsid);
	
	//������������
	boolean insertComment(Comment comment);

		
}