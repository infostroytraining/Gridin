package com.infostroy.dao.memory;

import java.util.List;

import com.infostroy.dao.UserDAO;
import com.infostroy.dao.storage.UserStorage;
import com.infostroy.entity.User;

public class MemoryUserDAO implements UserDAO {

	private UserStorage storage;

	public MemoryUserDAO(UserStorage storage) {
		this.storage = storage;
	}

	@Override
	public User create(User object) {
		return storage.add(object);
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

	@Override
	public List<User> getAll() {
		return storage.all();
	}
}
