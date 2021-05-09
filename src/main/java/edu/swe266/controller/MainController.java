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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String depositGet(Model model, HttpSession session) {
        String username = (String) session.getAttribute(Const.CURRENT_USER);
        double balance = accountService.checkDeposit(username);
        model.addAttribute("balance",balance);
        model.addAttribute("user",username);
        return "deposit";
    }

    @RequestMapping(value = "/logout")
    public String logoutGet() { return "/login"; }

    @RequestMapping({"/login"})
    public String loginGet(HttpSession session) {
        if(session.getAttribute(Const.CURRENT_USER)!=null){
            return "forward:deposit";
        }
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String loginPost(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            HttpSession session,
            Model model) {
        boolean isSuccess = accountService.logIn(username,password);
        if(isSuccess){
            session.setAttribute(Const.CURRENT_USER,username);
            return "forward:deposit";
        }
        model.addAttribute("password_error","username or password does not match");
        System.out.println("validation fail");
        //return "redirect:/";
        return "login";

    }


    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public String signUpPost(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            HttpSession session,Model model) {
        boolean isSuccess = accountService.createAccount(username,password);
        //BAD CODE: cwe trust boundary
        session.setAttribute(Const.CURRENT_USER,username);
        if(isSuccess){
            //session.setAttribute(Const.CURRENT_USER,username);
            return "redirect:deposit";
        }
        model.addAttribute("user_exist","this username has already been registered");
        return "signUp";
    }


    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String depositPost(
            @RequestParam(value = "save", required = true) String amount,
            HttpSession session,Model model) {
        String username = (String) session.getAttribute(Const.CURRENT_USER);
        boolean isSuccess = accountService.depositMoney(username,Double.parseDouble(amount));
        if(isSuccess){
            double balance = accountService.checkDeposit(username);
            model.addAttribute("balance",balance);
            model.addAttribute("user",username);
        }
        return "deposit";
    }

    @RequestMapping(value = "/withdraw",method = RequestMethod.POST)
    public String withdrawPost(
            @RequestParam(value = "withdraw", required = true) String amount,
            HttpSession session,Model model) {
        String username = (String) session.getAttribute(Const.CURRENT_USER);
        boolean isSuccess = accountService.withdrawMoney(username,Double.parseDouble(amount));
        if(isSuccess){
            double balance = accountService.checkDeposit(username);
            model.addAttribute("balance",balance);
            model.addAttribute("user",username);
        }
        return "deposit";
    }

}
