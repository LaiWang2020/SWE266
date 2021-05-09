package edu.swe266.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyVetoException;

public class jdbcTest {
    @Test
    public void test1() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/testm5");
        dataSource.setUser("root");
        dataSource.setPassword("wanglai2020");
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        int row = jdbcTemplate.update("insert into courses value (?,?,?,?)",7,"a",1,"10am");
        System.out.println(row);

    }
}
