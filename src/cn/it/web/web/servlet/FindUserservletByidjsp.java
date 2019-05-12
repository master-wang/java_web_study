package cn.it.web.web.servlet;

import cn.it.web.dao.UserDao2;
import cn.it.web.dao.impl.UserDao2Impl;
import cn.it.web.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserservletByidjsp")
public class FindUserservletByidjsp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        UserDao2 dao = new UserDao2Impl();

        User userInfoById = dao.findUserInfoById(id);
        System.out.println(userInfoById);
        req.setAttribute("userInfo",userInfoById);
        req.getRequestDispatcher("/update.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
