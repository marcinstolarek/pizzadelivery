package pl.shop.pizzadelivery.client.discounts;

import pl.shop.pizzadelivery.product.Product;

import java.util.List;

/**
 * Discount idea verified at visitor cart.
 */
public interface Discount {
    /**
     * Check if discount is possible to use with specified productList (eq. minimum 3 products required, two pizzas in cart, etc.).
     * @param productList - actual list of products in cart
     * @return true if it is possible to use this discount
     */
    boolean isPossibleToUse(List<Product> productList);

    /**
     * Count total discount. Depends on productList in cart.
     * @param productList - actual list of products in cart
     * @return sum of discount
     */
    int countDiscount(List<Product> productList);
}
