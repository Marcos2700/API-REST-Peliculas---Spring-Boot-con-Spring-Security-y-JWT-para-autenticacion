package com.example.springsecuritycifrado;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringSecurityCifradoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void passwordEncode(){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String passw = passwordEncoder.encode("");

		System.out.println(passw);
	}
}
