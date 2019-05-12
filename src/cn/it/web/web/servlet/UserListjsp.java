package cn.it.web.web.servlet;

import cn.it.web.dao.UserDao2;
import cn.it.web.dao.impl.UserDao2Impl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListjsp")
public class UserListjsp extends HttpServlet {
    /**
     * 用户列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao2 dao = new UserDao2Impl();
        List list = dao.userList();
        System.out.println("查询的列表==========");
        System.out.println(list);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
