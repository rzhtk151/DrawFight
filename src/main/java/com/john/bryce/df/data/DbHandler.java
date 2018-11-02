package com.john.bryce.df.data;

import com.john.bryce.df.data.implementation.JdbcChatDao;
import com.john.bryce.df.data.implementation.JdbcQuestionDao;
import com.john.bryce.df.data.implementation.JdbcUserDao;
import com.john.bryce.df.data.interfaces.ChatDao;
import com.john.bryce.df.data.interfaces.QuestionDao;
import com.john.bryce.df.data.interfaces.UserDao;

public final class DbHandler {

	public static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String MYSQL_SERVER_URL = "jdbc:mysql://localhost:3306/drawfightnewdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	public static final String MYSQL_USERNAME = "root";
	public static final String MYSQL_PASSWORD = "Shushan3";
	
	private static final DbHandler instance = new DbHandler();
	
	public static DbHandler getInstance() {
		return instance;
	}
	
	private final ChatDao chatDao;
	private final QuestionDao questionDao;
	private final UserDao userDao;
	
	private DbHandler() {
		this.chatDao = new JdbcChatDao();
		this.questionDao = new JdbcQuestionDao();
		this.userDao = new JdbcUserDao();
	}

	public ChatDao getChatDao() {
		return chatDao;
	}

	public QuestionDao getQuestionDao() {
		return questionDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}
	
	public void clear() {
		chatDao.deleteAll();
		userDao.deleteAll();
	}
	
}
