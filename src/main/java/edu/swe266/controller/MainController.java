package edu.swe266.controller;

import edu.swe266.service.AccountService;
import edu.swe266.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class MainController {

    @Autowired
    AccountService accountService;

    @RequestMapping("/")
    public String main() { return "login"; }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session) {
        boolean isSuccess = accountService.logIn(username,password);
        //这里如果不判断，直接加入到session里面其实是可以当成一个问题的，就是那个cwe trust boundary
        if(isSuccess){
            session.setAttribute(Const.CURRENT_USER,username);
            return Const.SUCCESS;
        }
        return Const.FAILURE;
    }

    @RequestMapping(value = "logout",method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return Const.SUCCESS;
    }

    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public String signUp(String username, String password, HttpSession session) {
        boolean isSuccess = accountService.createAccount(username,password);
        //这里如果不判断，直接加入到session里面其实是可以当成一个问题的，就是那个cwe trust boundary
        if(isSuccess){
            session.setAttribute(Const.CURRENT_USER,username);
            return Const.SUCCESS;
        }
        return Const.FAILURE;
    }

    @RequestMapping(value = "/forgot")
    public String forgot() { return "forgot"; }

    @RequestMapping(value = "/deposit",method = RequestMethod.POST)
    public String deposit(double amount,HttpSession session) {
        String username = (String) session.getAttribute(Const.CURRENT_USER);
        boolean isSuccess = accountService.depositMoney(username,amount);
        if(isSuccess){
            return Const.SUCCESS;
        }
        return Const.FAILURE;
    }

    @RequestMapping(value = "/withdraw",method = RequestMethod.POST)
    public String withdraw(double amount,HttpSession session) {
        String username = (String) session.getAttribute(Const.CURRENT_USER);
        boolean isSuccess = accountService.withdrawMoney(username,amount);
        if(isSuccess){
            return Const.SUCCESS;
        }
        return Const.FAILURE;
    }

    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public double checkDeposit(HttpSession session) {
        String username = (String) session.getAttribute(Const.CURRENT_USER);
        double deposit = accountService.checkDeposit(username);
        return deposit;
    }


}
