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

    @Override
    public double checkDeposit(String user) {
        /*Good code here
        Account account=jdbcTemplate.queryForObject("Select deposit from account where username=?", new BeanPropertyRowMapper<Account>(Account.class), "duke");
        System.out.println("invoke success");
        return account.getDeposit();*/

        /*Bad Code start*/
        Account account=jdbcTemplate.queryForObject("Select deposit from account where username='"+user+"'", new BeanPropertyRowMapper<Account>(Account.class));
        System.out.println("invoke success");
        return account.getDeposit();
        /*Bad Code end*/
    }



    @Override
    public void deposit(String user, double money) {
         Account account=jdbcTemplate.queryForObject("Select deposit from account where username=?", new BeanPropertyRowMapper<Account>(Account.class), user);
         double deposit= account.getDeposit();
        jdbcTemplate.update("update account set deposit=? where username=?", money+deposit, user);
        //bad code
        System.out.println("Deposit "+money+"successful");
    }


    @Override
    //should add check part
    public void withDraw(String user, double money) {
        Account account=jdbcTemplate.queryForObject("Select deposit from account where username=?", new BeanPropertyRowMapper<Account>(Account.class), user);
        double deposit= account.getDeposit();
        jdbcTemplate.update("update account set deposit=? where username=?", deposit-money, user);
        //bad code
        System.out.println("Withdraw "+money+"successful");
    }

    @Override
    public void createAccount(String user, String psw) {
        if(!userExist(user)){

        }
    }

    @Override
    public boolean logIn(String user, String psw) {
        return false;
    }

    @Override
    public boolean userExist(String user) {
        //jdbcTemplate.query("Select"username"from ");
        return false;
    }
}
