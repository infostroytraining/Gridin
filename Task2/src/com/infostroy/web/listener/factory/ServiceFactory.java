package com.infostroy.web.listener.factory;

import java.util.ServiceConfigurationError;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.infostroy.dao.UserDAO;
import com.infostroy.dao.memory.MemoryUserDAO;
import com.infostroy.dao.postgresql.PostgreSQLUserDAO;
import com.infostroy.dao.storage.UserStorage;
import com.infostroy.db.TransactionManager;
import com.infostroy.service.MemoryUserService;
import com.infostroy.service.TransactionalUserService;
import com.infostroy.service.UserService;

public class ServiceFactory {

	public static final String MEMORY = "memory";
	public static final String DB = "db";
	private static final String POSTGRE_DRIVER = "org.postgresql.Driver";

	private static Logger logger = LogManager.getLogger();

	public static UserService getUserService(String type) {
		if (type == null || type.isEmpty()) {
			logger.fatal("Could initialize application. Source type is null or empty");
			throw new IllegalArgumentException();
		}
		if (type.equals(MEMORY)) {
			return initMemoryService();
		} else if (type.equals(DB)) {
			loadPostgreDriver();
			return initTransactionalService();
		} else {
			logger.fatal("Could initialize application with source type {}", type);
			throw new ServiceConfigurationError("Could initialize application with source type [" + type + "]");
		}
	}

	private static void loadPostgreDriver() {
		try {
			Class.forName(POSTGRE_DRIVER);
		} catch (ClassNotFoundException e) {
			logger.fatal("Could load {} driver", POSTGRE_DRIVER);
			throw new ServiceConfigurationError("Could load" + POSTGRE_DRIVER + "driver");
		}
	}

	private static UserService initMemoryService() {
		UserStorage storage = new UserStorage();
		UserDAO answerDAO = new MemoryUserDAO(storage);
		return new MemoryUserService(answerDAO);
	}

	private static UserService initTransactionalService() {
		TransactionManager transactionManager = new TransactionManager();
		UserDAO answerDAO = new PostgreSQLUserDAO();
		return new TransactionalUserService(transactionManager, answerDAO);
	}
}
