package com.microsoft.devcon.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.MetricTelemetry;
import com.microsoft.devcon.demo.Dto.OutputDto;
import com.microsoft.devcon.demo.entity.User;
import com.microsoft.devcon.demo.repo.UserRepo;

@RestController
@RequestMapping("/")
public class Controller {
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
    TelemetryClient telemetryClient;
	
	@Autowired
	UserRepo userRepo;
	
	@GetMapping("/")
	public String insights() {
		MetricTelemetry sample = new MetricTelemetry();
	    sample.setName("metric name");
	    sample.setValue(42.3);
	    telemetryClient.trackMetric(sample);
	    
	    telemetryClient.trackEvent("Spring Boot is running");
	    
		return "Spring boot is running!";
	}

	@GetMapping("/greeting")
	public String greeting() {
		String greetingMsg = "Hello from microsoft";
		
		telemetryClient.trackEvent("/greeting is triggered");
		
		telemetryClient.trackTrace("return message: " + greetingMsg);
		return greetingMsg;
	}
	
	@GetMapping("/users")
	public List<User> users() {
		try {
			return (List<User>) userRepo.findAll();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
		}
	}

	@PostMapping("/newuser")
	@ResponseBody
	public OutputDto add(@RequestBody User inputDto) {
		logger.info("input: {}", inputDto.toString());

		//TODO:
		// add business logics here
		
		OutputDto outputDto = new OutputDto(100, "return results");

		return outputDto;
	}

}
