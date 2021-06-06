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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Paths;
import java.util.logging.LogManager;
import java.util.logging.Logger;


@Controller
public class MainController {

    //private static final Logger logger = LogManager.getLogger(MainController.class);
    @Autowired
    AccountService accountService;

    @Autowired
    ServletContext context;

    @RequestMapping({"/"})
    public String main(Model model) {
//        model.addAttribute("password_error","username or password does not match");
//        System.out.println("validation fail");
        return "login"; }

    @RequestMapping(value = "/signUp")
    public String signUpGet(Model model){
        model.addAttribute("terms", "terms_and_agreement");
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
    public String logoutGet(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return "/login";
    }

    @RequestMapping({"/login"})
    public String loginGet(@RequestParam(value = "target", required = false) String target,
                           HttpSession session) {
        //http://localhost:8080/swe266_war_exploded/login?target=http://google.com
        //http://localhost:8080/swe266_war_exploded/login?target=http://127.0.0.1:8080/swe266_war_exploded/signUp
        if(target!=null){
            if(target.startsWith("http://127.0.0.1:8080/")){
                return "redirect:"+target;
            }else{
                return "redirect:login";
            }
        }
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
        if (username == "" || password == "") {
            model.addAttribute("password_error","username or password cannot be empty");
            return "login";
        }
        boolean isSuccess = accountService.logIn(username,password);
        if(isSuccess){
            session.setAttribute(Const.CURRENT_USER,username);
            return "forward:deposit";
        }
        model.addAttribute("password_error","username or password does not match");
        return "login";

    }


    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public String signUpPost(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            HttpSession session,Model model) {
        // check empty input
        if (username == "" || password == "") {
            model.addAttribute("user_exist","username or password cannot be empty");
            return "signUp";
        }
        String reg = "^[_\\-\\.0-9a-z]{0,127}$";
        if(!username.matches(reg)||!password.matches(reg)){
            model.addAttribute("user_exist","username or password not in format");
            return "signUp";
        }
        boolean isSuccess = accountService.createAccount(username,password);
        //BAD CODE: cwe trust boundary
        //session.setAttribute(Const.CURRENT_USER,username);
        session.setAttribute(Const.REGISTER_USER,username);
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
        try {
            if(amount.charAt(0)=='0'){
                throw new NumberFormatException();
            }
            boolean isSuccess = accountService.depositMoney(username, Double.parseDouble(amount));
            if (isSuccess) {
                double balance = accountService.checkDeposit(username);
                model.addAttribute("balance", balance);
                model.addAttribute("user", username);
                return "deposit";
            }
        }catch (NumberFormatException nfe){
            System.out.println("Invalid deposit Input ");
        }
        model.addAttribute("deposit_error","deposit error invalid input");

        return "forward:deposit";
    }

    //TODO: money ckeck
    @RequestMapping(value = "/withdraw",method = RequestMethod.POST)
    public String withdrawPost(
            @RequestParam(value = "withdraw", required = true) String amount,
            HttpSession session,Model model) {
        String username = (String) session.getAttribute(Const.CURRENT_USER);
        try{
            if(amount.charAt(0)=='0'){
                throw new NumberFormatException();
            }
            boolean isSuccess = accountService.withdrawMoney(username,Double.parseDouble(amount));
            if(isSuccess){
                double balance = accountService.checkDeposit(username);
                model.addAttribute("balance",balance);
                model.addAttribute("user",username);
                return "deposit";
            }
        }catch (NumberFormatException nfe){
            System.out.println("Invalid withdraw Input ");
        }
        model.addAttribute("withdraw_error","Withdraw error, invalid input");
        return "forward:deposit";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String downloadFile(

            @RequestParam(value = "terms", required = true) String fileName,
            HttpServletRequest request,
            HttpServletResponse response) {


        System.out.println("download File");
        // Bad Code
        String path = context.getRealPath("/resources/terms") + File.separator + fileName;
        // Add logic to check if the final path is still in the /resources/terms folder
        // Consider the case /resources/terms/../WEB-INF/web.xml

//        boolean valid = Paths.get(path).normalize().equals(Paths.get(path));
//        if (!valid){
//            System.out.println("invalid download");
//            return "signUp";
//        }


        InputStream inputStream = null;
        OutputStream outStream = null;
        try {
            File downloadFile = new File(path);
            inputStream = new FileInputStream(downloadFile);

            // get MIME type of the file
            String mimeType = context.getMimeType(path);
            if (mimeType == null) {
                // set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
            }

            // Set content attributes for the response
            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

            // get output stream of the response
            outStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            // write bytes read from the input stream into the output stream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            outStream.flush();
        } catch (IllegalStateException  |IOException ex ) {
            System.out.println("State Error");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                System.out.println("IO Error");
            }
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException ex) {
                System.out.println("OutStream Error");
            }
        }

        return "signUp";
    }

}
