package edu.swe266.pojo;

import org.springframework.stereotype.Service;

@Service
public class Bank {
    private String bankName;
    private int bankBalance;


    public void setBankBalance(int bankBalance) {
        this.bankBalance = bankBalance;
    }

    public int getBankBalance() {
        return bankBalance;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }
}
