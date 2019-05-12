package cn.it.web.web.servlet;

import cn.it.web.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/successServlet")
public class SuccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //需要强制类型转换一下
        User user = (User) req.getAttribute("user");
        if (user != null) {
            resp.setContentType("text/html;charset=utf-8");
            /**
             * resp.getWriter()  获取字符输出流   write（）写出渲染给浏览器
             */
            resp.getWriter().write("登录成功sjdh1111！！！" + user.getUsername() + "");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //需要强制类型转换一下
        User user = (User) req.getAttribute("user");
        if (user != null) {
            /**
             * resp.getWriter()  获取字符输出流   write（）写出渲染给浏览器
             */
            resp.setContentType("text/html;charset=utf-8");
            //resp.getWriter().write("登录成功sssaasss！！！" + user.getUsername() + "");
            /**
             * resp.getOutputStream()  获取字节输出流   write（）写出渲染给浏览器  一般使用 传递图片和文件
             */
            resp.getOutputStream().write("hellow".getBytes("utf-8"));
        }
    }
}
