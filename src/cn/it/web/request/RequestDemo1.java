package cn.it.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * requrest 的学习
 * 演示
 */
@WebServlet(urlPatterns = "/RequestDemo1")
public class RequestDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求方式
       /* String method = req.getMethod();
        System.out.println(method);
        //获取虚拟目录
        String contextPath = req.getContextPath();
        System.out.println(contextPath);
        //获取Servlet 的路径
        String servletPath = req.getServletPath();
        System.out.println(servletPath);
        //获取get 方式的请求参数
        String str = req.getQueryString();
        System.out.println(str);
        //获取请求的url
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        StringBuffer requestURL = req.getRequestURL();
        System.out.println(requestURL);
        //获取协议 版 本

        String protocol = req.getProtocol();
        System.out.println(protocol);
        //获取客户机的IP
        String remoteAddr = req.getRemoteAddr();
        System.out.println(remoteAddr);
        System.out.println("====================");*/
        /**
         * 根据请求的名称获取参数
         */
        String username = req.getParameter("username");
        System.out.println("get");
        System.out.println(username);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从消息体获取数据

        /*//1获取字符流
        BufferedReader br = req.getReader();
        //读取数据
        String line = null;
        while((line = br.readLine())!=null){
            System.out.println(line);
        }*/
        /**
         * 根据请求的名称获取参数   需要设置流的字符集，不然会有乱码
         */
        req.setCharacterEncoding("utf-8");

        String name = req.getParameter("username");
        String[] hos = req.getParameterValues("ho");

        for (String ttem : hos){
            System.out.println(ttem);
        }
        System.out.println("post11");

        System.out.println(name);

    }
}
