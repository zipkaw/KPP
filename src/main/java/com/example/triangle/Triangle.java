package com.example.triangle;

import com.example.triangle.repos.Repos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

//import javax.validation.constraints.*;
import java.util.Objects;

@Component
public class Triangle {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int firstSide;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int secondSide;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int thirdSide;

    public Triangle(){}
    public class Sides{
        public  int val1;
        public  int val2;
        public  int val3;
        public Sides (int val1, int val2, int val3){
            this.val1= val1;
            this.val2= val2;
            this.val3= val3;
        }
    }
    public Triangle(int firstSide, int secondSide, int thirdSide){
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide = thirdSide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return firstSide == triangle.firstSide && secondSide == triangle.secondSide && thirdSide == triangle.thirdSide;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstSide, secondSide, thirdSide);
    }

    public static boolean canExist(int first, int second, int third){
        return (first + second > third && first + third > second && second + third > first);
    }

    public int getFirstSide() {
        return firstSide;
    }

    public void setFirstSide(int firstSide) {
        this.firstSide = firstSide;
    }

    public int getSecondSide() {
        return secondSide;
    }

    public void setSecondSide(int secondSide) {
        this.secondSide = secondSide;
    }

    public int getThirdSide() { return thirdSide; }

    public void setThirdSide(int thirdSide) {
        this.thirdSide = thirdSide;
    }

}