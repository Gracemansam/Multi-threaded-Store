package interfaces;

import models.Product;
import utility.FileUtility;

import java.io.IOException;
import java.util.HashMap;

public interface iFileUtility {

    boolean checkDuplicate(HashMap<String, Product> productHashMap , String category);

    HashMap<String , Product> createCategoryInventory(HashMap<String, Product> categoryHashMap, FileUtility file, int categoryTotalQuantity, String categoryId, int idIncreament) throws IOException;

}
