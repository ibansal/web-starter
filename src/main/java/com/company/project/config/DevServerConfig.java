package com.company.project.config;

import com.company.project.dto.MobileApiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@Profile("dev")
@PropertySource(value = { "classpath:dev.properties" })
public class DevServerConfig {

	@Autowired
	Environment env;

	@Bean
	public MobileApiDto mobileApiDto() {
		System.out.println("In dev");
		String baseUrl = env.getProperty("api.urls.base_url", String.class);
		MobileApiDto mobileApiDto = new MobileApiDto();
		return mobileApiDto;
	}

}
