package com.jean.stardewvalleyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication(scanBasePackages = "com.jean.stardewvalleyapi")
@EntityScan(basePackages = "com.jean.stardewvalleyapi")
@EnableJpaRepositories(basePackages = {"com.jean.stardewvalleyapi"})
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class StardewValleyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StardewValleyApiApplication.class, args);
	}

}
