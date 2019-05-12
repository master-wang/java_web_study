package cn.it.web.test;

import cn.it.web.dao.UserDao;
import cn.it.web.domain.User;
import org.junit.Test;

/**
 * 测试 dao 的用户登录操作的 对不对
 */
public class UserDaoTest {


    @Test
    public void testLogin(){
        User loginuser = new User();
        loginuser.setUsername("wang");
        loginuser.setPassword("123456");
        UserDao dao = new UserDao();
        User user = dao.login(loginuser);
        System.out.println(user);
    }
}
