package com.john.bryce.df.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.john.bryce.df.data.DbHandler;

public final class JdbcUtils {

	private JdbcUtils() {
	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(DbHandler.MYSQL_JDBC_DRIVER);
		return DriverManager.getConnection(DbHandler.MYSQL_SERVER_URL, DbHandler.MYSQL_USERNAME,
				DbHandler.MYSQL_PASSWORD);
	}

	public static void closeConnection(final Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeStatement(final Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeResultSet(final ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void execute(String sql) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
		} finally {
			closeConnection(connection);
			closeStatement(preparedStatement);
		}
	}
	
	public static void executeUpdate(String sql) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} finally {
			closeConnection(connection);
			closeStatement(preparedStatement);
		}
	}
	
	public static boolean deleteAll(String tableName) {
		boolean success = true;
		
		try {
			final String sql = "delete from " + tableName + ";";
			executeUpdate(sql);
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
