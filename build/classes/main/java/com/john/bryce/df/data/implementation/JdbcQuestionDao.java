package com.john.bryce.df.data.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.john.bryce.df.data.entity.Question;
import com.john.bryce.df.data.interfaces.QuestionDao;
import com.john.bryce.df.util.JdbcUtils;

public final class JdbcQuestionDao implements QuestionDao {

	@Override
	public boolean insert(Question question) {
		boolean success = true;
		try {
			final String sql = "INSERT INTO " + Question.TABLE_NAME + " (" + Question.COLUMN_WORD + ") VALUES (\""
					+ question.getWord() + "\");";

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
	public boolean delete(Question question) {
		boolean success = true;
		try {
			final String sql = "DELETE FROM " + Question.TABLE_NAME + " WHERE " + Question.COLUMN_ID + "="
					+ question.getId() + ";";
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
	public boolean update(Question question) {
		boolean success = true;
		try {
			final String sql = "UPDATE " + Question.TABLE_NAME + " SET " + Question.COLUMN_WORD + "=\""
					+ question.getWord() + " WHERE " + Question.COLUMN_ID + "=" + question.getId() + ";";
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
	public List<Question> getAll() {
		List<Question> questionList = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtils.getConnection();
			final String sql = "SELECT * FROM " + Question.TABLE_NAME + ";";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			questionList = new ArrayList<>();

			final int columnId = resultSet.findColumn(Question.COLUMN_ID);
			final int columnWord = resultSet.findColumn(Question.COLUMN_WORD);

			while (resultSet.next()) {
				final Question question = new Question();
				question.setId(resultSet.getLong(columnId));
				question.setWord(resultSet.getString(columnWord));
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
		return questionList;
	}

	@Override
	public boolean deleteAll() {
		boolean success = true;
		
		try {
			final String sql = "DELETE FROM " + Question.TABLE_NAME + ";";
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
	public List<Question> getRandom(int count) {
		List<Question> questionList = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtils.getConnection();
			final String sql = "SELECT * FROM " + Question.TABLE_NAME + " ORDER BY RAND() LIMIT " + count + ";";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			questionList = new ArrayList<>();

			final int columnId = resultSet.findColumn(Question.COLUMN_ID);
			final int columnWord = resultSet.findColumn(Question.COLUMN_WORD);

			while (resultSet.next()) {
				final Question question = new Question();
				question.setId(resultSet.getLong(columnId));
				question.setWord(resultSet.getString(columnWord));
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
		return questionList;
	}

}
