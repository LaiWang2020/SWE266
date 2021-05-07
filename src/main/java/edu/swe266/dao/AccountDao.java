package edu.swe266.dao;

public interface AccountDao {
    public void withDraw(String username, double money );
    public void deposit(String username, double money);
    public double checkDeposit(String username);
    public void createAccount(String psw,String username);
    public boolean logIn(String username, String psw);
    public boolean userExist(String username);
}
