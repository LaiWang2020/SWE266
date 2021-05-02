package edu.swe266.controller;

import edu.swe266.service.IMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/cart/")
public class MainController {

    @Autowired
    IMainService IMainService;

}
