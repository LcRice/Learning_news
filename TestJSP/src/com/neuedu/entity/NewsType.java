package com.neuedu.entity;

import java.io.Serializable;

public class NewsType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int typeid;
	private String typename;
	
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	public NewsType() {
		super();
	}
	public NewsType(int typeid, String typename) {
		super();
		this.typeid = typeid;
		this.typename = typename;
	}
	
	@Override
	public String toString() {
		return "NewsType [typeid=" + typeid + ", typename=" + typename + "]";
	}
	
	
}
