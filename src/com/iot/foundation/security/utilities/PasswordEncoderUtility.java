package com.iot.foundation.security.utilities;

import java.io.PrintStream;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtility {
	public static void main(String[] args) {
		System.out.println("Now encoding: " + args[0]);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword1 = encoder.encode(args[0]);
		String encodedPassword2 = encoder.encode(args[1]);

		System.out.println("Encoding password1 --> " + encodedPassword1);
		System.out.println("Encoding password2 --> " + encodedPassword2);
		System.out.println("Passwords match? -->" + encoder.matches(args[0], encodedPassword1));
		System.out.println("Passwords match? -->" + encoder.matches(args[1], encodedPassword2));
	}
}