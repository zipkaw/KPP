package com.example.triangle.streamService;

import com.example.triangle.TriangleSides;
import com.example.triangle.Triangle;
import com.example.triangle.myLogger.MyLogger;
import com.example.triangle.perimeterInterface.PerimeterInterface;
import com.example.triangle.repos.Repos;
import com.example.triangle.services.CalcService;
//import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CalcStream {
    private Repos cache;
    @Autowired
    public CalcStream(Repos cache){
        this.cache = cache;
    }

    public PerimeterInterface result = (numbers) -> {
        Integer sideFlag = 0;
        Triangle result = new Triangle();
        for (Integer side : numbers) {
            if (sideFlag == 0) {
                result.setFirstSide(side);
                sideFlag++;
            } else if (sideFlag == 1) {
                result.setSecondSide(side);
                sideFlag++;
            } else if (sideFlag == 2) {
                result.setThirdSide(side);
                sideFlag++;
            }
        }
        cache.addPerimeter(result, CalcService.calculatePerimeter(result));
        MyLogger.info("Calculated triangle perimeter");
        return CalcService.calculatePerimeter(result);
    };

    public TriangleSides sideStream(List<Integer> arr){
        Double max = arr.stream().map(Integer::doubleValue).max(Comparator.comparingDouble(Double::doubleValue)).orElse(0d);
        Double min = arr.stream().map(Integer::doubleValue).min(Comparator.comparingDouble(Double::doubleValue)).orElse(0d);
        Double mid = arr.stream().mapToDouble(Integer::doubleValue).average().orElse(0);
        return new TriangleSides(min, mid, max);
    }
}
