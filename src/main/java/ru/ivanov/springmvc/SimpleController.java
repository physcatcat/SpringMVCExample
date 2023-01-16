package ru.ivanov.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
