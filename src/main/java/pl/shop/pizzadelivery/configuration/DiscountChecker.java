package pl.shop.pizzadelivery.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import pl.shop.pizzadelivery.client.discounts.Discount;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DiscountChecker {
    List<Discount> list;

    @Bean
    public List<Discount> discountList() {
        list = new ArrayList<>();
        return list;
    }

    @Scheduled(fixedRate = 60000)
    public void checkDiscounts() {
        ; // TODO - check discount file and (re)active discounts
    }
}
