package com.chatserver.model;


import com.chatserver.entitys.Message;
import java.util.ArrayList;
import java.util.List;

public class JsonMessages {
    

    public static List<Message> jsonMessages(List<Message> sourceList, int fromIndex) {
        List<Message>list = new ArrayList<>();
        for (int i = fromIndex; i < sourceList.size(); i++)
            list.add(sourceList.get(i));
        return list;
    }
}
