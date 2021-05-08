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
    public boolean withdrawMoney(String username, double money) {
        return accountDao.withDraw(username,money);
    }


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean depositMoney(String username, double money) {
        return accountDao.deposit(username, money);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public double checkDeposit(String username) {
        return accountDao.checkDeposit(username);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean createAccount(String username,String psw) {
        return accountDao.createAccount(username,psw);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean logIn(String username, String psw) {
        return accountDao.logIn(username, psw);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean userExist(String username) {
        return accountDao.userExist(username);
    }

}
