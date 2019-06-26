package com.jhaexample.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class Controller {
	@GetMapping
    public String hello() {
        return "Hello JHA!, need to add business logics here";
    }
}
