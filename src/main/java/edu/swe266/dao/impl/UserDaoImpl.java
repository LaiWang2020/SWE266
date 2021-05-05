package edu.swe266.dao.impl;

import edu.swe266.dao.UserDao;
import edu.swe266.pojo.Account;
import edu.swe266.pojo.Bank;
import edu.swe266.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    Account account;
    @Autowired
    Bank bank;
    @Autowired
    User user;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int checkBalance(int account) {
        return 0;
    }

    @Override
    public void createAccount(int account, int psw) {
        this.account.setAccid(12345);
        this.account.setPsw(1234567);
        System.out.println(this.account.getAccid()+" "+this.account.getPsw());
    }

    @Override
    public void modifyAddress(int account, String newInfo) {

    }

    @Override
    public void modifyDOB(int account, String newInfo) {

    }

    @Override
    public void modifyName(int account, String newInfo) {

    }

    @Override
    public boolean checkAccountExist(int account) {
        return false;
    }
}
