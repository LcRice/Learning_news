package com.neuedu.Listener;

import java.util.TimerTask;

import com.neuedu.service.NewsService;
import com.neuedu.service.impl.NewsServiceImpl;

public class MyTimerTask extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		NewsService newsService = new NewsServiceImpl();
		/*if(newsService.updateCommentCount()){
			System.out.println("更新成功");
		}else{
			System.out.println("更新失败");
		}*/
	}

}
