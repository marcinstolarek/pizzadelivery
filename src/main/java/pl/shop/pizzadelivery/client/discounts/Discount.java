package pl.shop.pizzadelivery.client.discounts;

import pl.shop.pizzadelivery.product.Product;

import java.util.List;

/**
 * Discount idea verified at visitor cart.
 * Every new discount class have to be updated in "getDiscountFromName" method!
 */
public abstract class Discount {
    /**
     * Activity bit for specified discount
     */
    protected boolean active;
    protected String shortName;
    protected String fullName;

    /**
     * Check if discount is possible to use with specified productList (eq. minimum 3 products required, two pizzas in cart, etc.).
     * @param productList - actual list of products in cart
     * @return true if it is possible to use this discount
     */
    public abstract boolean isPossibleToUse(List<Product> productList);

    /**
     * Count total discount. Depends on productList in cart.
     * @param productList - actual list of products in cart
     * @return sum of discount
     */
    public abstract int countDiscount(List<Product> productList);

    /**
     * If discount has parameter (eq. percent of discount) it should be possible to change
     * @param parameter - int paramater to change
     */
    public abstract void changeDiscountParameter(int parameter);

    /**
     * Create ready to use discount from name
     * @param discountShortName - short name of discount
     * @param parameter - parameter of activity (0/>0: not active/active, when positive number is parameter)
     * @return created Discount
     */
    public static Discount getDiscountFromName(String discountShortName, int parameter) {
        if (discountShortName.equals("TwoPizzas")) {
            Discount disc = new DiscountTwoPizzas(parameter);
            disc.setActive(parameter > 0);
            return new DiscountTwoPizzas(parameter);
        }
        return null;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }
}
