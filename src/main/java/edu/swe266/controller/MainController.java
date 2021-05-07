package edu.swe266.controller;

import edu.swe266.service.AccountService;
import edu.swe266.service.IMainService;
import edu.swe266.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        }
        return "login";
    }

    @RequestMapping("/signUp")
    public String signUp() {
        return "signUp";
    }

    @RequestMapping("/forgot")
    public String forgot() { return "forgot"; }


}
