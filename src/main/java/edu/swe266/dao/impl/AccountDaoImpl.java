package edu.swe266.dao.impl;

import edu.swe266.dao.AccountDao;
import edu.swe266.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.List;

@Component("AccountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final double DEFAULT_DEPOSIT=100;

    @Override
    public double checkDeposit(String username) {

        Account account=jdbcTemplate.queryForObject("Select deposit from account where username=?", new BeanPropertyRowMapper<Account>(Account.class), "duke");
        System.out.println("invoke success");
        return account.getDeposit();
    }



    @Override
    public void deposit(String username, double money) {
         Account account=jdbcTemplate.queryForObject("Select deposit from account where username=?", new BeanPropertyRowMapper<Account>(Account.class), username);
         double deposit= account.getDeposit();
        jdbcTemplate.update("update account set deposit=? where username=?", money+deposit, username);
        //bad code
        System.out.println("Deposit "+money+"successful");
    }


    @Override
    //should add check part
    public void withDraw(String username, double money) {
        double deposit= checkDeposit(username);
        //bad code
        if(deposit-money<0){
            System.out.println("fail! ");
            return;
        }
        jdbcTemplate.update("update account set deposit=? where username=?", deposit-money, username);
        //bad code
        System.out.println("Withdraw "+money+"successful");
    }

    @Override
    public void createAccount( String username,String psw) {
        if(!userExist(username)){
            jdbcTemplate.update("INSERT INTO account (psw,username,deposit) values(?,?,?)", psw,username,DEFAULT_DEPOSIT);
        }
    }

    @Override
    public boolean logIn(String username, String psw) {
//        String sqlQuery = "select username, password, password_hint, created_at, last_login, real_name, blab_name from users where username='"
//                + username + "' and password='" + md5(password) + "';";
        //bad code SQL injection
        List<Account> accounts = jdbcTemplate.query("Select * from account where username='" + username + "' and psw='" + psw + "'", new BeanPropertyRowMapper<Account>(Account.class));
        if(accounts.size()==0){
            System.out.println("User not found, please check your psw or username");
            return false;
        }
        else{
            System.out.println("login success");
            return true;
        }

    }

    @Override
    public boolean userExist(String username) {
        List<Account> accounts = jdbcTemplate.query("Select username from account where username=?", new BeanPropertyRowMapper<Account>(Account.class), username);
        //bad code
        if(accounts.size()==0){
            return false;
        }
        return true;
    }
}
