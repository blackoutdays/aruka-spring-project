package iitu.edu.kz.aruka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocumentationController {

    @GetMapping("/redoc.html")
    public String redoc() {
        return "redoc";  // This corresponds to redoc.html in the templates directory
    }
}