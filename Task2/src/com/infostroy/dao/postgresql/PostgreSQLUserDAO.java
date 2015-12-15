package com.infostroy.dao.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.infostroy.dao.UserDAO;
import com.infostroy.dao.exception.DAOException;
import com.infostroy.db.ConnectionHolder;
import com.infostroy.entity.User;
import com.infostroy.utils.EncryptDecrypt;

public class PostgreSQLUserDAO implements UserDAO {

	private static final String INSERT_USER = "INSERT INTO users(firstname, lastname, email, password) values(?,?,?,?);";

	private static final Logger log = LogManager.getLogger();

	@Override
	public User create(User user) throws DAOException {
		try {
			ConnectionHolder.setConnection(DriverManager.getConnection("jdbc:postgresql://localhost:5432/test",
					"postgres", new EncryptDecrypt().decrypt("")));
		} catch (Exception e) {
			log.fatal("Could create {} connection", "postgres");
		}
		log.entry(user);
		Connection connection = ConnectionHolder.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				user.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException ex) {
			log.error("SQLException during answer insert query", ex);
			throw new DAOException(ex);
		}
		log.exit(user);
		return null;

	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

}
