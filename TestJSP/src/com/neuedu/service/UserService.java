package com.neuedu.service;

import java.util.List;

import com.neuedu.entity.News;
import com.neuedu.entity.User;
import com.neuedu.vo.UserPage;

public interface UserService {

	// ��¼
	User login(String username, String password, int score);

	// ע��
	User register(User user);

	// �����û�
	User findUser(int userid);

	// �����û�
	boolean updateUser(int userid, String username, String photo, String gender, String job, String interests);

	// ����û����Ƿ���� true---���� false---��ռ��
	boolean checkUsername(String username);

	boolean checkUsername(String username, int userid);

	boolean deleteUser(int userid);

	boolean deleteUserBatch(String[] userids);

	// ���û���ģ����ѯ�û��б�
	List<User> getUserList(String username);

	List<User> getUserList(String username, String gender);

	List<User> getUserList(String username, String gender, String job);

	List<User> getUserList(String username, String gender, String job, String begintime, String endtime, int currentPage, int pageSize);

	// ��ѯ�û��б�---��ҳ
	// List<User> getUserList(int currentPage, int pageSize);

	// ��ȡ��ҳͳ����ϢVO
	UserPage getUserPage(String username, String gender, String job, String begintime, String endtime,int currentPage, int pageSize);

	// �������ط���
	boolean updateDownloadCount(News news);

}