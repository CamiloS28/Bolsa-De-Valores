package co.edu.unbosque.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String inicio(Model modelo) {
        return "index";
    }
}