package com.infostroy.dao.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.infostroy.dao.UserDAO;
import com.infostroy.entity.User;

public class MemoryUserDAO implements UserDAO {

	List<User> users = new ArrayList<>();

	@Override
	public User create(User object) {
		if (!users.contains(object)) {
			object.setId(UUID.randomUUID().toString());
			users.add(object);
			return object;
		}
		return null;
	}

	@Override
	public User get(int id) {
		return null;
	}

	@Override
	public User update(User object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
