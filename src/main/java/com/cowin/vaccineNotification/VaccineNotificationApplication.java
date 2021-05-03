package com.cowin.vaccineNotification;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class VaccineNotificationApplication {
	public static void main(String[] args) {


		new SpringApplicationBuilder(VaccineNotificationApplication.class)
				.headless(false)
				.run(args);

	}
}
