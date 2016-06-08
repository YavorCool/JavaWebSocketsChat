package servlets;

import accounts.AccountService;
import dbService.dataSets.UserDataSet;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qwe on 01.06.2016.
 */
public class SessionServlet extends HttpServlet {
    private final AccountService accountService;

    public SessionServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionId = req.getSession().getId();
        UserDataSet profile = accountService.getUserBySessionId(sessionId);
        if(profile==null){
            resp.setContentType("text/html;charser=utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }else{
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println(profile.getLogin());
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

    //Sign in
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Map<String, Object> pageVariables = new HashMap<>();

        if (login == null || password == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserDataSet profile = null;

        try {
            profile = accountService.getUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (profile == null || !profile.getPassword().equals(password)) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().println("Unauthorized");
            return;
        }

        accountService.addSession(req.getSession().getId(), profile);
        pageVariables.put("username", profile.getLogin());
        resp.getWriter().println(PageGenerator.instance().getPage("chat.html", pageVariables));
    }
}
