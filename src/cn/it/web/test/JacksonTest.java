package cn.it.web.test;

import cn.it.web.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * ｊｓｏｎ转换城java对象就是　readＶａｌｕｅ
 */
public class JacksonTest {
    //java对象转化城json

    /**
     * 单个对象转换城ｊｓｏｎ
     * @throws JsonProcessingException
     */
    @Test
    public void test1() throws JsonProcessingException {
        User user = new User();
        user.setId(1);
        user.setUsername("wang");
        user.setPassword("123");

        //创建jsckson 的核心对象  ObijectMapper
        ObjectMapper mapper = new ObjectMapper();
        //调用方法转换
        /**
         * writeValue()
         * writeAsString(obi)  装换城json字符串   {"id":1,"username":"wang","password":"123"}
         *
         */
        String s = mapper.writeValueAsString(user);
        System.out.println(s);
    }

    /**
     * ｌｉｓｔ
     * ｍａｐ
     * 装换城json数组
     */
    @Test
    public void test２() throws JsonProcessingException {
        User user = new User();
        user.setId(1);
        user.setUsername("wang");
        user.setPassword("123");

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

        String s = mapper.writeValueAsString(ps);
        System.out.println(s);
    }
    @Test
    public void testMap() throws JsonProcessingException {
        Map<String,Object> map = new HashMap<String, Object>();

        map.put("name","张三");
        map.put("age","23");
        map.put("gender","nan");

        //创建jsckson 的核心对象  ObijectMapper
        ObjectMapper mapper = new ObjectMapper();

        String s = mapper.writeValueAsString(map);
        System.out.println(s);
    }

    /**
     * json转化为java对象
     * @throws JsonProcessingException
     */
    @Test
    public void test5() {
        String json = "{\"id\":1,\"username\":\"lv\",\"password\":\"789\"}";
        ObjectMapper mapper = new ObjectMapper();

        User user = null;
        try {
            user = mapper.readValue(json, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }
}
