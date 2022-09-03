package com.nart.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nart.service.GroupService;
import com.nart.util.EncryptUtil;
import com.nart.util.GsonFormatter;
import com.nart.util.RedisUtil;
import com.nart.vo.WSMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: pack
 *
 * @className: ChatEndPoint
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/9/3 11:05
 */
@ServerEndpoint(value="/chat",configurator = GetHttpSessionConfigurator.class)
@Component
public class ChatEndPoint {
    // stores all online users
    private static final Map<String, ChatEndPoint> onlineUsers = new ConcurrentHashMap<>();

    private Session session;

    private HttpSession httpSession;

    @Autowired
    private GroupService groupService;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;
        String uid = (String) httpSession.getAttribute("uid");

        onlineUsers.put(uid, this);
    }

    private void broadcastAllUsers(WSMsg wsMsg, Set<String> receivers) {
        // find all online userIds
        Set<String> ids = onlineUsers.keySet();
        // find intersections between online users and target users;
        ids.retainAll(receivers);
        try {
            // send to all
            for(String id: ids){
                ChatEndPoint chatEndPoint = onlineUsers.get(id);
                chatEndPoint.session.getBasicRemote().sendText(GsonFormatter.toJsonString(wsMsg));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Set<String> getIds() {
        return onlineUsers.keySet();
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            WSMsg msg = mapper.readValue(message, WSMsg.class);

            Set<String> receivers = new HashSet<>();
            String receiverType = msg.getReceiverType();
            String receiver = msg.getReceiver();
            // set receivers
            if(receiverType.equals("friend")) {
                receivers.add(receiver);
            } else {
                receivers.addAll(groupService.findAllMembers(receiver));
            }

            broadcastAllUsers(msg, receivers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session){
        String uid = (String) httpSession.getAttribute("uid");
        onlineUsers.remove(uid);
    }
}
