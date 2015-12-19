package com.infostroy.service;

import java.util.List;

import com.infostroy.entity.User;
import com.infostroy.service.exception.ServiceException;

public interface UserService {

	User add(User answer) throws ServiceException;

	User update(User user) throws ServiceException;

	void delete(User user) throws ServiceException;

	User get(int id) throws ServiceException;

	List<User> getAll() throws ServiceException;
}
