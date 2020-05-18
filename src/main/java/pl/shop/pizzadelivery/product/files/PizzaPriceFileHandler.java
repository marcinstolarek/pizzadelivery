package pl.shop.pizzadelivery.product.files;

import pl.shop.pizzadelivery.product.pizza.PriceSize;

/**
 * Read prizes (from file) to all sizes
 */
public abstract class PizzaPriceFileHandler extends FileHandler {
    public static final String PIZZA_PRICE_FILE = "src/pizzaData/pizzaPrice";

    /**
     * Read component price size from PIZZA_PRICE_FILE
     * @param componentName - name of component to read price
     * @return price of component to all sizes
     */
    public static PriceSize readPriceSize(String componentName) {
        return readPriceFromFileContent(componentName, getFileContent(PIZZA_PRICE_FILE));
    }

    /**
     * Read component price size from specified file
     * @param componentName - name of component to read price
     * @return price of component to all sizes
     */
    public static PriceSize readPriceSize(String componentName, String fileName) {
        return readPriceFromFileContent(componentName, getFileContent(fileName));
    }

    /**
     * Read price from file content
     * @param componentName - name of component to read price
     * @param fileContent - whole text read in file
     * @return price of component to all sizes
     */
    private static PriceSize readPriceFromFileContent(String componentName, String fileContent) {
        int startIndex = fileContent.indexOf(componentName + ":") + componentName.length() + 1;
        if (startIndex < componentName.length() + 1) // no such component specified in file
            return null;
        int endIndex = fileContent.indexOf("\n", startIndex);
        if (endIndex < 0)
            endIndex = fileContent.length() - 1;
        if (startIndex > endIndex)
            return null;

        String componentContent = fileContent.substring(startIndex, endIndex);
        int price[] = new int[3];
        int index = 0;
        String temp = "";
        for (int i = 0; i < componentContent.length(); i++) {
            if (Character.isDigit(componentContent.charAt(i))) { // add digit
                temp += componentContent.charAt(i);
            }
            if (componentContent.charAt(i) == ',' || i == componentContent.length() - 1) { // save price info
                price[index] = Integer.parseInt(temp);
                temp = "";
                ++index;
            }
        }
        return new PriceSize(price[0], price[1], price[2]);
    }
}
