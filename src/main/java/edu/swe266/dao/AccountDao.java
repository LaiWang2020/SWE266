package edu.swe266.dao;

public interface AccountDao {
    public boolean withDraw(String username, double money );
    public boolean deposit(String username, double money);
    public double checkDeposit(String username);
    public boolean createAccount(String psw,String username);
    public boolean logIn(String username, String psw);
    public boolean userExist(String username);
}
