package cn.it.web.web.servlet;

import cn.it.web.dao.UserDao2;
import cn.it.web.dao.impl.UserDao2Impl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delUserManyServletjsp")
public class DelUserManyServletjsp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] uids = req.getParameterValues("uid");
        UserDao2 dao = new UserDao2Impl();
        dao.delManyUser(uids);

        resp.sendRedirect(req.getContextPath()+"/userlist.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
