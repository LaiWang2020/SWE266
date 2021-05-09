package edu.swe266.pojo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Account {
    private String accid;
    private String username;
    private String psw;
    private double deposit;

    public void setAccid(String accid) {
        this.accid = accid;
    }



    public String getAccid() {
        return accid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getPsw() {
        return psw;
    }
}
