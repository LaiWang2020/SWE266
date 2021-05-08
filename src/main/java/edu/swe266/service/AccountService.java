package edu.swe266.service;

public interface AccountService {

    //TODO: return type of withdrawMoney and depositMoney and creat Account should be boolean
    public boolean withdrawMoney(String username, double money);
    public boolean depositMoney(String username, double money);
    public double checkDeposit(String username);
    public boolean createAccount(String username,String psw);
    public boolean logIn(String username, String psw);
    public boolean userExist(String username);
}
