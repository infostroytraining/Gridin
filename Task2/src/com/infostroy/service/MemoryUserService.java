package com.infostroy.service;

import java.util.List;

import com.infostroy.dao.UserDAO;
import com.infostroy.dao.exception.DAOException;
import com.infostroy.entity.User;
import com.infostroy.service.exception.ServiceException;

public class MemoryUserService implements UserService {

	private UserDAO userDAO;

	public MemoryUserService(UserDAO answerDAO) {
		this.userDAO = answerDAO;
	}

	public List<User> getAll() throws ServiceException {
		try {
			return userDAO.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User add(User answer) throws ServiceException {
		User createdAnswer = null;
		if (answer != null) {
			try {
				createdAnswer = userDAO.create(answer);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return createdAnswer;
	}

	@Override
	public User update(User user) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User user) throws ServiceException {
		// TODO Auto-generated method stub
	}

	@Override
	public User get(int id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
