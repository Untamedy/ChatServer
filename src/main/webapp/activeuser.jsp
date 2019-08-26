<%-- 
    Document   : activeuser
    Created on : Aug 26, 2019, 7:00:00 PM
    Author     : YBolshakova
--%>

<%@page import="com.chatserver.entitys.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Active users</title>
    </head>
    <body>
        <h2>This user is active now</h2>
        <%List<User> users =(List<User>) request.getAttribute("listOfActiveUser");%> 
        <%for(User u: users){%>
            <%out.println(u.toString());%>
       <% }%>    
        
    </body>
</html>