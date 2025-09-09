package com.example.bankcards.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(name = "/api")
public class MainController {


    @GetMapping()
    public void getMainPage(){

    }
}
