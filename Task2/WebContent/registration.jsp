<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="botDetect" uri="botDetect"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<big>Registration Page</big>
<br/>
<br/>
<form action="" method="POST">
<label for="name">First Name</label> <input id="name" name="name" value="${user.firstName}"/> <br/>
<br/>
<label for="lastName">Last Name</label>
<input id="lastName" name="lastName" value="${user.lastName}"/>
<br/>
<br/>
<label for="name">Email</label>
<input id="email" name="email" value="${user.email}"/>
<br/>
<br/>
<label for="password">Password</label>
<input id="password" name="password" type="password" />
<br/>
<br/>
<botDetect:captcha id="exampleCaptchaTag" userInputClientId="captchaCode" />
<input id="captcha" name="captchaCode" />
<input type="submit"  value="Send"></input>
</form>
</body>
</html>