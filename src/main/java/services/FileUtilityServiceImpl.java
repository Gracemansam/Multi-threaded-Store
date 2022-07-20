package services;

import interfaces.iFileUtility;
import models.Product;
import utility.FileUtility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileUtilityServiceImpl  implements iFileUtility {

    public FileUtilityServiceImpl(String file) throws FileNotFoundException {
        FileUtility fileUtility= new FileUtility(file);
    }
    public  boolean checkDuplicate(HashMap<String, Product> productHashMap , String category){
        boolean isExisting = false;
        for (Map.Entry<String, Product> product : productHashMap.entrySet()){
            if (product.getKey().equalsIgnoreCase(category)){
                isExisting = true;
            }
        }
        return isExisting;
    }

    public HashMap<String , Product>  createCategoryInventory(HashMap<String, Product> categoryHashMap, FileUtility file, int categoryTotalQuantity, String categoryId, int idIncreament) throws IOException {
        String[] inventory = new String[0];
        String line = file.getLine();

            while ((line = file.getReader().readLine()) != null) {
                while ((line = file.getReader().readLine()) != null) {
                    inventory = line.split(",");
                    if (checkDuplicate(categoryHashMap , inventory[4])){
                       categoryTotalQuantity += Integer.parseInt(inventory[5]);
                       categoryHashMap.put(inventory[4] , new Product(categoryId + (idIncreament++) , inventory[4], inventory[3] , categoryTotalQuantity ,Double.parseDouble(inventory[6]) ));
                    }else {
                        categoryHashMap.put(inventory[4] , new Product(categoryId + (idIncreament++) , inventory[4], inventory[3] , Integer.parseInt(inventory[5]) ,Double.parseDouble(inventory[6]) ));
                    }
                }
            }


        return categoryHashMap;
    }


}
