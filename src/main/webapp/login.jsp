<%-- 
    Document   : login
    Created on : Aug 24, 2019, 4:38:22 PM
    Author     : YBolshakova
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <% String userName = (String) session.getAttribute("name");%>
        <%if (userName == null || "".equals(userName)) {%>
        <form action="/login" method ="Post">
            Login: <input type="text" name="login" required placeholder="User name">"> <br>
            Password: <input type="password" name="pass" required placeholder="Password"> <br>
            <input type="submit"/>
        </form>
        <%} else {%>
        <h1>You are logged in as: <%= userName%></h1> <br>
        Click to logout <a href="/login?exit=exit">logout></a>   
        <% }%>

    </body>
</html>
