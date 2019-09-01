package com.chatserver.servlets;

import com.chatserver.entitys.User;
import com.chatserver.model.Users;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("login");
        String password = request.getParameter("pass");
        Users userList = Users.getInstance();        
        User user = new User();
        String json = "";

        if (userList.getUsersMap().containsKey(userName)) {
            if (userList.getPass(userName).equalsIgnoreCase(password)) {             
                json = user.toJson(userList.getUser(userName));
            }
        } else {
            userList.addUser(new User(userName, password, true));           
            json = user.toJson(userList.getUser(userName));
        }         

        if (json != null) {
            try {
                OutputStream os = response.getOutputStream();
                byte[] buf = json.getBytes(StandardCharsets.UTF_8);
                os.write(buf);
            } catch (IOException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
