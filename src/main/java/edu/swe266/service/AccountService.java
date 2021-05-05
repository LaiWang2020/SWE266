package edu.swe266.service;

public interface AccountService {

    public void withdrawMoney(String user, double money);
    public void depositMoney(String user, double money);
    public double checkDeposit(String user);
    public void createAccount(String user, String psw);
    public boolean logIn(String user, String psw);
    public boolean userExist(String user);
}
