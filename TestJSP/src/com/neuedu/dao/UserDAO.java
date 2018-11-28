package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.News;
import com.neuedu.entity.User;

public interface UserDAO {

	boolean insertUser(User user);

	boolean deleteUser(int userid);

	boolean deleteUserBatch(String[] userids);

	boolean updateUser(User user);

	boolean updateUser(int userid, String username, String photo, String gender, String job, String interests);

	User findUser(int userid);

	List<User> findUser();

	User findUser(String username, String password);

	boolean updateScore(User user, int score);

	boolean findUser(String username);

	boolean findUser(String username, int userid);

	// 模糊查询
	List<User> findUserList(String username);

	List<User> findUserList(String username, String gender);

	List<User> findUserList(String username, String gender, String job);

	List<User> findUserList(String username, String gender, String job, String begintime, String endtime,int currentPage, int pageSize);

	// 分页

	// 查询分页的数据列表
	List<User> findUserList(int currentPage, int pageSize);

	// 查询总总记录数
	int findTotalCount();

	// 更新下载分数
	boolean updateDownloadCount(News news);
	

}