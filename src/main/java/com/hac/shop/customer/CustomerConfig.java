package com.hac.shop.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.util.Calendar.JANUARY;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner( CustomerRepository repository){
        return  args -> {
            Customer hac = new Customer(
                        "Haro",
                        "hac@email.com",
                        LocalDate.of(2000, Month.JANUARY, 5)

            );

            Customer bobby = new Customer(
                    "Bobby",
                    "bob@email.com",
                    LocalDate.of(2002, Month.MARCH, 5)
            );

            repository.saveAll(List.of(hac, bobby));

        };
    }
}
