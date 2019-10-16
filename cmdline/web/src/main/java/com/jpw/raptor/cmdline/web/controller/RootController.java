package com.jpw.raptor.cmdline.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by John on 8/23/2017.
 */
@Controller
public class RootController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @RequestMapping("/")
    public String homePage(Model model) {
        return "index";
    }

    @RequestMapping("/index")
    public String indexPage(Model model) {
        return "index";
    }
}
