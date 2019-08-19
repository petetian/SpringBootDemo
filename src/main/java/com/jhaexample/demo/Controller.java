package com.jhaexample.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jhaexample.demo.Dto.InputDto;
import com.jhaexample.demo.Dto.OutputDto;

@RestController
@RequestMapping("/")
public class Controller {
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	@GetMapping("/")
	public String hello() {
		return "Spring boot is running!";
	}

	@GetMapping("/greeting")
	public String greeting() {
		return "Hello JHA!, need to add business logics here";
	}

	@PostMapping("/lending")
	@ResponseBody
	public OutputDto add(@RequestBody InputDto inputDto) {
		logger.info("input: {}", inputDto.toString());

		//TODO:
		// add business logics here
		
		OutputDto outputDto = new OutputDto(100, "return results");

		return outputDto;
	}

}
