package edu.swe266.service;

public interface AccountService {

    public void withdrawMoney(String user, double money);
    public void depositMoney(String user, double money);
}
