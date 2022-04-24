package com.example.triangle.streamService;

import com.example.triangle.Triangle;
import com.example.triangle.myLogger.MyLogger;
import com.example.triangle.repos.Repos;
import com.example.triangle.services.CalcServ;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CalcStream {
    private Repos cache;

    public ArrayList<Triangle> inversionStream(Stream<Triangle.Sides> sides){

        return sides.map(this::calc).collect(Collectors.toCollection(ArrayList::new));
    }

    public Triangle calc(Stream<Triangle.Sides> sides){
        Triangle result = new Triangle(sides.val1, sides.val2, sides.val3);
        cache.addPerimeter(result, CalcServ.calculatePerimeter(result));
        cache.addSquare(result, CalcServ.calculateSquare(result));
        MyLogger.info("Calculated triangle");
        return result;
    }
}
