package services;

import models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.FileUtility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilityTest {

    FileUtility file;
    FileUtilityServiceImpl fileUtilityService;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        file  = new FileUtility("resources/FoodSales.csv");
        fileUtilityService = new FileUtilityServiceImpl("resources/FoodSales.csv");
    }



    @Test
    void checkDuplicate_true() {
        HashMap<String, Product> categoryHashMap = new HashMap<>();
        categoryHashMap.put("Sugar" , new Product("p234" , "Sugar" , "Sweetener" , 23 , 1.20));

        var expected = true;
        var actual = fileUtilityService.checkDuplicate(categoryHashMap , "Sugar");
        assertEquals(expected , actual);
    }


    @Test
    void checkDuplicate_No_Duplicate() {
        HashMap<String, Product> categoryHashMap = new HashMap<>();
        categoryHashMap.put("Sugar" , new Product("p234" , "Sugar" , "Sweetener" , 23 , 1.20));

        var expected = false;
        var actual = fileUtilityService.checkDuplicate(categoryHashMap , "Bran");
        assertEquals(expected , actual);
    }


    @Test
    void createCategoryInventory() throws IOException {
        HashMap<String, Product> categoryHashMap = new HashMap<>();
        var actual = fileUtilityService.createCategoryInventory(categoryHashMap , file, 0 , "P" , 1);
        var exxpected = categoryHashMap;
        assertEquals(exxpected , actual);
    }
}