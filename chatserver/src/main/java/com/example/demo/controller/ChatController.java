package com.example.demo.controller;

import com.example.demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @Autowired
    private SimpMessageSendingOperations simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    private Message receivePubliceMessage(@Payload Message message){
        return message;
    }
    @MessageMapping("/private-message")
    public Message receivePrivateMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private",message);
        System.out.println(message.toString());
        return message;
    }
}
