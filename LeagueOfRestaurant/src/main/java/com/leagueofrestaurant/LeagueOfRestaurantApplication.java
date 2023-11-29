package com.leagueofrestaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LeagueOfRestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeagueOfRestaurantApplication.class, args);
    }

}
