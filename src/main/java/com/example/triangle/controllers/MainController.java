package com.example.triangle.controllers;

import com.example.triangle.Triangle;
import com.example.triangle.repos.Repos;
import com.example.triangle.serviceException.Service;
import com.example.triangle.services.CalcServ;
import com.example.triangle.services.Counter;
import com.example.triangle.streamService.CalcStream;
import com.example.triangle.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class MainController {

    private Repos db;
    private Counter counter;

    @Autowired
    public MainController(Repos db, Counter counter){
        this.db = db;
        this.counter = counter;
    }

    @GetMapping("/")
    public String homeView(@RequestParam(required = false) String value1,
                           @RequestParam(required = false) String value2,
                           @RequestParam(required = false) String value3,
                           Model model){
        counter.addCount();
        System.out.println(value1 + " " + value2 + " " + value3);
        Integer per = 0;
        double area = 0;
        if(value1 != null && value2 != null && value3 != null){
            Triangle tr = new Triangle(Integer.parseInt(value1), Integer.parseInt(value2), Integer.parseInt(value3));
            Validation.parsing(value1, value2, value3);
            if(Validation.existing(tr.canExist(Integer.parseInt(value1), Integer.parseInt(value2), Integer.parseInt(value3)))){
                if (db.getMp().containsKey(tr)) {
                    if (db.getMp().get(tr).getSquare() == 0.0 && db.getMp().get(tr).getPerimeter() == 0.0) {
                        db.getMp().get(tr).setPerimeter(CalcServ.calculatePerimeter(tr));
                        db.getMp().get(tr).setSquare(CalcServ.calculateSquare(tr));
                    }
                } else {
                    db.addPerimeter(tr, CalcServ.calculatePerimeter(tr));
                    db.addSquare(tr, CalcServ.calculateSquare(tr));
                }
            }
            per = CalcServ.calculatePerimeter(tr);
            area = db.getMp().get(tr).getSquare();
            model.addAttribute("value1", value1);
            model.addAttribute("value2", value2);
            model.addAttribute("value3", value3);
            model.addAttribute("per", per);
            model.addAttribute("area", area);
        }
        return "home_page";
    }
    @GetMapping("/count")
    public ResponseEntity getCount(){
        return new ResponseEntity(counter.getCount(), HttpStatus.OK);
    }

    @PostMapping("/Stream")
    public ArrayList<Triangle> getControllerStream(@RequestBody ArrayList<Triangle.Sides> ent_stream){
        ArrayList<Triangle> result = CalcStream.calc(ent_stream.stream());
        return result;
    }

}
