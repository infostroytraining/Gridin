package com.infostroy.service;

import java.util.Map;

import com.infostroy.entity.User;
import com.infostroy.service.exception.ServiceException;

public interface UserService {

	public User add(User answer) throws ServiceException;

	public Map<String, Integer> getStatisticForEachAnswer() throws ServiceException;
}
