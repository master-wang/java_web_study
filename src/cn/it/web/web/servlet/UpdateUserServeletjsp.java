package cn.it.web.web.servlet;

import cn.it.web.dao.UserDao2;
import cn.it.web.dao.impl.UserDao2Impl;
import cn.it.web.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updateUserServeletjsp")
public class UpdateUserServeletjsp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //获取请求参数
        String checkcode = req.getParameter("checkcode");
        HttpSession session = req.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        //得到验证码的判断之后，立即删除
        session.removeAttribute("checkCode_session");
        if (checkCode_session!=null && checkCode_session.equalsIgnoreCase(checkcode)){//验证码正确
            /**
             * 验证码通过  执行查询数据库看是否存在这样的一个人
             */
            //获取所有的数据
            Map<String, String[]> map = req.getParameterMap();
            //创建user对象
            User updateUser = new User();
            //使用 BeanUtil 封装  非常简约，不用对对象一个一个的赋值
            try {
                BeanUtils.populate(updateUser,map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            //调用UserDao的login方法

            //UserDao dao = new UserDao();
            UserDao2 dao = new UserDao2Impl();


            dao.updateUserbyId(updateUser);

            resp.sendRedirect(req.getContextPath()+"/userlist.jsp");

        }else {//验证码不正确
            req.setAttribute("cc_error","验证码不正确！");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
