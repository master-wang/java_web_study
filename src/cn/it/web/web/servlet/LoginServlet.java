package cn.it.web.web.servlet;

import cn.it.web.dao.UserDao;
import cn.it.web.dao.UserDao2;
import cn.it.web.dao.impl.UserDao2Impl;
import cn.it.web.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        /*//获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username+"=========="+password);
        //封装对象
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);*/

        //获取所有的数据
        Map<String, String[]> map = req.getParameterMap();
        //创建user对象
        User loginUser = new User();
        //使用 BeanUtil 封装  非常简约，不用对对象一个一个的赋值
        try {
            BeanUtils.populate(loginUser,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用UserDao的login方法


        //UserDao dao = new UserDao();
        UserDao2 dao = new UserDao2Impl();

        User user = dao.login(loginUser);
        System.out.println(user);
        if (user == null){
            //登录失败
            req.getRequestDispatcher("/failServlet").forward(req,resp);
        }else {
            //登录成功
            req.setAttribute("user",user);
            req.getRequestDispatcher("/successServlet").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);

    }
}
