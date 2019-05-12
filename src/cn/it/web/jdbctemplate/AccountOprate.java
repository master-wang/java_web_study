package cn.it.web.jdbctemplate;

import cn.it.web.util.JDBCDBPoolUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AccountOprate {
    private JdbcTemplate temp = new JdbcTemplate(JDBCDBPoolUtils.getDatasource());
    /**
     *  Junit 单元测试 不用依赖于主方法
     */

    /**
     * 添加
     */
    @Test
    public void accountAdd(){
        String sql = "insert into account(`name`,`bland`) values (?,?)";
        int count = temp.update(sql, "template", 2500);
        System.out.println(count);
    }

    /**
     * 修改
     */
    @Test
    public void accountUpdate(){
        String sql = "update account set bland = ? where id = 1";
        int count = temp.update(sql, 1300);
        System.out.println(count);
    }

    /**
     * 删除
     */
    @Test
    public void accountDelete(){

    }

    /**
     * Map查询数据库  期望只有一条记录，不能查询多条记录，结果集只能有一个记录
     */
    @Test
    public void accountMap(){
        String sql = "select * from account where id = ?";
        Map<String, Object> rs = temp.queryForMap(sql,3);
        System.out.println(rs);
    }

    /**
     * List 数据  [{id=1, name=wang, bland=1300}, {id=2, name=lv, bland=1500}, {id=3, name=?, bland=2000}, {id=4, name=template, bland=2500}]
     */
    @Test
    public void accountList(){
        String sql ="select * from account";
        List<Map<String, Object>> list = temp.queryForList(sql);
        System.out.println(list);
        System.out.println("================");
        for(Map<String,Object> item : list ){
            System.out.println(item);
        }

    }

    /**
     * EmpList 数据查询结果  封装城 javabeam 对象  自己定义接口
     */
    @Test
    public void accountEmpListXZidinyi(){
        String sql ="select * from account";
        List<Account> list = temp.query(sql, new RowMapper<Account>() {//接口类的实现  查一条返回一条

            @Override
            public Account mapRow(ResultSet rs, int i) throws SQLException {
                Account account = new Account();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int bland = rs.getInt("bland");

                account.setId(id);
                account.setName(name);
                account.setBland(bland);
                return account;
            }
        });
        System.out.println(list);
        System.out.println("================");
        for (Account li:list) {
            System.out.println(li);
        }
    }
    /**
     * EmpList 数据查询结果  封装城 javabeam 对象  官方定义接口
     */
    @Test
    public void accountEmpListAoto(){
        String sql ="select * from account";
        List<Account> list = temp.query(sql, new BeanPropertyRowMapper<Account>(Account.class));//字节码类型，官方提供的
        System.out.println(list);
        System.out.println("========");
        for (Account account : list) {
            System.out.println(account);
        }
    }
    /**
     * 查询总记录数
     *
     */
    @Test
    public void accountAllCount(){
        String sql = "select count(id) from account";
        Long total = temp.queryForObject(sql, Long.class);
        System.out.println(total);
    }
}
