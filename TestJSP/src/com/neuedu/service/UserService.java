package com.neuedu.service;

import java.util.List;

import com.neuedu.entity.News;
import com.neuedu.entity.User;
import com.neuedu.vo.UserPage;

public interface UserService {

	// 登录
	User login(String username, String password, int score);

	// 注册
	User register(User user);

	// 查找用户
	User findUser(int userid);

	// 更新用户
	boolean updateUser(int userid, String username, String photo, String gender, String job, String interests);

	// 检测用户名是否可用 true---可用 false---已占用
	boolean checkUsername(String username);

	boolean checkUsername(String username, int userid);

	boolean deleteUser(int userid);

	boolean deleteUserBatch(String[] userids);

	// 按用户名模糊查询用户列表
	List<User> getUserList(String username);

	List<User> getUserList(String username, String gender);

	List<User> getUserList(String username, String gender, String job);

	List<User> getUserList(String username, String gender, String job, String begintime, String endtime, int currentPage, int pageSize);

	// 查询用户列表---分页
	// List<User> getUserList(int currentPage, int pageSize);

	// 获取分页统计信息VO
	UserPage getUserPage(String username, String gender, String job, String begintime, String endtime,int currentPage, int pageSize);

	// 更新下载分数
	boolean updateDownloadCount(News news);

}