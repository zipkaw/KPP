package com.example.triangle;

import com.example.triangle.repos.Repos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

//import javax.validation.constraints.*;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Component
@Data
@Entity
public class Triangle {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private  Long id;
    private Integer firstSide;
    private Integer secondSide;
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
