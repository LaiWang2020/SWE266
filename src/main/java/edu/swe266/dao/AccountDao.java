package edu.swe266.dao;

public interface AccountDao {
    public void withDraw(String user, double money );
    public void deposit(String user, double money);
    public double checkDeposit(String user);
    public void createAccount(String user, String psw);
    public boolean logIn(String user, String psw);
    public boolean userExist(String user);
}
