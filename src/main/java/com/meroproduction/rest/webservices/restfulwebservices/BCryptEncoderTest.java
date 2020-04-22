package com.meroproduction.rest.webservices.restfulwebservices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoderTest {

    public static void main(String[] args) {
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	for (int i = 1; i <= 10; i++) {
	    String encodedString = bCryptPasswordEncoder.encode("password@!26");
	    System.out.println(encodedString);
	}

    }

}
