package com.john.bryce.df.data.interfaces;

import java.util.List;

import com.john.bryce.df.data.entity.User;

public interface UserDao extends Dao<User> {
	User getById(String id);

	List<User> getAllExcept(String idNotToGet);
}
