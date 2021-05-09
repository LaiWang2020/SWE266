package edu.swe266.controller;

import edu.swe266.service.AccountService;
import edu.swe266.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.logging.LogManager;
import java.util.logging.Logger;


@Controller
public class MainController {

    //private static final Logger logger = LogManager.getLogger(MainController.class);
    @Autowired
    AccountService accountService;

    @RequestMapping({"/"})
    public String main() { return "login"; }

    @RequestMapping(value = "/signUp")
    public String signUpGet(){
        return "signUp";
    }

    @RequestMapping(value = "/forgot")
    public String forgotGet() { return "forgot"; }

    @RequestMapping(value = "/deposit")
    public String depositGet() { return "deposit"; }

    @RequestMapping(value = "/logout")
    public String withdrawGet() { return "logout"; }



    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    @ResponseBody
    public String loginPost(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            HttpSession session,
            Model model) {
        boolean isSuccess = accountService.logIn(username,password);
        //这里如果不判断，直接加入到session里面其实是可以当成一个问题的，就是那个cwe trust boundary
        if(isSuccess){
            session.setAttribute(Const.CURRENT_USER,username);

            return "signUp";
        }
        model.addAttribute("password_error","username or password does not match");
        return "redirect:/";
    }

    @RequestMapping(value = "logout",method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return Const.SUCCESS;
    }



    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public String signUpPost(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password, HttpSession session) {
        boolean isSuccess = accountService.createAccount(username,password);
        //这里如果不判断，直接加入到session里面其实是可以当成一个问题的，就是那个cwe trust boundary
        if(isSuccess){
            session.setAttribute(Const.CURRENT_USER,username);
            return "deposit";
        }
        return "signUp";
    }



    @RequestMapping(value = "/deposit",method = RequestMethod.POST)
    public String deposit(
            @RequestParam(value = "amount", required = true) double amount,HttpSession session) {
        String username = (String) session.getAttribute(Const.CURRENT_USER);
        boolean isSuccess = accountService.depositMoney(username,amount);
        if(isSuccess){
            return Const.SUCCESS;
        }
        return Const.FAILURE;
    }

    @RequestMapping(value = "/withdraw",method = RequestMethod.POST)
    public String withdraw(
            @RequestParam(value = "amount", required = true) double amount,HttpSession session) {
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
