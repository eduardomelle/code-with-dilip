package com.learnkafkastreams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@EnableKafkaStreams
@SpringBootApplication
public class GreetingStreamsSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreetingStreamsSpringbootApplication.class, args);
	}

}
