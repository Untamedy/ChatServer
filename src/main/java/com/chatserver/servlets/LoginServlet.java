package com.chatserver.servlets;

import com.chatserver.entitys.User;
import com.chatserver.model.Users;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author YBolshakova
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private HttpSession session;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("name");
        String password = request.getParameter("password");
        Users userList = Users.getInstance();
        session = request.getSession(true);

        if (userList.getUserslist().containsKey(userName)) {
            if (userList.getPass(userName).equalsIgnoreCase(password)) {
                session.setAttribute("name", userName);
            }
        } else {
            userList.addUser(new User(userName, password,true));
            session.setAttribute("name", userName);
        }

        try {
            response.sendRedirect("login.jsp");
        } catch (IOException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String exit = request.getParameter("exit");
        session = request.getSession(false);

        if ("exit".equals(exit) && (session != null)) {
            session.removeAttribute("name");
        }
        try {
            response.sendRedirect("/login.jsp");
        } catch (IOException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
