package com.john.bryce.df.data.interfaces;

import java.util.List;

public interface Dao<T> {

	boolean insert(T t);

	boolean delete(T t);

	boolean update(T t);

	boolean deleteAll();

	List<T> getAll();

}
