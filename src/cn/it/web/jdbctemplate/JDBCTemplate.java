package cn.it.web.jdbctemplate;

/**
 * JdbcTemplate 的入门
 */
public class JDBCTemplate {
    public static void main(String[] args) {
        /**
         * 修改操作 update 增 删 改
         */
        /*//创建jdbctemplate 对象
        JdbcTemplate temp = new JdbcTemplate(JDBCDBPoolUtils.getDatasource());
        //调用方法
        String sql = "update  account set bland = 2000 where id = ?";
        int count = temp.update(sql,3);

        System.out.println(count);*/

        /**
         * 查询 queryForMap() queryForList() queryForObject() query() 集中方式
         *
         */
    }
}
