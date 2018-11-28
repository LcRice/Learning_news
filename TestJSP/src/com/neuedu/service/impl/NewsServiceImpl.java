package com.neuedu.service.impl;

import java.util.List;

import com.neuedu.dao.NewsDAO;
import com.neuedu.dao.UserDAO;
import com.neuedu.dao.impl.NewsDAOImpl;
import com.neuedu.dao.impl.UserDAOImpl;
import com.neuedu.entity.Attachment;
import com.neuedu.entity.Comment;
import com.neuedu.entity.News;
import com.neuedu.entity.User;
import com.neuedu.service.NewsService;
import com.neuedu.vo.NewsPage;

public class NewsServiceImpl implements NewsService {

	private NewsDAO newsDAO = new NewsDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();
	
	
	@Override
	public boolean addNews(News news, User user, int addNewsScore) {
		
		if(newsDAO.insertNews(news)){   //插入新闻主表记录
			
			//查询最大的新闻编号
			int maxNewsid = newsDAO.findMaxNewsid();
			
			//插入多条附件子表记录
			for(Attachment attachment : news.getAttachment()){
				
				//填充附件所属的新闻编号外键
				attachment.setNewsid(maxNewsid);
				
				//插入附件记录
				if(!newsDAO.insertAttachment(attachment)){
					return false;
				}
			}
			
			if(userDAO.updateScore(user, addNewsScore)){  //更新数据库的积分
				
				//更新当前对象的积分 
				user.setScore(user.getScore() + addNewsScore);
				
				return true;
			}
		}
		
		return false;
	}


	/*@Override
	public List<News> getNewsList() {
		return newsDAO.findNewsList();
	}*/

	@Override
	public NewsPage getNewsPage(int currentPage, int pageSize) {
		
		NewsPage newsPage = new NewsPage();
		
		newsPage.setPageSize(pageSize);
		
		newsPage.setCurrentPage(currentPage);
		
		int totalCount = newsDAO.findTotalCount();
		newsPage.setTotalCount(totalCount);
		
		int totalPage = totalCount % pageSize ==0 ?  totalCount / pageSize : totalCount / pageSize + 1;
		newsPage.setTotalPage(totalPage);
		
		
		//********************
		//newsPage.setDataList(newsDAO.findNewsList(currentPage, pageSize));
		List<News> newsList = newsDAO.findNewsList(currentPage, pageSize);
		
		for(News news : newsList){
			
			List<Attachment> attachments = newsDAO.findAttachmentList(news.getNewsid());
			
			news.setAttachment(attachments);
			
		}
		
		newsPage.setDataList(newsList);
		
		
		
		
		int pageNumer = 5;
		
		//计算页码
		int begin = currentPage - pageNumer / 2;
		int end = currentPage + pageNumer / 2;
		
		//调整开始页码
		if(begin<1){
			begin = 1;
			end = pageNumer;
		}
		
		//调整结束页码
		if(end>totalPage){
			end = totalPage;
			begin = totalPage - pageNumer + 1;
		}
		
		//调整页码
		if(totalPage<pageNumer){
			begin = 1;
			end = totalPage;
		}
		
		newsPage.setBegin(begin);
		newsPage.setEnd(end);
		
		return newsPage;
	}

	@Override
	public void downloadAttachment(User user, int attachmentid, int download) {
		
		if(newsDAO.updateDownloadcount(attachmentid)){
			
			if(userDAO.updateScore(user, -1 * download)){
				user.setScore(user.getScore() - download);
			}
			
		}
		
	}


	@Override
	public List<Comment> getCommentList(int newsid) {
		return newsDAO.findCommentList(newsid);
	}

	@Override
	public boolean addComment(Comment comment, User user, int addCommentScore) {

		if(newsDAO.insertComment(comment)){   //插入评论成功
			
			if(userDAO.updateScore(user, addCommentScore)){  //给用户加分成功
				
				//给当前用户对象加分
				user.setScore(user.getScore() + addCommentScore);
				
				return true;
			}
		}
		
		return false;
	}


}