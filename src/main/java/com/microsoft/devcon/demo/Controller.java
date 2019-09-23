package com.microsoft.devcon.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.MetricTelemetry;
import com.microsoft.devcon.demo.Dto.User;
import com.microsoft.devcon.demo.Dto.OutputDto;

@RestController
@RequestMapping("/")
public class Controller {
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
    TelemetryClient telemetryClient;
	 
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
