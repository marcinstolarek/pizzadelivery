package pl.shop.pizzadelivery.files;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DiscountFileHandlerTest {
    private FileWriter writer = null;
    private File file = null;
    private String testFileName = "test_writer";

    /**
     * Create file to test.
     * It has comment at first line and two discounts ("TwoPizzas" as active and "BigAsNormal" as not active) in next two lines
     */
    @BeforeEach
    void setUp() {
        String firstLine = "#comment\n";
        String secondLine = "TwoPizzas: 50\n";
        String thirdLine = "BigAsNormal: 0";
        try {
            file = new File(testFileName);
            writer = new FileWriter(file);
            writer.write(firstLine, 0, firstLine.length());
            writer.write(secondLine, 0, secondLine.length());
            writer.write(secondLine, 0, thirdLine.length());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if file is read correct
     */
    void checkDiscounts() {
        Map<String, Integer> discountList = DiscountFileHandler.readDiscounts();
        assertTrue(discountList.containsKey("TwoPizzas") && discountList.get("TwoPizzas") == 50);
        assertTrue(discountList.containsKey("BigAsNormal") && discountList.get("BigAsNormal") == 1);
        assertEquals(2, discountList.size());
    }

    /**
     * Close and delete test file
     */
    @AfterEach
    void tearDown() {
        try { // close file
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.deleteOnExit();
    }
}