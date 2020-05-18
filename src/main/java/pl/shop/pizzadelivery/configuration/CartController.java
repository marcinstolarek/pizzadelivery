package pl.shop.pizzadelivery.configuration;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import pl.shop.pizzadelivery.client.VisitorCart;
import pl.shop.pizzadelivery.product.Product;
import pl.shop.pizzadelivery.product.pizza.Pizza;
import pl.shop.pizzadelivery.product.pizza.PizzaComponent;
import pl.shop.pizzadelivery.product.pizza.PizzaSize;

@RestController
//@Scope("session")
public class CartController {

    private VisitorCart cart;

    public CartController() {
        System.out.println("CartController constructor");
        cart = new VisitorCart();
    }

    @GetMapping("/cart")
    public VisitorCart getWholeCart() {
        System.out.println("Get mapping");
        return cart;
    }

    @PostMapping("/cart")
    public Product newProduct(@RequestBody Product newProduct) {
        System.out.println("Post mapping 1");
        cart.addProduct(newProduct);
        return newProduct;
    }

    @PostMapping("/cart/pizza")
    public Pizza newPizza(@RequestParam("size") PizzaSize size/*, @RequestBody Pizza newPizza*/) {
        System.out.println("Post mapping 2");
        Pizza newPizza = new Pizza(size);
        cart.addProduct(newPizza);
        return newPizza;
    }

    @PutMapping("/cart/pizza/{index}/{componentName}")
    public Pizza addOrRemoveComponent(@PathVariable int index, @PathVariable String componentName) {
        System.out.println("Put mapping");
        if (cart.getProductList().size() >= index && cart.getProductList().get(index).getClass() == Pizza.class) {
            Pizza pizza = (Pizza)cart.getProductList().get(index);
            for (PizzaComponent comp : pizza.getComponentList()) { // component already found - delete it
                if (comp.getName().equals(componentName)) {
                    ((Pizza) cart.getProductList().get(index)).removeComponent(componentName);
                    return (Pizza) cart.getProductList().get(index);
                }
            }
            ((Pizza) cart.getProductList().get(index)).addComponent(componentName); // new component
            return (Pizza) cart.getProductList().get(index);
        }
        return null;
    }

    @DeleteMapping("/cart/{index}")
    public void deleteProduct(@PathVariable int index) {
        System.out.println("Delete mapping");
        cart.removeProduct(index);
    }
}
