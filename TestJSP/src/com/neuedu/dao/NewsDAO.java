package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Attachment;
import com.neuedu.entity.Comment;
import com.neuedu.entity.News;

public interface NewsDAO {

	//插入新闻
	boolean insertNews(News news);
	
	//查询最大的新闻编号
	int findMaxNewsid();
	
	//插入新闻对应的附件
    boolean insertAttachment(Attachment attachment);
		
	
	//查询分页的新闻列表
	List<News> findNewsList(int currentPage, int pageSize);
	
	//查询总记录数
	int findTotalCount();
		
	//根据新闻编号查询新闻对应的附件列表
	List<Attachment> findAttachmentList(int newsid);
	
	
	//更新附件的下载次数
	boolean updateDownloadcount(int attachmentid);
		
	
	
	//查询新闻评论列表
	List<Comment> findCommentList(int newsid);
	
	//插入新闻评论
	boolean insertComment(Comment comment);

		
}