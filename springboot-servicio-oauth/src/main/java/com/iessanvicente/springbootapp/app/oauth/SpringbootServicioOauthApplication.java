package com.iessanvicente.springbootapp.app.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SpringbootServicioOauthApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioOauthApplication.class, args);
	}
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private Logger log = LoggerFactory.getLogger(SpringbootServicioOauthApplication.class);
	
	@Override
	public void run(String... args) throws Exception {
		String password = "12345";
		for(int i = 0 ; i < 4 ; i++) {
			log.info("Password " + i + ": " + passwordEncoder.encode(password));
		}
		
	}

}
