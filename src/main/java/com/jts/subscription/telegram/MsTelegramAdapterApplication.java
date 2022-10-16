package com.jts.subscription.telegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsTelegramAdapterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTelegramAdapterApplication.class, args);
	}

}
