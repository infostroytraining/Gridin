package com.infostroy.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.infostroy.dto.UserDTO;
import com.infostroy.entity.User;
import com.infostroy.entity.validation.UserValidation;
import com.infostroy.service.UserService;
import com.infostroy.service.exception.ServiceException;

import botdetect.web.Captcha;

public class UserServlet extends HttpServlet {

	private static final Logger log = LogManager.getLogger();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("registration.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserService userService = (UserService) req.getServletContext().getAttribute("userService");
		String firstName = req.getParameter("name");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		UserDTO user = new UserDTO(firstName, lastName, email, password);
		Captcha captcha = Captcha.load(req, "exampleCaptchaTag");
		boolean isHuman = captcha.validate(req, req.getParameter("captchaCode"));
		// List<String> errors = validateForm(answer);
		if (!isHuman && !UserValidation.validate(user)) {
			log.trace("Faild validation");
			req.setAttribute("user", user);
			// req.setAttribute("errors", errors);
			req.getRequestDispatcher("registration.jsp").forward(req, resp);
		} else {
			try {
				userService.add(new User(firstName, lastName, email, password, " "));
			} catch (ServiceException e) {
				log.error("Exception in servlet", e);
				throw new ServletException(e);
			}
			req.setAttribute("name", firstName);
			req.getRequestDispatcher("answer.jsp").forward(req, resp);
		}
	}
}
