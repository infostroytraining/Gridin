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

public class UserServlet extends HttpServlet {

	private static final Logger log = LogManager.getLogger();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("registration.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// String name = req.getParameter("name");
		// String language = req.getParameter("language");
		//
		// if (name == null || name.isEmpty()) {
		// name = "Stranger";
		// }
		// req.setAttribute("name", name);
		// req.getRequestDispatcher("answer.jsp").forward(req, resp);
		UserService userService = (UserService) req.getServletContext().getAttribute("userService");
		String firstName = req.getParameter("name");
		String lastName = req.getParameter("lastName");
		// AnswerDTO answer = new AnswerDTO(name, lastName);
		// List<String> errors = validateForm(answer);
		// if (!errors.isEmpty()) {
		// req.setAttribute("answer", answer);
		// req.setAttribute("errors", errors);
		// req.getRequestDispatcher("home.jsp").forward(req, resp);
		// } else {
		try {
			userService.add(new User(firstName, lastName, " ", " ", " "));
			// req.setAttribute("statisticMap",
			// userService.getStatisticForEachAnswer());
		} catch (ServiceException e) {
			log.error("Exception in servlet", e);
			throw new ServletException(e);
		}
		req.setAttribute("name", firstName);
		req.getRequestDispatcher("answer.jsp").forward(req, resp);
		// }
	}
}
