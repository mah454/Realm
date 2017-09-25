package ir.moke.realm.basic.controller;

import ir.moke.realm.basic.model.bl.UserManager;
import ir.moke.realm.basic.model.to.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user-manager")
public class UserManagerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserManager userManager = new UserManager() ;
        List<User> userList = userManager.find();
        req.setAttribute("users",userList);
        req.getRequestDispatcher("/view/user-manager/index.jsp").forward(req,resp);
    }
}
