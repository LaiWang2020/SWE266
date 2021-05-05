package edu.swe266.service.impl;

import edu.swe266.dao.AccountDao;
import edu.swe266.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component("AccountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void withdrawMoney(String user, double money) {
        accountDao.withDraw(user,money);
    }


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void depositMoney(String user, double money) {
        accountDao.deposit(user, money);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public double checkDeposit(String user) {
        return accountDao.checkDeposit(user);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void createAccount(String user, String psw) {
        accountDao.createAccount(user,psw);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean logIn(String user, String psw) {
        return accountDao.logIn(user, psw);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean userExist(String user) {
        return accountDao.userExist(user);
    }

}
