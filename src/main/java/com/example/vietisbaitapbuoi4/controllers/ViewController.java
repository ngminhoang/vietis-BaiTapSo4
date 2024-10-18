package com.example.vietisbaitapbuoi4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "/dashboard";
    }
}
