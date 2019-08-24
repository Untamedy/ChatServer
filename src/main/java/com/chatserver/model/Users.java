package com.chatserver.model;

import com.chatserver.entitys.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author YBolshakova
 */
public class Users {

    private static final Users users = new Users();
    private final Map<String, User> userslist;
    private ObjectMapper mapper;

    public static Users getInstance() {
        return users;
    }

    private Users() {
        userslist = new HashMap<>();
        mapper = new ObjectMapper();
    }

    public synchronized void addUser(User user) {
        if (null != user) {
            userslist.putIfAbsent(user.getName(), user);
        }
    }

    public synchronized void removeUser(String name) {
        if (userslist.containsKey(name)) {
            userslist.remove(name);
        }
    }

    public Map<String, User> getUserslist() {
        return userslist;
    }

    public String getPass(String name) {
        String pass = "";
        if (userslist.containsKey(name)) {
            pass = userslist.get(name).getPassword();
        }
        return pass;
    }

    public String getStatus(String name) {
        boolean status = userslist.get(name).isStatus();
        if (status) {
            return "active";
        }
        return "off";
    }

    public List<User> activeUser() {
        List<User> activeUsers = new ArrayList<>();
        Set<String> keys = userslist.keySet();
        for (String s : keys) {
            User user = userslist.get(s);
            if(user.isStatus()){
                activeUsers.add(user);
            }
        }
        return activeUsers;
    }
}
