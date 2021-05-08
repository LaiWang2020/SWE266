package edu.swe266.service;

public interface AccountService {

    //TODO: return type of withdrawMoney and depositMoney and creat Account should be boolean
    public void withdrawMoney(String username, double money);
    public void depositMoney(String username, double money);
    public double checkDeposit(String username);
    public void createAccount(String username,String psw);
    public boolean logIn(String username, String psw);
    public boolean userExist(String username);
}
