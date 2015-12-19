package com.infostroy.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.infostroy.dao.UserDAO;
import com.infostroy.dao.exception.DAOException;
import com.infostroy.db.ConnectionHolder;
import com.infostroy.entity.User;

public class PostgreSQLUserDAO implements UserDAO {

	private static final String INSERT_USER = "INSERT INTO users(firstname, lastname, email, password) values(?,?,?,?);";

	private static final Logger log = LogManager.getLogger();

	@Override
	public User create(User user) throws DAOException {
		log.entry(user);
		Connection connection = null;
		try {
			connection = ConnectionHolder.getConnection();
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
		return user;
	}

	@Override
	public User get(int id) throws DAOException {
		Connection connection = null;
		User user = null;
		try {
			connection = ConnectionHolder.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id = ?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String email = rs.getString(4);
				String password = rs.getString(5);
				user = new User(firstName, lastName, email, password, " ");
				user.setId(id);
			}
		} catch (SQLException ex) {
			log.error("SQLException during answer insert query", ex);
			throw new DAOException(ex);
		}
		return user;
	}

	@Override
	public User update(User user) throws DAOException {
		String KOMA = ", ";
		StringBuilder UPDATE_USER = new StringBuilder("UPDATE users SET firstname = ")
				.append("'" + user.getFirstName() + "'").append(KOMA).append("lastname = ")
				.append("'" + user.getLastName() + "'").append(KOMA).append("email = ")
				.append("'" + user.getEmail() + "'").append(KOMA).append("password = ")
				.append("'" + user.getPassword() + "'").append(" WHERE id = ").append(String.valueOf(user.getId()))
				.append(";");
		log.entry(user);
		Connection connection = null;
		try {
			connection = ConnectionHolder.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate(UPDATE_USER.toString());
		} catch (SQLException ex) {
			log.error("SQLException during answer update query", ex);
			throw new DAOException(ex);
		}
		log.exit(user);
		return user;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		log.entry("Delete user from database");
		String DELETE_USER = "DELETE FROM users WHERE id = ?;";
		Connection connection = null;
		boolean result = false;
		try {
			connection = ConnectionHolder.getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_USER);
			statement.setInt(1, id);
			statement.executeUpdate();
			log.trace("Delete user succsesful");
			log.exit("Done delete user from database");
			result = true;
		} catch (SQLException ex) {
			log.error("SQLException during answer delete query", ex);
			throw new DAOException(ex);
		}
		return result;
	}

	@Override
	public List<User> getAll() throws DAOException {
		String ALL_USERS = "SELECT * FROM users;";
		List<User> users = new ArrayList<>();
		Connection connection = null;
		try {
			connection = ConnectionHolder.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(ALL_USERS);
			while (rs.next()) {
				int k = 1;
				int id = rs.getInt(k++);
				String firstName = rs.getString(k++);
				String lastName = rs.getString(k++);
				String email = rs.getString(k++);
				String password = rs.getString(k++);
				User user = new User(firstName, lastName, email, password, " ");
				user.setId(id);
				users.add(user);
			}
		} catch (SQLException ex) {
			log.error("SQLException during answer delete query", ex);
			throw new DAOException(ex);
		}
		return users;
	}
}
