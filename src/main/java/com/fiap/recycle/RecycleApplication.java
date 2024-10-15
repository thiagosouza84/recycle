package com.fiap.recycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RecycleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecycleApplication.class, args);
	}

	@RestController
	public static class HelloController {
		@GetMapping("/hello")
		public String hello() {
			return "Hello, Recycle!";
		}
	}
}