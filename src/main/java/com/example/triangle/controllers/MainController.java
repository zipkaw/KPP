package com.example.triangle.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.triangle.validation.Validation;
@Controller
public class MainController {

    @GetMapping("/")
    public String homeView(@RequestParam(required = false) String value1,
                           @RequestParam(required = false) String value2,
                           @RequestParam(required = false) String value3,
                           Model model){
        System.out.println(value1 + " " + value2 + " " + value3);
        Integer per = 0;
        double area;
        if(value1 != null && value2 != null && value3 != null){
            Validation.parsing(value1,value2,value3);
            per = Integer.parseInt(value1) + Integer.parseInt(value2) + Integer.parseInt(value3);
            area = Math.sqrt(per*(per-Integer.parseInt(value1))*(per-Integer.parseInt(value2))*(per-Integer.parseInt(value3)));
            model.addAttribute("value1", value1);
            model.addAttribute("value2", value2);
            model.addAttribute("value3", value3);
            model.addAttribute("per", per);
            model.addAttribute("area", area);
        }
        return "home_page";
    }
}
