package com.jean.stardew_valley_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication(scanBasePackages = "com.jean.stardew_valley_api")
@EntityScan(basePackages = "com.jean.stardew_valley_api")
@EnableJpaRepositories(basePackages = {"com.jean.stardew_valley_api"})
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class StardewValleyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StardewValleyApiApplication.class, args);
	}

}
