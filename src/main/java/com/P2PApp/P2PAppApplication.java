package com.P2PApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@SpringBootApplication
public class P2PAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(P2PAppApplication.class, args);
	}

}
