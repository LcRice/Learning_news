package com.neuedu.test;

import com.neuedu.dbutil.DBManager;

public class BatchInsertNews {

	public static void main(String[] args) {
		
		DBManager dbManager = DBManager.getInstance();
		
		String sql = "insert into news values(null, 6, ?, ?, now(), '', '', 246, 0, 0, 0)";

		for(int i=1; i<100; i++){
		
			String title = "��������" + i;

			String content = "��������" + i;
			
			dbManager.execUpdate(sql, title, content);
		}
	}
}
