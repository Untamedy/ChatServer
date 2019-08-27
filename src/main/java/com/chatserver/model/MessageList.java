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
    private final List<Message> list = new LinkedList<Message>();

    public static MessageList getInstance() {
        return msgList;
    }

    private MessageList() {
        mapper = new ObjectMapper();
    }

    public synchronized void add(Message m) {
        if (list.size() + 1 == LIMIT) {
            list.remove(0);
        }
        list.add(m);
    }

    public synchronized String toJSON(int n) {
        if (n == list.size()) {
            return null;
        }
        String json = "";
        try {
            List<Message> messages = JsonMessages.jsonMessages(list, n);
            for(Message m: messages){
                if(m.getTo().equalsIgnoreCase(json))
            }
            
            json = mapper.writeValueAsString(new JsonMessages(list, n));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(MessageList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;

    }
}
