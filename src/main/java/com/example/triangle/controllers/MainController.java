package com.example.triangle.controllers;

import com.example.triangle.TriangleSides;
import com.example.triangle.Triangle;
import com.example.triangle.repos.Repos;
import com.example.triangle.services.CalcService;
import com.example.triangle.services.Counter;
import com.example.triangle.streamService.CalcStream;
import com.example.triangle.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

    private final CalcStream serv;
    private Repos db;
    private Counter counter;

    @Autowired
    public MainController(CalcStream serv, Repos db, Counter counter){
        this.serv = serv;
        this.db = db;
        this.counter = counter;
    }

    @GetMapping("/")
    public String homeView(@RequestParam(required = false) String value1,
                           @RequestParam(required = false) String value2,
                           @RequestParam(required = false) String value3,
                           Model model) {
        counter.addCount();
        System.out.println(value1 + " " + value2 + " " + value3);
        Integer per = 0;
        double area = 0;
        if (value1 != null && value2 != null && value3 != null) {
            Triangle tr = new Triangle(Integer.parseInt(value1), Integer.parseInt(value2), Integer.parseInt(value3));
            Validation.parsing(value1, value2, value3);
            if (Validation.existing(tr.canExist(Integer.parseInt(value1), Integer.parseInt(value2), Integer.parseInt(value3)))) {
                if (db.getMp().containsKey(tr)) {
                    if (db.getMp().get(tr).getSquare() == 0.0 && db.getMp().get(tr).getPerimeter() == 0.0) {
                        db.getMp().get(tr).setPerimeter(CalcService.calculatePerimeter(tr));
                        db.getMp().get(tr).setSquare(CalcService.calculateSquare(tr));
                    }
                } else {
                    db.addPerimeter(tr, CalcService.calculatePerimeter(tr));
                    db.addSquare(tr, CalcService.calculateSquare(tr));
                }
            }
            per = CalcService.calculatePerimeter(tr);
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
    //LR5
    @PostMapping(value = "/perimeter/stream",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Integer> getPerimeterStream(@RequestBody List<Integer> array){
        return new ResponseEntity<>(serv.result.Calculate(array), HttpStatus.OK);
    }
    //LR6
    @PostMapping(value = "/data/stream",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TriangleSides> getControllerStream(@RequestBody List<Integer> array){
        //serv.calcStream(ent_stream);
        return new ResponseEntity<>(serv.sideStream(array), HttpStatus.OK);
    }

}
