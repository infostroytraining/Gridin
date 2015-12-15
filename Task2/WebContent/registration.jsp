<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="com.infostroy.captcha.CaptchasDotNet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<%
// Construct the captchas object (Default Values)
CaptchasDotNet captchas = new CaptchasDotNet(
  request.getSession(true),     // Ensure session
  "demo",                       // client
  "secret"                      // secret
  );
// Construct the captchas object (Extended example)
// CaptchasDotNet captchas = new captchas.CaptchasDotNet(
//  request.getSession(true),     // Ensure session
//  "demo",                       // client
//  "secret",                     // secret
//  "01",                         // alphabet
//  16,                           // letters
//  500,                          // width
//  80                            // height
//  );
%>
<form action="" method="POST">
<label for="name">First Name</label> <input id="name" name="name" /> <br/>
<br/>
<label for="lastName">Last Name</label>
<input id="lastName" name="lastName" />
<br/>
<br/>
<label for="name">Email</label>
<input id="email" name="email" />
<br/>
<br/>
<label for="password">Password</label>
<input id="password" name="password" type="password" />
<br/>
<br/>
<%= captchas.image() %><br>
<input id="captcha" name="captcha" />
<input type="submit"  value="Send"></input>
</form>
</body>
</html>