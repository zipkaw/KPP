package com.example.triangle;

import com.example.triangle.repos.Repos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

//import javax.validation.constraints.*;
import java.util.Objects;

@Component
@Data
public class Triangle {
    @JsonProperty
    private Integer firstSide;

    @JsonProperty
    private Integer secondSide;

    @JsonProperty
    private Integer thirdSide;

    public Triangle(){}

    public Triangle(Integer firstSide, Integer secondSide, Integer thirdSide){
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

    public static boolean canExist(Integer first, Integer second, Integer third){
        return (first + second > third && first + third > second && second + third > first);
    }

    public int getFirstSide() {
        return firstSide;
    }

    public void setFirstSide(Integer firstSide) {
        this.firstSide = firstSide;
    }

    public int getSecondSide() {
        return secondSide;
    }

    public void setSecondSide(Integer secondSide) {
        this.secondSide = secondSide;
    }

    public int getThirdSide() { return thirdSide; }

    public void setThirdSide(Integer thirdSide) {
        this.thirdSide = thirdSide;
    }

}
