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
    }

}
