package com.chatserver.servlets;

import com.chatserver.entitys.User;
import com.chatserver.model.Users;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author YBolshakova
 */
@WebServlet("/userlist")
public class GetUserListServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (!session.isNew()) {
            Users users = Users.getInstance();
            List<User> isActiveNow = users.activeUser();
            request.setAttribute("listOfActiveUser", isActiveNow);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("userList.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(GetUserListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
