<%-- 
    Document   : userlist
    Created on : Aug 24, 2019, 5:25:03 PM
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
