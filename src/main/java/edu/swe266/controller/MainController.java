package edu.swe266.controller;

import edu.swe266.service.IMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {

    @RequestMapping("/")
    public String main() { return "login"; }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/signUp")
    public String signUp() {
        return "signUp";
    }

    @RequestMapping("/forgot")
    public String forgot() { return "forgot"; }


}
