package com.infostroy.db;

import java.sql.Connection;

public class ConnectionHolder {

	private static final ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();

	public final static void setConnection(Connection connection) {
		connectionHolder.set(connection);
	}

	public final static Connection getConnection() {
		return connectionHolder.get();
	}

}
