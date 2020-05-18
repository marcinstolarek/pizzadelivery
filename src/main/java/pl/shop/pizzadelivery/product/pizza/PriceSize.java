package pl.shop.pizzadelivery.product.pizza;

import java.util.Objects;

/**
 * Contains price of pizza in three sizes: big, normal and small as int (multiplied by 100, 1.00 is typed as 100)
 */
public class PriceSize {
    private int big;
    private int normal;
    private int small;

    public PriceSize(int small, int normal, int big) {
        this.small = small;
        this.normal = normal;
        this.big = big;
    }

    /**
     * Get price by specified pizza size
     * @param size - size of pizza
     * @return price for specified size
     */
    public int getPriceBySize(PizzaSize size) {
        switch (size) {
            case SMALL: return small;
            case NORMAL: return normal;
            case BIG: return big;
        }
        return 0;
    }

    public float getBig() {
        return big;
    }

    public void setBig(int big) {
        this.big = big;
    }

    public float getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public float getSmall() {
        return small;
    }

    public void setSmall(int small) {
        this.small = small;
    }

    @Override
    public String toString() {
        return "PriceSize{" +
                "big=" + big +
                ", normal=" + normal +
                ", small=" + small +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceSize priceSize = (PriceSize) o;
        return big == priceSize.big &&
                normal == priceSize.normal &&
                small == priceSize.small;
    }

    @Override
    public int hashCode() {
        return Objects.hash(big, normal, small);
    }
}
