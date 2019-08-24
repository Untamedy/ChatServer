package com.chatserver.entitys;

/**
 *
 * @author YBolshakova
 */
public class User {
    
    private String name;
    private String password;
    private boolean status;

    public User() {
    }    

    public User(String name, String password, boolean status) {
        this.name = name;
        this.password = password;
        this.status = status;
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
        return "User{" + "name=" + name + ", status=" + status + '}';
    }
    
    
    
    
    

}
