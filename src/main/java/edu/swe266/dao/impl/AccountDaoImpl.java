package edu.swe266.dao.impl;

import edu.swe266.dao.AccountDao;
import edu.swe266.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static edu.swe266.util.Const.DEFAULT_DEPOSIT;
import static org.apache.commons.lang3.Validate.notNull;
import java.sql.Statement;
import java.util.List;

@Component("AccountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public double checkDeposit(String username) {
        notNull(username);
        if(!userExist(username)){
            System.out.println("User doesn't exist");
            return 0;
        }
        Account account=jdbcTemplate.queryForObject("Select deposit from account where username=?", new BeanPropertyRowMapper<Account>(Account.class), username);
        System.out.println("invoke success");
        return account.getDeposit();
    }
    
    @Override
    public boolean deposit(String username, double money) {
        notNull(username);
        notNull(money);
        if(!userExist(username)){
            System.out.println("User doesn't exist");
            return false;
        }
        if(money<0){
            System.out.println("Please enter the valid money");
            return false;
        }
         Account account=jdbcTemplate.queryForObject("Select deposit from account where username=?", new BeanPropertyRowMapper<Account>(Account.class), username);
         double deposit= account.getDeposit();
        jdbcTemplate.update("update account set deposit=? where username=?", money+deposit, username);
        //bad code
        System.out.println("Deposit "+money+"successful");
        return true;
    }


    @Override
    //should add check part
    public boolean withDraw(String username, double money) {
        notNull(username);
        notNull(money);
        if(!userExist(username)){
            System.out.println("User doesn't exist");
            return false;
        }
        double deposit= checkDeposit(username);
        System.out.println("deposit is "+deposit);
        //bad code
        if(deposit-money<0||money<0){
            System.out.println("operation fail!");
            return false;
        }
        jdbcTemplate.update("update account set deposit=? where username=?", deposit-money, username);
        //bad code
        System.out.println("Withdraw "+money+"successful");
        return true;
    }

    @Override
    public boolean createAccount(String username,String psw) {
        notNull(username);
        notNull(psw);
        if(!userExist(username)){
            jdbcTemplate.update("INSERT INTO account (psw,username,deposit) values(?,?,?)", psw,username,DEFAULT_DEPOSIT);
            return true;
        }
        return false;
    }

    @Override
    public boolean logIn(String username, String psw) {
        notNull(username);
        notNull(psw);
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
        notNull(username);
        List<Account> accounts = jdbcTemplate.query("Select username from account where username=?", new BeanPropertyRowMapper<Account>(Account.class), username);
        //bad code
        if(accounts.size()==0){
            return false;
        }
        return true;
    }
}
