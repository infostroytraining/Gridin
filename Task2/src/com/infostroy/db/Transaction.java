package com.infostroy.db;

import com.infostroy.dao.exception.DAOException;

public interface Transaction<T> {

	public T execute() throws DAOException;

}
