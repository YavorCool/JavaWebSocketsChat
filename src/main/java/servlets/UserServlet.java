package servlets;

import accounts.AccountService;
import dbService.dataSets.UserDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by qwe on 01.06.2016.
 */
public class UserServlet extends HttpServlet {
    private final AccountService accountService;

    public UserServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("sign in or sign up");
    }

    //Sign Up
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserDataSet newUser = new UserDataSet(login, password);

        try {
            accountService.addNewUser(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.setStatus(HttpServletResponse.SC_OK);
        req.getRequestDispatcher("/signin").forward(req, resp);
    }
}
