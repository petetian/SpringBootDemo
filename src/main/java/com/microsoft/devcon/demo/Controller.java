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

import com.microsoft.applicationinsights.telemetry.MetricTelemetry;
import com.microsoft.devcon.demo.entity.Customer;

@RestController
@RequestMapping("/")
public class Controller {
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String insights() {
   		return "Spring boot is running!";
	}

	@GetMapping("/greeting")
	public String greeting() {
		String greetingMsg = "Hello from Microsoft";
		
		return greetingMsg;
	}
	
	@GetMapping("/customers")
	public List<Customer> customers() {			
		try {
			List<Customer> customers;
			
			long startTime = System.nanoTime();
			customers = userService.findAll();
			long endTime = System.nanoTime();
			
			MetricTelemetry benchmark = new MetricTelemetry();
			benchmark.setName("DB query");
			benchmark.setValue(endTime - startTime);
			
			Runtime runtime = Runtime.getRuntime();
			benchmark.setName("free memory");
			benchmark.setValue(runtime.freeMemory());
						
			return customers;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
		}
	}
	
	@GetMapping("/mockerrors")
	public String mockErrors() {
		return "mock error";
	}

	@PostMapping("/create")
	@ResponseBody
	public Customer add(@RequestBody Customer inputDto) {
		logger.info("input: {}", inputDto.toString());

		Customer outputDto = userService.save(inputDto);
				
		return outputDto;
	}

}
