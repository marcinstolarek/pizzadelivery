package pl.shop.pizzadelivery.product.pizza;

import pl.shop.pizzadelivery.product.Product;
import pl.shop.pizzadelivery.files.PizzaPriceFileHandler;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Information about selected pizza. Always have dough component and it is impossible to delete it.
 * Variable maxNumOfComponents should be written before create first instance of object. It counts all components instead of dough - minimum is 0 (just dough of pizza).
 */
public final class Pizza extends Product {
    private List<PizzaComponent> componentList; // no setter
    private PizzaSize size;
    public static int maxNumOfComponents;

    /**
     * Create SMALL pizza with one component - dough as a base
     */
    public Pizza() {
        super();
        size = PizzaSize.SMALL;
        componentList = new ArrayList<>();
        addComponent("dough");
        recountPrice();
    }

    /**
     * Create pizza with one component - dough as a base
     * @param size - specified size
     */
    public Pizza(@NotNull PizzaSize size) {
        super();
        this.size = size;
        componentList = new ArrayList<>();
        addComponent("dough");
        recountPrice();
    }

    /**
     *
     * @param size - pizza size
     * @param componentName - components without dough (it is obvious)
     */
    public Pizza(@NotNull PizzaSize size, String ... componentName) {
        this(size);
        for (String name : componentName) {
            if (name.equals("dough")) // skip dough
                continue;
            addComponent(name);
        }
        recountPrice();
    }

    /**
     * Get components price depends on its name
     * @param name - name of component
     * @return full prices for each size
     */
    private PriceSize getPriceOfComponent(String name) {
        return PizzaPriceFileHandler.readPriceSize(name);
    }

    /**
     * Change size of pizza and recount its price
     * @param newSize - new specified size of pizza. @NotNull used.
     */
    private void resize(@NotNull PizzaSize newSize) {
        if (newSize.equals(size))
            return;
        size = newSize;
        recountPrice();
    }

    /**
     * Recount price after any change. Count each element at ComponentList
     */
    private void recountPrice() {
        int newPrice = 0;
        for (PizzaComponent comp : componentList) {
            newPrice += comp.getPrice().getPriceBySize(this.size);
        }
        price = newPrice;
    }

    /**
     * Add new compoment to pizza
     * @param componentName - name of component
     */
    public void addComponent(String componentName) {
        PizzaComponent newComponent = new PizzaComponent(componentName, getPriceOfComponent(componentName));
        if (newComponent.getPrice() != null) { // add only recognized components
            componentList.add(newComponent);
            recountPrice();
        }
    }

    /**
     *
     * @param componentName
     * @return true if removed, false if component wasn't found
     */
    public boolean removeComponent(String componentName) {
        boolean isRemoved = componentList.remove(new PizzaComponent(componentName, getPriceOfComponent(componentName)));
        if (isRemoved)
            recountPrice();
        return isRemoved;
    }

    public List<PizzaComponent> getComponentList() {
        return componentList;
    }

    public PizzaSize getSize() {
        return size;
    }

    public void setSize(PizzaSize size) {
        resize(size);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "componentList=" + componentList +
                ", size=" + size +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return price == pizza.price &&
                Objects.equals(componentList, pizza.componentList) &&
                size == pizza.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(componentList, size, price);
    }
}
