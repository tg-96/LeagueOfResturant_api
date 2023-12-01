package com.leagueofrestaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing
@SpringBootApplication
//@Import(WebConfig.class)
public class LeagueOfRestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeagueOfRestaurantApplication.class, args);
    }

}
