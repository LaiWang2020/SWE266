package edu.swe266.dao;

public interface UserDao {
     /**
      *
      * @param account user's account
      * @return balance of the account
      */
     int checkBalance(int account);

     void createAccount(int account, int psw);

     void modifyAddress(int account, String newInfo);

     void modifyDOB(int account, String newInfo);

     void modifyName(int account, String newInfo);

     /**
      *
      * @param account user's account
      * @return whether this account has already exist
      */
     boolean checkAccountExist(int account);

}
