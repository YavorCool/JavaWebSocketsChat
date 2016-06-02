package chat;

import accounts.AccountService;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import javax.servlet.http.HttpSession;

/**
 * Created by qwe on 31.05.2016.
 */

@WebSocket
public class ChatWebSocket {
    private ChatService chatService;
    private final AccountService accountService;
    private Session session;
    private String userName;

    public ChatWebSocket(ChatService chatService, AccountService accountService, String userName) {
        this.chatService = chatService;
        this.accountService = accountService;
        this.userName = userName;
    }


    @OnWebSocketConnect
    public void onOpen(Session session) {
        chatService.add(this);
        this.session = session;
        userName = session.getUpgradeRequest().getCookies().get(2).toString().substring(12, 44);
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        chatService.sendMessage(userName + data);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        chatService.remove(this);
    }

    public void sendString(String data) {
        try {
            session.getRemote().sendString(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
