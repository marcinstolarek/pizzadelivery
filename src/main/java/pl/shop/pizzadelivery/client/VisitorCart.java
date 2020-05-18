package pl.shop.pizzadelivery.client;

import org.springframework.beans.factory.annotation.Autowired;
import pl.shop.pizzadelivery.client.discounts.Discount;
import pl.shop.pizzadelivery.product.Product;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Cart with product and discounts. Have total price and count the best discount.
 */
public class VisitorCart {
    @Autowired public static List<Discount> discountList;
    private List<Product> productList; // no setter
    private int totalFullPrice;
    private int totalDiscountedPrice;
    private int totalDiscount;

    /**
     * Create empty cart
     */
    public VisitorCart() {
        productList = new ArrayList<>();
        totalFullPrice = 0;
        totalDiscountedPrice = 0;
        totalDiscount = 0;
    }

    /**
     * Use always when new product added or at least one deleted
     */
    private void recountPrice() {
        countTotalDiscount();
        countTotalFullPrice();
        countTotalDiscountedPrice();
    }

    public List<Product> getProductList() {
        return productList;
    }

    /**
     * Add not null product to cart
     * @param product - product to add
     */
    public void addProduct(@NotNull Product product) {
        productList.add(product);
        recountPrice();
    }

    /**
     * Remove one of products from cart
     * @param index - index of removing product
     * @return true if succeed, false otherwise
     */
    public boolean removeProduct(int index) {
        if (index < productList.size()) {
            productList.remove(index);
            recountPrice();
            return true;
        }
        return false;
    }

    public int getTotalFullPrice() {
        return totalFullPrice;
    }

    /**
     * USE ONLY in recountPrice() !!!
     */
    private void countTotalFullPrice() {
        int price = 0;
        for (Product product : productList)
            price += product.getPrice();
    }

    public int getTotalDiscountedPrice() {
        return totalDiscountedPrice;
    }

    /**
     * USE ONLY in recountPrice() !!!
     */
    private void countTotalDiscountedPrice() {
        totalDiscountedPrice = totalFullPrice - totalDiscount;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    /**
     * Checking all possible discounts and choose the best. Only one discount to benefit in one cart.
     * USE ONLY in recountPrice() !!!
     */
    private void countTotalDiscount() {
        int bestDiscountValue = 0;
        int newDiscountValue;
        for (Discount discount : discountList) {
            newDiscountValue = discount.countDiscount(productList);
            if (newDiscountValue > bestDiscountValue)
                bestDiscountValue = newDiscountValue;
        }
        totalDiscount = bestDiscountValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitorCart that = (VisitorCart) o;
        return Objects.equals(productList, that.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productList);
    }
}
