package com.neuedu.entity;
 
import java.io.Serializable;
import java.util.Date;
 
public class User implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userid;
	private String username;
	private String password;
	private Integer score;
	private String photo;
	private String gender;
	private String job;
	private String interest;
	private Date regtime;
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public Date getRegtime() {
		return regtime;
	}
	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}
	
	public User(String username, String password, Integer score, String photo, String gender, String job,
			String interest, Date regtime) {
		super();
		this.username = username;
		this.password = password;
		this.score = score;
		this.photo = photo;
		this.gender = gender;
		this.job = job;
		this.interest = interest;
		this.regtime = regtime;
	}
	public User(){
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", score=" + score
				+ ", photo=" + photo + ", gender=" + gender + ", job=" + job + ", interest=" + interest + ", regtime="
				+ regtime + "]";
	}
	
	
	
	
	
	
	
}