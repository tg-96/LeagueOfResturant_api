package com.leagueofrestaurant;

import com.leagueofrestaurant.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
//@Import(WebConfig.class)
public class LeagueOfRestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeagueOfRestaurantApplication.class, args);
    }

}
