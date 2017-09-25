package ir.moke.realm.basic.controller;

import ir.moke.realm.basic.model.to.OnlineUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class AuthenticationController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private static List<OnlineUser> listOnlineUsers = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logout(req, resp);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.logout();
            response.sendRedirect("/");
        } catch (ServletException | IOException e) {
            logger.debug(e.getMessage());
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            request.login(username, password);
            recordUser(request);
            request.getSession().setAttribute("listOnlineUsers", listOnlineUsers);
            response.sendRedirect("/dashboard");
        } catch (ServletException | IOException e) {
            logger.debug(e.getMessage());
        }
    }

    private void recordUser(HttpServletRequest request) {
        OnlineUser onlineUser = new OnlineUser();
        onlineUser.setUsername(request.getRemoteUser());
        onlineUser.setRemoteAddress(request.getRemoteAddr());
        onlineUser.setDate(new Date());
        listOnlineUsers.add(onlineUser);
        logger.info("Logged in : " + onlineUser.toString());
    }
}
