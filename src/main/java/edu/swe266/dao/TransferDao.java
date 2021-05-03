package edu.swe266.dao;


public interface TransferDao {
    /**
     *
     * @param account user's account
     * @param amount amount want to withdraw from Bank
     */
    void withdrawFromBank(int account, int amount);

    /**
     *
     * @param account user's account
     * @param amount amount want to deposit to Bank
     */
    void depositToBank(int account, int amount);

    /**
     *
     * @param balance Bank's balance
     * @param amount  operation's amount
     * @return whether balance has sufficient money
     */

    boolean checkBankBalance(int balance, int amount);

    /**
     *
     * @param balance user's balance
     * @param amount operation's amount
     * @return whether user has sufficient money
     */
    boolean checkAccountBalance(int balance, int amount);

}
