package com.neuedu.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.NewsTypeDAO;
import com.neuedu.dbutil.DBManager;
import com.neuedu.entity.NewsType;
import com.neuedu.entity.User;

public class NewsTypeDAOImpl implements NewsTypeDAO {

	private DBManager dbManager = DBManager.getInstance(); // 组合

	@Override
	public int findAllLine() {
		String sql = "select count(*) from newstype";

		ResultSet rs = dbManager.execQuery(sql);

		try {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection();
		}

		return -1;
	}

	@Override
	public List<NewsType> findAllType() {
		String sql = "select * from newstype";

		ResultSet rs = dbManager.execQuery(sql);

		if (rs != null) {
			try {

				List<NewsType> list = new ArrayList<>();

				while (rs.next()) { // 有数据

					// 创建并填充实体类
					NewsType newsType = new NewsType();

					newsType.setTypeid(rs.getInt(1));
					newsType.setTypename(rs.getString(2));

					// 添加到列表中
					list.add(newsType);
				}

				return list;

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				// 关闭数据库连接
				dbManager.closeConnection();
			}
		}
		return null;
	}

	@Override
	public NewsType findNewsTypeById(int typeid) {
		String sql = "select * from newstype where typeid = ?";

		ResultSet rs = dbManager.execQuery(sql, typeid);

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据

					// 创建并填充实体类
					NewsType newsType = new NewsType();

					newsType.setTypeid(rs.getInt(1));
					newsType.setTypename(rs.getString(2));
					
					return newsType;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				// 关闭数据库连接
				dbManager.closeConnection();
			}
		}
		return null;
	}

}
