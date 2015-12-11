package com.infostroy.dao;

public interface DAO<T> {

	T create(T object);

	T get(int id);

	T update(T object);

	boolean delete(int id);
}
