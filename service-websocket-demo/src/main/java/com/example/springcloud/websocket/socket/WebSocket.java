package com.example.springcloud.websocket.socket;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/01/23  下午 02:02
 * Description:
 */

import com.alibaba.fastjson.JSON;
import com.example.springcloud.websocket.model.MsgBody;
import com.example.springcloud.websocket.util.MessageDecoder;
import com.example.springcloud.websocket.util.MessageEncoder;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/webSocket/{username}", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
@Component
public class WebSocket {

    private static int onlineCount = 0;

    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();

    private Session session;

    private String username;

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        System.out.println("on open.");
        this.session = session;
        this.username = username;
        clients.put(username, this);
        onlineCount++;
        sendMsg("当前人数：" + onlineCount, "sys");
    }

    @OnClose
    public void onClose() {
        onlineCount--;
        System.out.println("on close.");
    }

    @OnMessage
    public String onMessage(MsgBody msgBody) {
        System.out.println("on message.");
        System.out.println("message:[" + msgBody + "]");
//        MsgBody msgBody = JSON.parseObject(message, MsgBody.class);
        WebSocket webSocket = clients.get(msgBody.getUsername());
        if (webSocket != null){
            clients.get(msgBody.getUsername()).sendMsg(msgBody.getMessage(), this.username);
            return "send success!";
        }else {
            return msgBody.getUsername() + " not online";
        }
    }

    @OnError
    public void onError(Throwable error) {
        System.out.println("on error.");
        error.printStackTrace();
    }

    public void sendMsg(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String message, String username) {
        try {
            this.session.getBasicRemote().sendObject(new MsgBody(username, message));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }
}
