package com.xqz.seckill.utils.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/notification")
@Component
public class WebSocketServer {

    private static ConcurrentHashMap<String, Session> username2Session = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        String username = session.getUserPrincipal().getName();
        username2Session.put(username, session);

        System.out.println(username + " 连入Notification");
    }

    @OnClose
    public void onClose(Session session) {
        String username = session.getUserPrincipal().getName();
        username2Session.remove(username);

        System.out.println(username + " 断开Notification连接");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println(message);
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    public void send(String username, String message) throws IOException {
        Session session = username2Session.get(username);
        if(session != null){
            session.getBasicRemote().sendText(message);
        }
    }

    public void broadcast(String message) throws IOException {
        for (Session session : username2Session.values()) {
            session.getBasicRemote().sendText(message);
        }
    }
}
