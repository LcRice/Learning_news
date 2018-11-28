package com.neuedu.service.impl;

import java.util.List;

import com.neuedu.dao.UserDAO;
import com.neuedu.dao.impl.UserDAOImpl;
import com.neuedu.entity.News;
import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import com.neuedu.vo.UserPage;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO = new UserDAOImpl(); // 组合

	@Override
	public User login(String username, String password, int score) {
		// return userDAO.findUser(username, password);

		// 查询用户
		User user = userDAO.findUser(username, password);
		if (user != null) {

			if (userDAO.updateScore(user, score)) { // 更新数据库的积分

				// 更新当前对象的积分
				user.setScore(user.getScore() + score);

				return user;
			}
		}

		return null;

	}

	@Override
	public User register(User user) {

		if (userDAO.insertUser(user)) { // 插入成功

			return userDAO.findUser(user.getUsername(), user.getPassword());

		}

		return null;
	}

	@Override
	public boolean checkUsername(String username) {

		return !userDAO.findUser(username);
	}

	@Override
	public List<User> getUserList(String username) {
		return userDAO.findUserList(username);
	}

	// @Override
	// public List<User> getUserList(int currentPage, int pageSize) {
	// return userDAO.findUserList(currentPage, pageSize);
	// }

	@Override
	public UserPage getUserPage(String username, String gender, String job, String begintime, String endtime, int currentPage, int pageSize) {

		UserPage userPage = new UserPage();

		userPage.setPageSize(pageSize);

		userPage.setCurrentPage(currentPage);

		int totalCount = userDAO.findTotalCount();
		userPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		userPage.setTotalPage(totalPage);

		userPage.setDataList(userDAO.findUserList(username, gender, job, begintime, endtime,currentPage,pageSize));

		int pageNumber = 3;

		int begin = currentPage - pageNumber / 2;
		int end = currentPage + pageNumber / 2;

		if (begin < 1) {
			begin = 1;
			end = pageNumber;
		}

		if (end > totalPage) {
			begin = totalPage - pageNumber + 1;
			end = totalPage;
		}

		if (totalPage < pageNumber) {
			begin = 1;
			end = totalPage;
		}
		userPage.setBegin(begin);
		userPage.setEnd(end);

		return userPage;
	}

	public static void main(String[] args) {

		UserService userService = new UserServiceImpl();

		User user = userService.login("user1", "1", 2);

		if (user != null) {
			System.out.println("登录成功，您的当前积分为" + user.getScore());
		} else {
			System.out.println("登录失败，请检查用户名呵呵密码是否输入正确");
		}

	}

	@Override
	public List<User> getUserList(String username, String gender) {
		// TODO Auto-generated method stub
		return userDAO.findUserList(username, gender);
	}

	@Override
	public List<User> getUserList(String username, String gender, String job) {
		// TODO Auto-generated method stub
		return userDAO.findUserList(username, gender, job);
	}

	@Override
	public List<User> getUserList(String username, String gender, String job, String begintime, String endtime, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return userDAO.findUserList(username, gender, job, begintime, endtime,currentPage,pageSize);
	}

	@Override
	public boolean deleteUser(int userid) {
		return userDAO.deleteUser(userid);
	}

	@Override
	public boolean deleteUserBatch(String[] userids) {
		return userDAO.deleteUserBatch(userids);
	}

	@Override
	public User findUser(int userid) {
		return userDAO.findUser(userid);
	}

	@Override
	public boolean updateUser(int userid, String username, String photo, String gender, String job, String interests) {
		return userDAO.updateUser(userid, username, photo, gender, job, interests);
	}

	@Override
	public boolean checkUsername(String username, int userid) {
		// TODO Auto-generated method stub
		return !userDAO.findUser(username, userid);
	}

	@Override
	public boolean updateDownloadCount(News news) {
		// TODO Auto-generated method stub
		return userDAO.updateDownloadCount(news);
	}
}