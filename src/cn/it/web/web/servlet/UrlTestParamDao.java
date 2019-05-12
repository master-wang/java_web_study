package cn.it.web.web.servlet;

import cn.it.web.domain.User;
import cn.it.web.web.UrlTestParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UrlTestParamDao implements UrlTestParam {
    @Override
    public void addUrl(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("addd==========");
    }

    @Override
    public void updateUrl(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("update===========");
    }

    @Override
    public void deleteUrl(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println(req.getParameter("name"));
        System.out.println("delete======");
    }

    @Override
    public void findUrl(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User();
        user.setId(1);
        user.setUsername("wang");
        user.setPassword("123");

        /*//创建jsckson 的核心对象  ObijectMapper
        ObjectMapper mapper = new ObjectMapper();
        //调用方法转换
        *//**
         * writeValue()
         * writeAsString(obi)  装换城json字符串   {"id":1,"username":"wang","password":"123"}
         *
         *//*
        String s = null;
        try {
            s = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/

        User user１ = new User();
        user.setId(1);
        user.setUsername("zhi");
        user.setPassword("456");
        User user２ = new User();
        user.setId(1);
        user.setUsername("lv");
        user.setPassword("789");


        List<User> ps = new ArrayList<User>();
        ps.add(user);
        ps.add(user１);
        ps.add(user２);

        //创建jsckson 的核心对象  ObijectMapper
        ObjectMapper mapper = new ObjectMapper();

        String s = null;
        try {
            s = mapper.writeValueAsString(ps);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(s);

        try {
            resp.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
