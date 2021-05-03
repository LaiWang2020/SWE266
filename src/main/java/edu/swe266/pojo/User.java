package edu.swe266.pojo;

import org.springframework.stereotype.Service;

@Service
public class User {
    private int accid;
    //information
    private String name;
    private String address;
    private String DOB;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAccid(int accid) {
        this.accid = accid;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }



    public int getAccid() {
        return accid;
    }



    public String getAddress() {
        return address;
    }

    public String getDOB() {
        return DOB;
    }
}
