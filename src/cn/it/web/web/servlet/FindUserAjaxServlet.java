package cn.it.web.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/findUserAjaxServlet")
public class FindUserAjaxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        resp.setContentType("text/html;charset=utf-8");
        //resp.setContentType("application/json;charset=utf-8");//json格式
        Map<String,Object> map = new HashMap<String, Object>();


        if("wang".equals(username)){
            map.put("userExit",true);
            map.put("msg","用户名已注册！");
        }else{
            map.put("userExit",false);
            map.put("msg","用户名可用！");
        }

        //讲map装环卫json
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(resp.getWriter(),map);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
