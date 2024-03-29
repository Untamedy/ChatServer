package com.chatserver;

import javax.websocket.Session;

/**
 *
 * @author YBolshakova
 */
public class UserSession {

    private String login;    
    private Session session;
    private String room;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

       

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

}
