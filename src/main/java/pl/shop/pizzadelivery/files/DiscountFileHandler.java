package pl.shop.pizzadelivery.files;

import java.util.HashMap;
import java.util.Map;

/**
 * Read information about discounts (activity)
 */
public abstract class DiscountFileHandler extends FileHandler {
    public static final String DISCOUNT_FILE = "src/pizzaData/pizzaDiscounts";

    /**
     * Read discounts data from file (with default path)
     * @return map of discounts: key - short name of discount, value - 0/>0 (not active or active as parameter)
     */
    public static Map<String, Integer> readDiscounts() {
        return readDiscountsFromFile();
    }

    /**
     * Read discount data from file with default path
     * @return map of discounts: key - short name of discount, value - 0/>0 (not active or active as parameter)
     */
    private static Map<String, Integer> readDiscountsFromFile() {
        return readDiscountsFromFile(DISCOUNT_FILE);
    }

    /**
     * Read discount from specified data file
     * @param fileName - path to file with discounts
     * @return map of discounts: key - short name of discount, value - 0/>0 (not active or active as parameter)
     */
    private static Map<String, Integer> readDiscountsFromFile(String fileName) {
        String content = getFileContent(fileName);
        if (content == null)
            return null;
        return readDiscountFromFileContent(content);
    }

    /**
     * Parse content to map with discounts. Manually added case of discounts.
     * @param content - content of file with specified discounts
     * @return map of discounts: key - short name of discount, value - 0/>0 (not active or active as parameter)
     */
    private static Map<String, Integer> readDiscountFromFileContent (String content) {
        Map<String, Integer> discount = new HashMap<>();
        int index = 0;
        int value = 0;
        String shortNameOfDiscount = null;

        // looking for discounts
        for (int i = 0; i < content.lines().count(); i++) { // check number of lines in file
            if (i == 0) // manually added cases with discounts
                shortNameOfDiscount = "TwoPizzas";
            else
                break; // nothing else to check

            index = content.indexOf(shortNameOfDiscount + ": ");
            if (index >= 0 && index + shortNameOfDiscount.length() + 3 < content.length())
                for (int j = 3; shortNameOfDiscount.length() + j < content.length() && Character.isDigit(content.charAt(index + shortNameOfDiscount.length() + j)); j++) {
                    value *= 10;
                    value += Integer.valueOf(content.charAt(index + shortNameOfDiscount.length() + j));
                }
                discount.put(shortNameOfDiscount, value);
        }
        return discount;
    }
}
