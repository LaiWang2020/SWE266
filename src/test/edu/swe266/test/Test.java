package edu.swe266.test;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import edu.swe266.pojo.Account;
import edu.swe266.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import java.beans.PropertyVetoException;

public class Test {
    ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
    AccountService account = applicationContext.getBean(AccountService.class);
    @org.junit.Test
    public void testDeposit(){

        System.out.println(account.checkDeposit("duke"));
        //System.out.println(account.depositMoney("duke", 1000));
    }
    @org.junit.Test
    public void testUserExist(){

        System.out.println(account.userExist("duke2"));
        //account.depositMoney("duke", 1000);
        //account.withdrawMoney("duke", 123);
    }
    @org.junit.Test
    public void testCreateAccount(){
        System.out.println(account.createAccount("duke5","123456"));

    }
    @org.junit.Test
    public void testLogin(){
        //normal login
        account.logIn("duke3", "123456");
        //sql injection login
        account.logIn("duke3' or '1 --","");

    }
    @org.junit.Test
    public void testWithdraw(){
        System.out.println(account.withdrawMoney("duke3",250));

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
