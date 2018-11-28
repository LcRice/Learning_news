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

	// ģ����ѯ
	List<User> findUserList(String username);

	List<User> findUserList(String username, String gender);

	List<User> findUserList(String username, String gender, String job);

	List<User> findUserList(String username, String gender, String job, String begintime, String endtime,int currentPage, int pageSize);

	// ��ҳ

	// ��ѯ��ҳ�������б�
	List<User> findUserList(int currentPage, int pageSize);

	// ��ѯ���ܼ�¼��
	int findTotalCount();

	// �������ط���
	boolean updateDownloadCount(News news);
	

}