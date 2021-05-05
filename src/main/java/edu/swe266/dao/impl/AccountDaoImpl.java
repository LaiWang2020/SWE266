package edu.swe266.dao.impl;

import edu.swe266.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void out(String user, double money) {
        jdbcTemplate.update("update account set money=money-? where name=?",money,user);
    }

    @Override
    public void in(String user, double money) {
        jdbcTemplate.update("update account set money=money+? where name=?",money,user);
    }
}
