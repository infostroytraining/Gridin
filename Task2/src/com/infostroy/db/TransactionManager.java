package com.infostroy.db;

import com.infostroy.dao.exception.DAOException;
import com.infostroy.db.exception.TransactionException;

public class TransactionManager {

	public <T> T doTask(Transaction<T> transaction, int transactionIsolation) throws TransactionException {
		try {
			// initialize connection with DB
			T result = transaction.execute();
			// close connection with DB
			return result;
		} catch (DAOException e) {
			throw new TransactionException(e);
		}
	}
}
