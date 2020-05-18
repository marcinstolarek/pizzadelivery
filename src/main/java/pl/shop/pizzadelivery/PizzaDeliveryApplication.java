package pl.shop.pizzadelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PizzaDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaDeliveryApplication.class, args);
    }

}

// TODO - order