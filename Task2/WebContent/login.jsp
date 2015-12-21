<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<big>Login Page</big>
<br/>
<form action="" method="POST">
<label for="name">Email</label>
<input id="email" name="email" />
<br/>
<br/>
<label for="password">Password</label>
<input id="password" name="password" type="password" />
<br/>
<br/>
<%-- <%= captchas.image() %><br> --%>
<input id="captcha" name="captcha" />
<input type="submit"  value="Send"></input>
</form>
</body>
</html>