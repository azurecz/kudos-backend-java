package cz.tslavik.kudos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
public class KudosApplication {

	public static void main(String[] args) {

		String activeProfile = System.getenv().getOrDefault("SPRING-PROFILES-ACTIVE", "default");
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, activeProfile);
		SpringApplication.run(KudosApplication.class, args);
	}

}

