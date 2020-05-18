package pl.shop.pizzadelivery.client.discounts;

import pl.shop.pizzadelivery.product.pizza.Pizza;
import pl.shop.pizzadelivery.product.Product;

import java.util.List;

/**
 * Second cheaper pizza has lower price. If more than two pizza, lower price only for the cheapest.
 */
public class DiscountTwoPizzas extends Discount {
    private float discountPercentageSecondPizza; // range 0.0 - 100.0

    /**
     * Discount for second pizza
     * @param discountPercentageSecondPizza - range 0.0 - 100.0
     */
    public DiscountTwoPizzas(float discountPercentageSecondPizza) {
        this.discountPercentageSecondPizza = discountPercentageSecondPizza;
    }

    @Override
    public boolean isPossibleToUse(List<Product> productList) {
        int numOfPizza = 0;
        if (productList.size() < 2)
            return false;
        for (Product prod : productList) { // check if there are at least two pizzas in cart
            if (prod.getClass() == Pizza.class) {
                ++numOfPizza;
                if (numOfPizza >= 2)
                    return true;
            }
        }
        return false;
    }

    @Override
    public int countDiscount(List<Product> productList) {
        int indexOfCheapest = -1;
        int priceOfCheapest = -1;
        if (!isPossibleToUse(productList) || !isActive())
            return -1;
        for (int i = 0; i < productList.size(); i++) { // looking for the cheapest pizza
            if (productList.get(i).getClass() == Pizza.class && (indexOfCheapest == -1 || productList.get(i).getPrice() < priceOfCheapest)) {
                priceOfCheapest = productList.get(i).getPrice();
                indexOfCheapest = i;
            }
        }
        return (priceOfCheapest * (int) this.discountPercentageSecondPizza) / 100;
    }

    @Override
    public boolean isActive() {
        return activity;
    }
}
