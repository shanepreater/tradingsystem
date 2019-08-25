package com.opportunitywatcher.tradesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
@EnableConfigurationProperties(WebConfig.class)
public class TradesystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradesystemApplication.class, args);
	}

	@RequestMapping("/api/echo")
	public Principal revealTheName(Principal principal) {
		return principal;
	}

	@RequestMapping("/api/hello")
	public String hello() {
		return "Hello there";
	}
}
