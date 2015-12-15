package com.infostroy.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.infostroy.dao.UserDAO;
import com.infostroy.dao.exception.DAOException;
import com.infostroy.db.Transaction;
import com.infostroy.db.TransactionManager;
import com.infostroy.db.exception.TransactionException;
import com.infostroy.entity.User;
import com.infostroy.service.exception.ServiceException;

public class TransactionalUserService implements UserService {

	private TransactionManager transactionManager;
	private UserDAO answerDAO;

	public TransactionalUserService(TransactionManager transactionManager, UserDAO answerDAO) {
		this.transactionManager = transactionManager;
		this.answerDAO = answerDAO;
	}

	@Override
	public User add(final User answer) throws ServiceException {
		try {
			return transactionManager.doTask(new Transaction<User>() {
				@Override
				public User execute() throws DAOException {
					return answerDAO.create(answer);
				}
			}, Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}
	}

	public Map<String, Integer> getStatisticForEachAnswer() {
		return new HashMap<>();
	}
}
