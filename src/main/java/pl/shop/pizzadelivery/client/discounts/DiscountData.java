package pl.shop.pizzadelivery.client.discounts;

/**
 * Discount data. Contains activity bit and names (short to read from file and full to display at web page)
 */
public abstract class DiscountData {
    /**
     * Activity bit for specified discount
     */
    protected boolean active;
    public String shortName;
    public String fullName;

    /**
     * Create with specified name of discount. Activity bit is set as false.
     * @param shortName - short name of discount - in one word - to read from file
     * @param fullName - full name of discount - to display at web page
     */
    public DiscountData(String shortName, String fullName) {
        this.shortName = shortName;
        this.fullName = fullName;
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
