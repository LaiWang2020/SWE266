package edu.swe266.dao;

public interface AccountDao {
    public void out(String user, double money );
    public void in(String user, double money);
}
