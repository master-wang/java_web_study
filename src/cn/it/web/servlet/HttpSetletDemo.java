package cn.it.web.servlet;

import com.sun.net.httpserver.HttpServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HttpSetlet  也是GenericServlet的子类
 * 对http协议的一些封装 简化操作
 *@WebServlet({"/HttpServletdemo","/sdsu"})可以设置多个路由
 */


@WebServlet("/HttpServletdemo")
public class HttpSetletDemo  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doget");
        System.out.println(req);
        //获取请求
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post....");
        System.out.println(req);
    }
}
