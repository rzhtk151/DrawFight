package com.john.bryce.df.data.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.john.bryce.df.data.entity.Chat;
import com.john.bryce.df.data.interfaces.ChatDao;
import com.john.bryce.df.util.JdbcUtils;

public final class JdbcChatDao implements ChatDao {

	@Override
	public boolean insert(Chat chat) {
		boolean success = true;
		try {
			final String sql = "INSERT INTO " + Chat.TABLE_NAME + " (" + Chat.COLUMN_USER_ID + ", "
					+ Chat.COLUMN_MESSAGE + ", " + Chat.COLUMN_TIME_STAMP + ") VALUES (\"" + chat.getUserId() + "\", \""
					+ chat.getMessage() + "\", " + chat.getTimeStamp() + ");";

			JdbcUtils.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			success = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			success = false;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	@Override
	public boolean delete(Chat chat) {
		boolean success = true;
		try {
			final String sql = "DELETE FROM " + Chat.TABLE_NAME + " WHERE " + Chat.COLUMN_ID + "=" + chat.getId() + ";";
			JdbcUtils.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			success = false;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	@Override
	public boolean update(Chat chat) {
		boolean success = true;
		try {
			final String sql = "UPDATE " + Chat.TABLE_NAME + " SET " + Chat.COLUMN_USER_ID + "=\"" + chat.getUserId()
					+ "\", " + Chat.COLUMN_MESSAGE + "=\"" + chat.getMessage() + "\", " + Chat.COLUMN_TIME_STAMP + "="
					+ chat.getTimeStamp() + " WHERE " + Chat.COLUMN_ID + "=" + chat.getId() + ";";
			JdbcUtils.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			success = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			success = false;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	@Override
	public List<Chat> getAll() {
		List<Chat> chatList = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtils.getConnection();
			final String sql = "SELECT * FROM " + Chat.TABLE_NAME + ";";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			chatList = new ArrayList<>();
			
			final int columnId = resultSet.findColumn(Chat.COLUMN_ID);
			final int columnUserId = resultSet.findColumn(Chat.COLUMN_USER_ID);
			final int columnMessage = resultSet.findColumn(Chat.COLUMN_MESSAGE);
			final int columnTimeStamp = resultSet.findColumn(Chat.COLUMN_TIME_STAMP);
			
			while (resultSet.next()) {
				final Chat chat = new Chat();
				chat.setId(resultSet.getLong(columnId));
				chat.setUserId(resultSet.getString(columnUserId));
				chat.setMessage(resultSet.getString(columnMessage));
				chat.setTimeStamp(resultSet.getLong(columnTimeStamp));
				chatList.add(chat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeStatement(statement);
			JdbcUtils.closeResultSet(resultSet);
			JdbcUtils.closeConnection(connection);
		}
		return chatList;
	}

	@Override
	public boolean deleteAll() {
		boolean success = true;
		
		try {
			final String sql = "DELETE FROM " + Chat.TABLE_NAME + ";";
			JdbcUtils.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			success = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			success = false;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}

		return success;
	}


}
