package com.infostroy.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infostroy.entity.LogMessage;

public class LoggerServlet extends HttpServlet {

    private List<LogMessage> logs = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setAttribute("listLogs", logs);
	req.getRequestDispatcher("logs.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String message = req.getParameter("logEvent");
	System.out.println(message);
	LogMessage logMessage = new LogMessage();
	logMessage.setMessage(message);
	if (message.contains("FATAL")) {
	    logMessage.setColor("red");
	} else if (message.contains("ERROR")) {
	    logMessage.setColor("red");
	} else if (message.contains("WARN")) {
	    logMessage.setColor("blue");
	} else if (message.contains("INFO")) {
	    logMessage.setColor("black");
	} else if (message.contains("DEBUG")) {
	    logMessage.setColor("green");
	} else if (message.contains("TRACE")) {
	    logMessage.setColor("blue");
	}
	logs.add(logMessage);
    }
}
