package com.neuedu.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.NewsDAO;
import com.neuedu.dbutil.DBManager;
import com.neuedu.entity.Attachment;
import com.neuedu.entity.Comment;
import com.neuedu.entity.News;
import com.neuedu.entity.NewsType;
import com.neuedu.entity.User;

public class NewsDAOImpl implements NewsDAO {

	private DBManager dbManager = DBManager.getInstance();
	
	@Override
	public boolean insertNews(News news) {
		
		String sql = "insert into news values(null, ?, ?, ?, ?, ?, ?)";
		return dbManager.execUpdate(sql, 
									news.getTitle(), news.getContent(), news.getPubtime(),
									news.getNewstype().getTypeid(), news.getUser().getUserid(), 0);
		
	}

	@Override
	public int findMaxNewsid() {
		
		String sql = "select max(newsid) from news";
		
		ResultSet rs = dbManager.execQuery(sql);
		
		try {
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	
	@Override
	public boolean insertAttachment(Attachment attachment) {
		String sql = "insert into attachment values(null, ?, ?, ?, ? ,?)";
		return dbManager.execUpdate(sql, attachment.getAttachment(), attachment.getTruename(), 
										 attachment.getDownload(), attachment.getDownloadcount(),
										 attachment.getNewsid());
	}
	
	@Override
	public List<News> findNewsList(int currentPage, int pageSize) {
	
		//三表连接
//		String sql = "select newsid, title, content, pubtime, typename, username, photo, attachment, truename, downloadscore, downloadcount";
		String sql = "select newsid, title, content, pubtime, typename, username, photo";
		sql+=" from news join newstype as nt on news.typeid = nt.typeid";
		sql+=" join user on news.userid = user.userid";
		sql+=" order by newsid";
		sql+=" limit ?, ?";
		
		ResultSet rs = dbManager.execQuery(sql, (currentPage-1) * pageSize, pageSize);
		
		List<News> list = new ArrayList<>();
		
		try {
			
			while(rs.next()){
				
				News news = new News();
				
				news.setNewsid(rs.getInt(1));
				news.setTitle(rs.getString(2));
				news.setContent(rs.getString(3));
				news.setPubtime(rs.getTimestamp(4));
				
				//news.setTypeid(rs.getInt(5));
				
				//news对象中组合了newstype对象
				NewsType newsType = new NewsType();
				newsType.setTypename(rs.getString(5));
				news.setNewstype(newsType);
				
				//news.setUserid(rs.getInt(6));
				User user = new User();
				user.setUsername(rs.getString(6));
				user.setPhoto(rs.getString(7));
				news.setUser(user);
				
				/*news.setAttachment(rs.getString(8));
				news.setTruename(rs.getString(9));
				news.setDownloadscore(rs.getInt(10));
				news.setDownloadcount(rs.getInt(11));*/
				
				list.add(news);
			}
			
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbManager.closeConnection();
		}
		
		return null;
	}

	//*******************
	@Override
	public List<Attachment> findAttachmentList(int newsid) {
		
		String sql = "select * from attachment where newsid = ?";
		
		ResultSet rs = dbManager.execQuery(sql, newsid);
		
		List<Attachment> list = new ArrayList<>();
		
		try {
			
			while(rs.next()){
				
				Attachment attachment = new Attachment();
				
				attachment.setAttachmentid(rs.getInt(1));
				attachment.setAttachment(rs.getString(2));
				attachment.setTruename(rs.getString(3));
				attachment.setDownload(rs.getInt(4));
				attachment.setDownloadcount(rs.getInt(5));
				attachment.setNewsid(newsid);
			
				list.add(attachment);
			}
			
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbManager.closeConnection();
		}
		return null;
	}


	
	@Override
	public boolean updateDownloadcount(int attachmentid) {
			
		String sql = "update attachment set downloadcount = downloadcount + 1 where attachmentid = ?";
			
		return dbManager.execUpdate(sql, attachmentid);
	}

	
	@Override
	public int findTotalCount() {

		String sql = "select count(*) from news";
		ResultSet rs = dbManager.execQuery(sql);
		
		try {
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbManager.closeConnection();
		}
		
		return -1;
	}

	@Override
	public List<Comment> findCommentList(int newsid) {
		
		String sql = "select commentid, content, comment.score, pubtime, username, photo";
		sql += " from comment join user on comment.userid = user.userid";
		sql += " where newsid = ?"; 
		sql += " order by pubtime desc";		
		
		ResultSet rs = dbManager.execQuery(sql, newsid);
		
		try {
			
			List<Comment> list = new ArrayList<Comment>();
			
			while(rs.next()){   
				
				//创建并填充实体类
				Comment comment = new Comment();
				
				comment.setCommentid(rs.getInt(1));
				
				comment.setContent(rs.getString(2));
				comment.setScore(rs.getInt(3));
				comment.setPubtime(rs.getTimestamp(4));
				
				User user = new User();
				user.setUsername(rs.getString(5));
				user.setPhoto(rs.getString(6));
				comment.setUser(user);
				
				list.add(comment);
			}

			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbManager.closeConnection();
		}
		
		return null;		
	}

	@Override
	public boolean insertComment(Comment comment) {
	
		String sql = "insert into comment values(null, ?, ?, ?, ?, ?)";
		
		return dbManager.execUpdate(sql, comment.getContent(), comment.getScore(), comment.getPubtime(), comment.getNews().getNewsid(), comment.getUser().getUserid());
	}



}