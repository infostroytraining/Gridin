package com.infostroy.dao.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.infostroy.entity.User;

public class UserStorage {

	private Map<Integer, User> storage;
	private AtomicInteger id = new AtomicInteger();

	public UserStorage() {
		storage = new HashMap<>();
	}

	public User add(User answer) {
		int id = generateId();
		answer.setId(id);
		storage.put(id, answer);
		return answer;
	}

	public List<User> all() {
		return new ArrayList<User>(storage.values());
	}

	private int generateId() {
		return id.incrementAndGet();
	}
}
