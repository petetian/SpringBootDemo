package com.microsoft.devcon.demo;

import static org.junit.Assert.fail;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ControllerTests {
	private static final Logger logger = LoggerFactory.getLogger(ControllerTests.class);

	@LocalServerPort
	private int randomServerPort;

	private final RestTemplate restTemplate = new RestTemplate();
	
	@Value("${spring.security.user.name}")
	private String username;
		
	@Value("${spring.security.user.password}")
	private String password;

	@Test
	public void testGreeting() throws URISyntaxException {
		String plainCreds = username + ":" + password;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
			
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);

		final String baseUrl = "http://localhost:" + randomServerPort + "/greeting";
	    URI uri = new URI(baseUrl);
		    
	    HttpEntity<String> request = new HttpEntity<>(headers);
		    ResponseEntity<String> result = restTemplate.exchange(uri, 
		    		HttpMethod.GET,
		    		request,
		    		String.class);
		     
	    //Verify request succeed
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    logger.debug("********* [{}]", result.getBody());
	    Assert.assertEquals(true, Objects.requireNonNull(result.getBody()).contains("Hello from microsoft"));
	}

}
