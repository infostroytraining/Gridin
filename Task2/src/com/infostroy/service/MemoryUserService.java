package com.infostroy.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.infostroy.dao.UserDAO;
import com.infostroy.dao.exception.DAOException;
import com.infostroy.entity.User;
import com.infostroy.service.exception.ServiceException;

public class MemoryUserService implements UserService {

	private UserDAO userDAO;

	public MemoryUserService(UserDAO answerDAO) {
		this.userDAO = answerDAO;
	}

	public List<User> getAll() throws DAOException {
		return userDAO.getAll();
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

	/**
	 * This method returns a map with programming language name as a key and
	 * count of answers for this language as a value.
	 * 
	 * @throws DAOException
	 */
	public Map<String, Integer> getStatisticForEachAnswer() throws ServiceException {
		Map<String, Integer> statisticMap = new HashMap<>();
		Set<String> languages = new HashSet<>();
		try {
			for (User answer : getAll()) {
				if (answer != null) {
					// languages.add(answer.getLanguage());
				}
			}
			for (String language : languages) {
				int answersCount = 0;
				if (language != null) {
					for (User answer : getAll()) {
						// if (answer != null &&
						// language.equals(answer.getLanguage())) {
						// answersCount += 1;
						// }
						statisticMap.put(language, answersCount);
					}
				}
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return statisticMap;
	}
}
