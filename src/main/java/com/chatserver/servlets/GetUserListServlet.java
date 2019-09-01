package com.chatserver.servlets;


import com.chatserver.model.Users;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
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
@WebServlet("/users")
public class GetUserListServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (!session.isNew()) {
            Users users = Users.getInstance();
            List<String> userList = users.getlist();
            String toJson = users.toJson(userList);
            if (null != toJson) {               
                try {                    
                   OutputStream os = response.getOutputStream();
                    byte[] buf = toJson.getBytes(StandardCharsets.UTF_8);
                    os.write(buf);
                } catch (IOException ex) {
                    Logger.getLogger(GetUserListServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

}


