package com.example.triangle.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String homeView(@RequestParam(required = false) Integer value1,
                           @RequestParam(required = false) Integer value2,
                           @RequestParam(required = false) Integer value3,
                           Model model){
        System.out.println(value1 + " " + value2 + " " + value3);
        Integer per = 0;
        double area;

        if(value1 != null && value2 != null && value3 != null){
            per = value1 + value2 + value3;
            area = Math.sqrt(per*(per-value1)*(per-value2)*(per-value3));
            model.addAttribute("value1", value1);
            model.addAttribute("value2", value2);
            model.addAttribute("value3", value3);
            model.addAttribute("per", per);
            model.addAttribute("area", area);
        }
        return "home_page";
    }
}
