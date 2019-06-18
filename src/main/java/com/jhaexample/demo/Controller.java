package com.jhaexample.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@RequestMapping("/hello")
    public String hello() {
        return "Hello JHA!, need to add business logics here";
    }
}
