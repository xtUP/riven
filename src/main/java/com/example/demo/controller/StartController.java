package com.example.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/web")
public class StartController {
    private static final Logger log = LoggerFactory.getLogger(StartController.class);
    @RequestMapping("/index")
    public  String start(ModelMap map){
        String idCard="412723199506041610";
        String name="张无忌";
        log.warn(idCard);
        log.warn(name);
        return "hello";
    }

    public static void main(String[] args) {
        List<String> sre=null;
        System.out.println(sre.size());
    }
}
