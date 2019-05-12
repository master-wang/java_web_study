package cn.it.web.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test/*")
public class UrlTestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("test路由------------------------");
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        String[] arr = requestURI.split("/");
        System.out.println(arr);
        UrlTestParamDao serDao = new UrlTestParamDao();
        if(arr[arr.length-1].equals("add")){
            serDao.addUrl(req,resp);
        }else if(arr[arr.length-1].equals("update")){
            serDao.updateUrl(req,resp);
        }else if(arr[arr.length-1].equals("delete")){
            serDao.deleteUrl(req,resp);
        } else if(arr[arr.length-1].equals("find")){
            serDao.findUrl(req,resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

}
