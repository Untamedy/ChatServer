package com.chatserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author YBolshakova
 */
@ServerEndpoint("/chat")
public class WebSocketEndpoint {

    Map<String, UserSession> users = new HashMap<>();
    Map<String,List<String>> rooms=new HashMap<>();
    
    @OnMessage
    public void onMessage(Session session, String message) {
        try {
            String sessionId=session.getId();
            Message chatMessage = new ObjectMapper().readValue(message.getBytes(), Message.class);
            Map<String, Object> data = chatMessage.getData();
            switch (chatMessage.getType()) {
                case "login":
                    String login = (String) data.get("login");
                    String pwd = (String) data.get("password");
                    for (UserSession us : users.values()) {
                        if (us.getLogin().equals(login)) {
                            session.getBasicRemote().sendText("user with this login already exists");
                            return;
                        }
                    }
                    UserSession userSession = new UserSession();
                    userSession.setLogin(login);
                    userSession.setPassword(pwd);
                    userSession.setRoom("main");
                    userSession.setSession(session);
                    users.put(sessionId, userSession);
                    List<String> list = new ArrayList<>();
                    rooms.put("main",list);
                    break;
                case "selectRoom":
                    if(users.containsKey(sessionId)){
                    users.get(sessionId).setRoom("");
                        
                    }else{
                        //
                    }
                    break;
                case "roomMsg":
                    
                    break;
                case "privateMsg":
                    break;
            }
        } catch (IOException ex) {
            try {
                session.getBasicRemote().sendObject("parsing message error");
            } catch (IOException ex1) {
                Logger.getLogger(WebSocketEndpoint.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (EncodeException ex1) {
                Logger.getLogger(WebSocketEndpoint.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    @OnOpen
    public void onConnect(Session session) {

    }

    @OnClose
    public void onDisconnect(Session session) {
        
        users.remove(session.getId());
    }

}
