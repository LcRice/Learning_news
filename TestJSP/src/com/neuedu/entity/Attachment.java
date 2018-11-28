package com.neuedu.entity;

import java.io.Serializable;

public class Attachment implements Serializable {
	private int attachmentid;
	private String attachment;
	private String truename;
	private int download;
	private int downloadcount;
	private int newsid;
	public int getAttachmentid() {
		return attachmentid;
	}
	public void setAttachmentid(int attachmentid) {
		this.attachmentid = attachmentid;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public int getDownload() {
		return download;
	}
	public void setDownload(int download) {
		this.download = download;
	}
	public int getDownloadcount() {
		return downloadcount;
	}
	public void setDownloadcount(int downloadcount) {
		this.downloadcount = downloadcount;
	}
	public int getNewsid() {
		return newsid;
	}
	public void setNewsid(int newsid) {
		this.newsid = newsid;
	}
	
	
	
	
}
