package edu.swe266.dao;

public interface UserDao {


     void createAccount(int account, int psw);


     /**
      *
      * @param account user's account
      * @return whether this account has already exist
      */
     boolean checkAccountExist(int account);

}
