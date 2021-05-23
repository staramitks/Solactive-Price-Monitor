package com.solactive.price.tracker.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;

import java.util.concurrent.Executors;

@Configuration
public class ThreadBeanConfig {


	@Bean
	@Qualifier("statisticsTaskExecutor")
	public TaskExecutor statisticsTaskExecutor() {
		return new ConcurrentTaskExecutor(Executors.newFixedThreadPool(10));
	}
}
