package com.infostroy.dao;

import java.util.List;

import com.infostroy.dao.exception.DAOException;
import com.infostroy.entity.User;

public interface DAO<T> {

	T create(T object) throws DAOException;

	T get(int id) throws DAOException;

	T update(T object) throws DAOException;

	boolean delete(int id) throws DAOException;

	List<User> getAll() throws DAOException;
}
