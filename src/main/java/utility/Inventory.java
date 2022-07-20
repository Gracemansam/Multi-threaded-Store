package utility;

import services.FileUtilityServiceImpl;
import utility.FileUtility;
import models.Product;

import java.io.IOException;
import java.util.HashMap;

public class Inventory {
    private String barCategoryProductId = "P" ;
    private int barIdIncreament = 1;
    private int barCategoryTotalQuantity = 0;

    private static HashMap<String, Product> barsCategory = new HashMap<>();


     public Inventory() throws IOException {
         FileUtilityServiceImpl fileUtility = new FileUtilityServiceImpl("resources/FoodSales.csv");
         FileUtility fileUtility1 = new FileUtility("resources/FoodSales.csv");
         fileUtility.createCategoryInventory(barsCategory, fileUtility1, barCategoryTotalQuantity,  barCategoryProductId , barIdIncreament);
         setBarsCategory(fileUtility.createCategoryInventory(barsCategory, fileUtility1, barCategoryTotalQuantity,  barCategoryProductId , barIdIncreament));
     }

    public String getBarCategoryProductId() {
        return barCategoryProductId;
    }

    public int getBarIdIncreament() {
        return barIdIncreament;
    }

    public int getBarCategoryTotalQuantity() {
        return barCategoryTotalQuantity;
    }

    public HashMap<String, Product> getBarsCategory() {
        return barsCategory;
    }

    public static void setBarsCategory(HashMap<String, Product> barsCategory) {
        Inventory.barsCategory = barsCategory;
    }
}
