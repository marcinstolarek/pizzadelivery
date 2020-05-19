package pl.shop.pizzadelivery.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import pl.shop.pizzadelivery.client.discounts.Discount;
import pl.shop.pizzadelivery.files.DiscountFileHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Create bean with list of discounts.
 * List is being updated every 60 seconds.
 */
@Configuration
public class DiscountChecker {
    List<Discount> list;

    /**
     * Bean with list of discounts
     * @return list of discounts
     */
    @Bean
    public List<Discount> discountList() {
        list = new ArrayList<>();
        checkDiscounts(); // check at the beginning
        return list;
    }

    /**
     * Checking if any changes were present in discount list
     */
    @Scheduled(fixedRate = 60000)
    public void checkDiscounts() {
        Map<String, Integer> discountList = DiscountFileHandler.readDiscounts();
        List<Integer> indexToDelete = new ArrayList<>();
        for (Discount discount : list) { // update every already present discount
            if (discountList.containsKey(discount.getShortName())) { // there is discount present in file
                discount.setActive(discountList.get(discount.getShortName()) > 0); // make active or not active
                discount.changeDiscountParameter(discountList.get(discount.getShortName())); // change parameter if it is present
            }
            else // there is unwanted discount at the list - add to delete list
                indexToDelete.add(list.indexOf(discount));
        }
        while (!indexToDelete.isEmpty()) // delete unwanted discounts
            list.remove((int)indexToDelete.get(indexToDelete.size() - 1)); // deleting from tail to head
        for (Map.Entry<String, Integer> entry : discountList.entrySet()) { // add new discount
            Discount discount = Discount.getDiscountFromName(entry.getKey(), entry.getValue());
            if (discount != null && !list.contains(discount)) // discount not present at list - add new
                list.add(discount);
        }
    }
}
