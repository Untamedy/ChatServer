package com.chatserver.entitys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class User implements Serializable {
    
    private String name;
    private String password;
    private boolean status;
    private String room;
    private ObjectMapper mapper = new ObjectMapper();

    public User() {
    }    

    public User(String name, String password, boolean status) {
        this.name = name;
        this.password = password;
        this.status = status;
        
    }
    
    public String toJson(User user){
        String json = "";
        try {
            json= mapper.writeValueAsString(user);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  name + ", status=" + status;
    }
    
    
    
    
    

}
