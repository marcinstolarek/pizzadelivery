package pl.shop.pizzadelivery.product.pizza;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Define pizza component with the name and prices of all size
 */
public class PizzaComponent {
    private String name;
    private PriceSize price;

    public PizzaComponent(@NotNull String name, PriceSize price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PriceSize getPrice() {
        return price;
    }

    public void setPrice(@NotNull PriceSize price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PizzaComponent{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    /**
     * Price doesn't affect the result
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaComponent that = (PizzaComponent) o;
        return name.equals(that.name);
    }

    /**
     * Price doesn't affect the result
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
