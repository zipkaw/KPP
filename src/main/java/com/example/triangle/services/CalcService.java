package com.example.triangle.services;

import com.example.triangle.Triangle;

public class CalcService {
    public static int calculatePerimeter(Triangle triangle){
        return triangle.getFirstSide() + triangle.getSecondSide() + triangle.getThirdSide();
    }
    public static double calculateSquare(Triangle triangle){
        int first = triangle.getFirstSide();
        int second = triangle.getSecondSide();
        int third = triangle.getThirdSide();
        double semiPerimeter;
        semiPerimeter = (double)(first + second + third) / 2;
        double square = Math.sqrt(semiPerimeter * Math.abs(semiPerimeter - first) * Math.abs(semiPerimeter - second) * Math.abs(semiPerimeter - third));
        return square;
    }
}
