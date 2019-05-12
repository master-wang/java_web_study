package cn.it.web.dao;

import cn.it.web.domain.User;
import cn.it.web.util.JDBCDBPoolUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 操作数据库中 User表的 类
 */
public class UserDao {
    //生命一个JDBCTemplate对象来共用
    private JdbcTemplate template = new JdbcTemplate(JDBCDBPoolUtils.getDatasource());
    /**
     * 登录的方法
     * @param loginUser 只要用户名和方法
     * @return 返回数据库中这个人的所有信息
     */

    public User login(User loginUser){
        try{
            String sql ="select * from user where username = ? and password= ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }

    }
}
