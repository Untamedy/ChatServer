package com.chatserver.model;

import com.chatserver.entitys.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageList {

    private static final MessageList msgList = new MessageList();

    private static final int LIMIT = 100;

    private ObjectMapper mapper;
    private final List<Message> alllist = new LinkedList<Message>();
    private final List<Message> sportlist = new LinkedList<Message>();

    public static MessageList getInstance() {
        return msgList;
    }

    private MessageList() {
        mapper = new ObjectMapper();
    }

    public synchronized void add(Message m) {
        if (m.getRoom().equalsIgnoreCase("all")) {
            addToList(alllist, m);
        } else {
            addToList(sportlist, m);
        }

    }

    private void addToList(List<Message> list, Message m) {
        if (list.size() + 1 == LIMIT) {
            list.remove(0);
        }
        list.add(m);
    }

    public synchronized String toJSON(int n,String room) {
        if(room.equals("all")){
          return listToJson(alllist, n);
        }else{
            return listToJson(sportlist, n);
        }
       

    }
    
    private String listToJson(List<Message> list, int n){
        if (n == list.size()) {
            return null;
        }
        String json = "";
        try {
            json = mapper.writeValueAsString(JsonMessages.jsonMessages(list, n));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(MessageList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;  
    }
}
