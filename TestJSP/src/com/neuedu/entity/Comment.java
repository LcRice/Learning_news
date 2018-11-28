package com.neuedu.entity;

import java.util.Date;

public class Comment {
	private int commentid;
	private String content;
	private int score;
	private Date pubtime;
	private News news;
	private User user;
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Date getPubtime() {
		return pubtime;
	}
	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Comment [commentid=" + commentid + ", content=" + content + ", score=" + score + ", pubtime=" + pubtime
				+ ", news=" + news + ", user=" + user + "]";
	}
	
	
}
