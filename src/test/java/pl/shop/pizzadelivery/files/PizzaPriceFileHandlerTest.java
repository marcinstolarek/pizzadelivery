package pl.shop.pizzadelivery.files;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.TestInstance;
import pl.shop.pizzadelivery.product.pizza.PriceSize;

import static pl.shop.pizzadelivery.files.FileHandler.getFileContent;
import static pl.shop.pizzadelivery.files.PizzaPriceFileHandler.readPriceSize;

/**
 * Test for class
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PizzaPriceFileHandlerTest {
    private FileWriter writer = null;
    private File file = null;
    private String testFileName = "test_writer";

    /**File file
     * Create file to test.
     * It has comment at first line and two components ("comp1" and "comp2") in next two lines
     */

    @BeforeAll
    void createFile() {
        String firstLine = "#comment\n";
        String secondLine = "comp1: 10, 20, 30\n";
        String thirdLine = "comp2: 2, 4, 5";
        try {
            file = new File(testFileName);
            writer = new FileWriter(file);
            writer.write(firstLine, 0, firstLine.length());
            writer.write(secondLine, 0, secondLine.length());
            writer.write(thirdLine, 0, thirdLine.length());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if content exist
     */
    @Test
    void fileHasBeenRead() {
        assertNotNull(getFileContent(testFileName)); // file must exist and not be null
    }

    /**
     * Check if finding component is ok
     */
    @Test
    void checkFindingComponent() {
        assertNotNull(readPriceSize("dough")); // from original file
        assertNull(readPriceSize("non_exist_component"));// from original file
        assertNotNull(readPriceSize("comp1", testFileName)); // from predefined file
    }

    /**
     * Check if prices were properly read
     */
    @Test
    void checkPrices() {
        PriceSize price = readPriceSize("comp1", testFileName);
        assertEquals(10, price.getSmall());
        assertEquals(20, price.getNormal());
        assertEquals(30, price.getBig());

        price = readPriceSize("comp2", testFileName);
        assertEquals(2, price.getSmall());
        assertEquals(4, price.getNormal());
        assertEquals(5, price.getBig());
    }

    /**
     * Close and delete test file
     */
    @AfterAll
    void closeAndDeleteFile() {
        try { // close file
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.deleteOnExit();
    }
}