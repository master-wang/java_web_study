package cn.it.web.jdbctemplate;

/**
 * 数据库表 数据要和数据库的数据匹配，字段
 */
public class Account {
    private int id;
    private String name;
    private int bland;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBland() {
        return bland;
    }

    public void setBland(int bland) {
        this.bland = bland;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bland=" + bland +
                '}';
    }
}
