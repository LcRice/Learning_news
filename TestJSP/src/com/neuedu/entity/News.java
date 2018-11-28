package com.neuedu.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class News  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int newsid;
	private String title;
	private String content;
	private Date pubtime;
	private NewsType newstype;
	private User user;
	private long filesize;
	private int commentcount;
	
	private List<Attachment> attachment;
	
	public int getNewsid() {
		return newsid;
	}
	public void setNewsid(int newsid) {
		this.newsid = newsid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPubtime() {
		return pubtime;
	}
	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}
	public NewsType getNewstype() {
		return newstype;
	}
	public void setNewstype(NewsType newstype) {
		this.newstype = newstype;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public int getCommentcount() {
		return commentcount;
	}
	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}
	public List<Attachment> getAttachment() {
		return attachment;
	}
	public void setAttachment(List<Attachment> attachment) {
		this.attachment = attachment;
	}
	
	public News(String title, String content, Date pubtime, NewsType newstype) {
		super();
		this.title = title;
		this.content = content;
		this.pubtime = pubtime;
		this.newstype = newstype;
	}
	public News(String title, String content, Date pubtime, NewsType newstype, User user) {
		super();
		this.title = title;
		this.content = content;
		this.pubtime = pubtime;
		this.newstype = newstype;
		this.user = user;
	}
	
	public News(String title, String content, Date pubtime, NewsType newstype, String truename, String attachment,
			User user, long filesize,int download,int downloadcount,int commentcount) {
		super();
		this.title = title;
		this.content = content;
		this.pubtime = pubtime;
		this.newstype = newstype;
		this.user = user;
		this.filesize = filesize;
		this.commentcount = commentcount;
	}
	public News() {
		super();
	}
	
	@Override
	public String toString() {
		return "News [newsid=" + newsid + ", title=" + title + ", content=" + content + ", pubtime=" + pubtime
				+ ", newstype=" + newstype + "]";
	}
	
	
}
