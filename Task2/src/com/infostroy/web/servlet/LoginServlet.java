package com.infostroy.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.infostroy.entity.User;
import com.infostroy.service.UserService;
import com.infostroy.service.exception.ServiceException;

public class LoginServlet extends HttpServlet {

	private static final Logger log = LogManager.getLogger();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserService userService = (UserService) req.getServletContext().getAttribute("userService");
		// TODO: must login user
	}
}
