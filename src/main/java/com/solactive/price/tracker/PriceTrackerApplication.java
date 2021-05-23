package com.solactive.price.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync(proxyTargetClass = true)
@SpringBootApplication
public class PriceTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceTrackerApplication.class, args);
	}

}
