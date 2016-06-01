package main;

import accounts.AccountService;
import chat.WebSocketChatServlet;
import dbService.DBService;
import dbService.jdbc.DBServiceImplJDBC;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SessionServlet;
import servlets.UserServlet;

/**
 * Created by qwe on 31.05.2016.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);
        DBService dbService = new DBServiceImplJDBC();
        AccountService accountService = new AccountService(dbService);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new UserServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SessionServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new WebSocketChatServlet(accountService)), "/chat");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("public_html");
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});
        server.setHandler(handlers);
        server.start();
        System.out.println("Server started");
        server.join();
    }
}
