package com.example.triangle.streamService;

import com.example.triangle.Triangle;
import com.example.triangle.myLogger.MyLogger;
import com.example.triangle.repos.Repos;
import com.example.triangle.services.CalcServ;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CalcStream {
    private Repos cache;

    public ArrayList<Triangle> calcStream(ArrayList<Integer> TrStream){
        return TrStream.stream().map(this::calcPer).collect(Collectors.toCollection(ArrayList::new));
    }
    public Triangle calcPer(Integer TrStream){
        Triangle result = new Triangle(TrStream, TrStream, TrStream);
        Integer per = CalcServ.calculatePerimeter(result);
        MyLogger.info("Calculated triangle");
        return result;
    }
}
