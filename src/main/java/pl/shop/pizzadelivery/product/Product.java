package pl.shop.pizzadelivery.product;

/**
 * Product in online shop
 * Have only price field
 */
public abstract class Product {
    protected int price; // no setter

    public Product() {
        price = 0;
    }

    public Product(int price) {
        this.setPrice(price);
    }

    protected void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }
}
