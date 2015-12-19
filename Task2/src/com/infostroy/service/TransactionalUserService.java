package com.infostroy.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.infostroy.dao.UserDAO;
import com.infostroy.db.TransactionManager;
import com.infostroy.db.exception.TransactionException;
import com.infostroy.entity.User;
import com.infostroy.service.exception.ServiceException;

public class TransactionalUserService implements UserService {

	private TransactionManager transactionManager;
	private UserDAO userDAO;

	public TransactionalUserService(TransactionManager transactionManager, UserDAO answerDAO) {
		this.transactionManager = transactionManager;
		this.userDAO = answerDAO;
	}

	@Override
	public User add(final User answer) throws ServiceException {
		try {
			return transactionManager.doTask(() -> userDAO.create(answer), Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}
	}

	public Map<String, Integer> getStatisticForEachAnswer() {
		return new HashMap<>();
	}

	@Override
	public User update(User user) throws ServiceException {
		try {
			return transactionManager.doTask(() -> userDAO.update(user), Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(User user) throws ServiceException {
		try {
			transactionManager.doTask(() -> userDAO.delete(1), Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User get(int id) throws ServiceException {
		try {
			return transactionManager.doTask(() -> userDAO.get(id), Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> getAll() throws ServiceException {
		try {
			return transactionManager.doTask(() -> userDAO.getAll(), Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}
	}
}
