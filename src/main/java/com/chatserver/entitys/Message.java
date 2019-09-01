package com.chatserver.entitys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Message {

    private Date date = new Date();
    private String from;
    private String to;
    private String text;
    private String room;
    private boolean isprivate;
    private static ObjectMapper mapper = new ObjectMapper();

    public Message() {
    }
    
 

    public Message(String from, String text,String to) {
        this.from = from;
        this.text = text;
        this.to = to;    
    }

    public Message(Date date,String from, String to, String text, String room, boolean isprivate) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.text = text;
        this.room = room;
        this.isprivate = isprivate;
    }

    
    public String toJSON() {
        String json = "";
        try {
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }

    public static Message fromJSON(String s) {
        Message message = null;
        try {
            message = mapper.readValue(s, Message.class);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;

    }

    @Override
    public String toString() {
        return new StringBuilder().append("[").append(date)
                .append(", From: ").append(from).append(", To: ").append(to)
                .append("] ").append(text)
                .toString();
    }

    public int send(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            String json = toJSON();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            return conn.getResponseCode();
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    
}
