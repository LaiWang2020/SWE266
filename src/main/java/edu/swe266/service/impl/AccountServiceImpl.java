package edu.swe266.service.impl;

import edu.swe266.dao.AccountDao;
import edu.swe266.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void withdrawMoney(String user, double money) {
        accountDao.out(user,money);
    }


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void depositMoney(String user, double money) {
        accountDao.in(user, money);
    }
}
