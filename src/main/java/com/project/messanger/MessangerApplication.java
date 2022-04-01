package com.project.messanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class MessangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessangerApplication.class, args);
		getIp();
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static String getIp(){
		String result = null;
		try {
			result = InetAddress.getLocalHost().getHostAddress();
			System.out.println("result : " + result);
		}catch (UnknownHostException e){
			result = "";
		}
		return result;
	}
}
