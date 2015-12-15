package com.infostroy.dao;

import java.util.List;

import com.infostroy.dao.exception.DAOException;
import com.infostroy.entity.User;

public interface DAO<T> {

	T create(T object) throws DAOException;

	T get(int id);

	T update(T object);

	boolean delete(int id);

	List<User> getAll();
}
