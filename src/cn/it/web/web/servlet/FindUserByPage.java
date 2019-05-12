package cn.it.web.web.servlet;

import cn.it.web.dao.UserDao2;
import cn.it.web.dao.impl.UserDao2Impl;
import cn.it.web.domain.PageBean;
import cn.it.web.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/FindUserByPage")
public class FindUserByPage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String currentPage = req.getParameter("currentPage");//当前页码
        String rows = req.getParameter("rows"); //每页的记录数

        if (currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)){
            rows ="5";
        }

        //获取条件查询的参数
        Map<String, String[]> condition = req.getParameterMap();



        UserDao2 dao = new UserDao2Impl();
        PageBean<User> pb = new PageBean<User>();

        pb.setCurrentPage(Integer.parseInt(currentPage));
        pb.setRows(Integer.parseInt(rows));

         int totalCount = dao.findTotalCount(condition);

         pb.setTotalCount(totalCount);
        //计算开始的记录缩影
        int start = (Integer.parseInt(currentPage)-1)*Integer.parseInt(rows);
        List<User>  list = dao.findByPage(start,Integer.parseInt(rows),condition);
        pb.setList(list);

        int totalPage = totalCount % Integer.parseInt(rows) == 0 ? totalCount /Integer.parseInt(rows):totalCount /Integer.parseInt(rows)+1 ;
        pb.setTotalPage(totalPage);
        System.out.println(pb);
        req.setAttribute("pb",pb);
        req.setAttribute("condition",condition);
        req.getRequestDispatcher("/userlist.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
