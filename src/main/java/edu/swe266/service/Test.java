package edu.swe266.service;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import edu.swe266.pojo.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyVetoException;

public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService account = applicationContext.getBean(AccountService.class);
        System.out.println(account.checkDeposit("duke"));
        //account.depositMoney("duke", 1000);
        account.withdrawMoney("duke", 123);
    }

//    @org.junit.Test
//    public void test1() throws PropertyVetoException {
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        dataSource.setDriverClass("com.mysql.jdbc.Driver");
//        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/bank?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT");
//        dataSource.setUser("root");
//        dataSource.setPassword("as2188988");
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        jdbcTemplate.setDataSource(dataSource);
//        int row = jdbcTemplate.update("update account set deposit =? where username =?",1,"duke");
//        System.out.println(row);
//        //Account account=jdbcTemplate.query("Select deposit from account where name=duke");
//        //System.out.println(account.toString());
//    }
}
