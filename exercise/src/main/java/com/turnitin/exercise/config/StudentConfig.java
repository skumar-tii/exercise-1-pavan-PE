package com.turnitin.exercise.config;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class StudentConfig {

	    @Bean
	    public FlywayMigrationStrategy cleanMigrateStrategy() {
	        return flyway -> {
	            flyway.repair();
	            flyway.migrate();
	        };
	    }

}
