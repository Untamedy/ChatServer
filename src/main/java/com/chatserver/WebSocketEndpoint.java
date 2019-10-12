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
   Map<String,List<String>> messages = new HashMap();
    
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
                   String newRoomName = (String)data.get("room");
                   String curentRoomName = users.get(sessionId).getRoom();
                    if(users.containsKey(sessionId)){
                        if(!curentRoomName.equals(newRoomName)){
                          List<String> curentRoom = rooms.get(curentRoomName);
                           curentRoom.remove(sessionId);
                           users.get(sessionId).setRoom(newRoomName);
                          List<String> newRoom =  rooms.get(newRoomName);
                          newRoom.add(sessionId);
                        List<String> roomMsg = messages.get(newRoomName);
                        for(String s:roomMsg){
                              try {
                                  session.getBasicRemote().sendObject(s);
                              } catch (EncodeException ex) {
                                  Logger.getLogger(WebSocketEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                              }
                              
                          }
                        }   
                    }else{
                        session.getBasicRemote().sendText("User is not logined");
                        
                    }
                    break;
                case "roomMsg":
                    StringBuilder msg = new StringBuilder();
                    msg.append("From:");
                    msg.append((String)data.get("from"));
                    msg.append("To");
                    msg.append(data.get("to"));
                    msg.append(data.get("text"));
                    
                    
                    String room =(String) data.get("room");
                    List<String> messages = this.messages.get(room);
                    checkMsgCount(messages, msg.toString());
                    
                    for(String id: rooms.get(room)){
                     session = users.get(id).getSession();
                     session.getBasicRemote().sendText(msg.toString());
                        
                    }                                       
                    break;
                case "privateMsg":
                    String to =(String) data.get("to");
                    session = users.get(to).getSession();
                    if(null!=session){
                    StringBuilder privateMsg = new StringBuilder();
                    privateMsg.append("From:");
                    privateMsg.append((String)data.get("from"));
                    privateMsg.append("To");
                    privateMsg.append(data.get(to));
                    privateMsg.append(data.get("text"));
                     session.getBasicRemote().sendText(privateMsg.toString());
                    }                  
                    
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
    
    
    public void checkMsgCount(List<String> messages, String message){
        if(messages.size()>=10){
            messages.remove(0);
            messages.add(message);        
            
        }
        
    }

}
