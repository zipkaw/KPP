package com.example.triangle.controllers;

import com.example.triangle.Triangle;
import com.example.triangle.repos.TriangleRepository;
import com.example.triangle.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JPAController {


    private final TriangleRepository triangleRepository;

    @Autowired
    public JPAController(TriangleRepository triangleRepository) {
        this.triangleRepository = triangleRepository;
    }

    @ResponseBody
    @RequestMapping("/JPA")
    public String home() {
        String html = "";
        html += "<ul>";
        html += " <li><a href='/testInsert'>Test Insert</a></li>";
        html += " <li><a href='/showAllEmployee'>Show All Employee</a></li>";
        html += " <li><a href='/showFullNameLikeTom'>Show All 'Tom'</a></li>";
        html += " <li><a href='/deleteAllEmployee'>Delete All Employee</a></li>";
        html += "</ul>";
        return html;
    }
    String insert = "<!DOCTYPE html>\n" +
            "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Title</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<form action=\"/insert\" method=\"get\">\n" +
            "    <input name=\"value1\" th:value=\"${value1}\"> <br>\n" +
            "    <input name=\"value2\" th:value=\"${value2}\"> <br>\n" +
            "    <input name=\"value3\" th:value=\"${value3}\"> <br>\n" +
            "    <button>Вставить</button>\n" +
            "    <p></p>\n" +
            "</form>\n" +
            "\n" +
            "</body>\n" +
            "</html>";

    @GetMapping(value = "/insert", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String insertion(@RequestParam(required = false) String value1,
                           @RequestParam(required = false) String value2,
                           @RequestParam(required = false) String value3,
                           Model model) {
        Long empIdMax = this.triangleRepository.getMaxId();
        long id = empIdMax + 1;
        Triangle triangle = new Triangle();
        if (value1 != null && value2 != null && value3 != null) {
            triangle.setFirstSide(Integer.parseInt(value1));
            triangle.setSecondSide(Integer.parseInt(value2));
            triangle.setThirdSide(Integer.parseInt(value3));
            Validation.parsing(value1, value2, value3);
        }
        triangle.setId(id);
        this.triangleRepository.save(triangle);
        return insert;
    }

    @ResponseBody
    @RequestMapping("/showAllEmployee")
    public String showAllEmployee() {

        Iterable<Triangle> triangles = this.triangleRepository.findAll();

        String html = "";
        for (Triangle tr : triangles) {
            html += tr + "<br>";
        }

        return html;
    }
}
