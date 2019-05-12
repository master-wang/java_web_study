package cn.it.web.dao.impl;

import cn.it.web.dao.UserDao2;
import cn.it.web.domain.User;
import cn.it.web.util.JDBCDBPoolUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDao2Impl implements UserDao2 {

    private JdbcTemplate template = new JdbcTemplate(JDBCDBPoolUtils.getDatasource());

    /**
     * 登录
     * @param loginUser
     * @return
     */
    @Override
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

    /**
     * 添加
     * @param user
     */
    @Override
    public void addUser(User user){
        String sql = "insert into user(`username`,`password`) values (?,?)";
        int count = template.update(sql, user.getUsername(),user.getPassword());
        System.out.println(count);
    }

    /**
     * 查询用户表
     * @return
     */
    @Override
    public List userList() {
        String sql ="select * from user";
        List<Map<String, Object>> list = template.queryForList(sql);
        System.out.println(list);
        return list;
    }

    /**
     * 用户的删除
     */
    @Override
    public void deleteUser( int id) {
        String sql = "delete from user where id = ?";
        int count = template.update(sql, id);
        System.out.println("删除成功"+count);
    }

    /**
     * 查询某个用户的信息
     * @param id
     * @return
     */
    @Override
    public User findUserInfoById(String id) {
        String sql = "select * from user where id = ?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), Integer.parseInt(id));
        return  user;
    }

    /**
     * 用户的修改提交
     * @param user
     */
    @Override
    public void updateUserbyId(User user) {
        String sql ="update  user set username=?,password=? where id = ?";
        int count = template.update(sql, user.getUsername(), user.getPassword(), user.getId());
        System.out.println(count);
    }

    /**
     * 删除 批量删除
     */
    @Override
    public void delManyUser(String[] uids) {
        for (String uid : uids) {
            this.deleteUser(Integer.parseInt(uid));
        }
    }
    /**
     * 查询总的记录
     * @return
     * @param condition
     */
    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select count(*) from user where 1 =1";
        StringBuilder sb = new StringBuilder(sql);//string类型的
        //便利 map拼接模糊查询的 sql
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<Object>();
        for (String key :keySet){
            //排除分页参数
            if ("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            String s = condition.get(key)[0];
            if(s !=null&&!"".equals(s)){
                sb.append(" and " + key+" like ?");
                params.add("%"+s+"%");
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);
        //sql 传递参数
        Integer count = template.queryForObject(sb.toString(), Integer.class, params.toArray());
        System.out.println(count);
        return count;
    }
    /**
     * 分页查询每页记录  还有模糊查询
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1 =1";
        StringBuilder sb = new StringBuilder(sql);//string类型的
        //便利 map拼接模糊查询的 sql
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<Object>();
        for (String key :keySet){
            //排除分页参数
            if ("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            String s = condition.get(key)[0];
            if(s !=null&&!"".equals(s)){
                sb.append(" and " + key+" like ?");
                params.add("%"+s+"%");
            }
        }

        //添加分页的查询
        sb.append(" limit ?,? ");
        params.add(start);
        params.add(rows);
        sql=sb.toString();

        System.out.println(sql);
        System.out.println(params);
        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }


}
