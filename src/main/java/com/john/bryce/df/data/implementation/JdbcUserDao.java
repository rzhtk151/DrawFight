package com.john.bryce.df.data.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.john.bryce.df.data.entity.User;
import com.john.bryce.df.data.interfaces.UserDao;
import com.john.bryce.df.util.JdbcUtils;

public final class JdbcUserDao implements UserDao {

    @Override
    public boolean insert(User user) {
        boolean success = true;
        try {
            final String sql = "INSERT INTO " + User.TABLE_NAME + " (" + User.COLUMN_ID + ", " + User.COLUMN_NAME + ", " + User.COLUMN_FCM_TOKEN
                    + ", " + User.COLUMN_IMAGE_URL + ", " + User.COLUMN_SCORE + ") VALUES (\"" + user.getId() + "\", \"" + user.getName()
                    + "\", \"" + user.getFcmToken() + "\", \"" + user.getImageUrl() + "\", " + user.getScore() + ");";

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
    public boolean delete(User user) {
        boolean success = true;
        try {
            final String sql = "DELETE FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_ID + "=" + user.getId() + ";";
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
    public boolean update(User user) {
        boolean success = true;
        try {
            final String sql = "UPDATE " + User.TABLE_NAME + " SET " + User.COLUMN_NAME + "=\"" + user.getName()
                    + "\", " + User.COLUMN_FCM_TOKEN + "=\"" + user.getFcmToken() + "\", " + User.COLUMN_IMAGE_URL
                    + "=\"" + user.getImageUrl() + "\", " + User.COLUMN_SCORE + "=" + user.getScore() + " WHERE "
                    + User.COLUMN_ID + "=" + user.getId() + ";";
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
    public List<User> getAll() {
        List<User> userList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            final String sql = "SELECT * FROM " + User.TABLE_NAME + ";";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            userList = new ArrayList<>();

            final int columnId = resultSet.findColumn(User.COLUMN_ID);
            final int columnName = resultSet.findColumn(User.COLUMN_NAME);
            final int columnFcmToken = resultSet.findColumn(User.COLUMN_FCM_TOKEN);
            final int columnImageUrl = resultSet.findColumn(User.COLUMN_IMAGE_URL);
            final int columnScore = resultSet.findColumn(User.COLUMN_SCORE);

            while (resultSet.next()) {
                final User user = new User();
                user.setId(resultSet.getString(columnId));
                user.setName(resultSet.getString(columnName));
                user.setFcmToken(resultSet.getString(columnFcmToken));
                user.setImageUrl(resultSet.getString(columnImageUrl));
                user.setScore(resultSet.getInt(columnScore));
                userList.add(user);
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
        return userList;
    }

    @Override
    public boolean deleteAll() {
        boolean success = true;

        try {
            final String sql = "DELETE FROM " + User.TABLE_NAME + ";";
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
    public User getById(String id) {
        User user = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            final String sql = "SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_ID + "=\"" + id + "\";";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            final int columnName = resultSet.findColumn(User.COLUMN_NAME);
            final int columnImageUrl = resultSet.findColumn(User.COLUMN_IMAGE_URL);
            final int columnScore = resultSet.findColumn(User.COLUMN_SCORE);

            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setName(resultSet.getString(columnName));
                user.setImageUrl(resultSet.getString(columnImageUrl));
                user.setScore(resultSet.getInt(columnScore));
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
        return user;
    }

    @Override
    public List<User> getAllExcept(String idNotToGet) {
        List<User> userList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            final String sql = "SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_ID + "!=\"" + idNotToGet
                    + "\";";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            userList = new ArrayList<>();

            final int columnId = resultSet.findColumn(User.COLUMN_ID);
            final int columnName = resultSet.findColumn(User.COLUMN_NAME);
            final int columnFcmToken = resultSet.findColumn(User.COLUMN_FCM_TOKEN);
            final int columnImageUrl = resultSet.findColumn(User.COLUMN_IMAGE_URL);
            final int columnScore = resultSet.findColumn(User.COLUMN_SCORE);

            while (resultSet.next()) {
                final User user = new User();
                user.setId(resultSet.getString(columnId));
                user.setName(resultSet.getString(columnName));
                user.setFcmToken(resultSet.getString(columnFcmToken));
                user.setImageUrl(resultSet.getString(columnImageUrl));
                user.setScore(resultSet.getInt(columnScore));
                userList.add(user);
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
        return userList;
    }

}
