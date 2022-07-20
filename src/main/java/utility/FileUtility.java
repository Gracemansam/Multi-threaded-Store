package utility;

//import models.Product;
import  models.Product;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileUtility  {

    private BufferedReader reader;
    private String line;
  //  private String[] inventory;
    private String barCategoryProductId = "BP" ;
    private int barIdIncreament = 1;

    public FileUtility(String file) throws FileNotFoundException {
       this.reader = new BufferedReader(new FileReader(file));
    }

   public boolean checkDuplicate(HashMap<String, Product> productHashMap , String category){
        boolean isExisting = false;
        for (Map.Entry<String,Product> product : productHashMap.entrySet()){
            if (product.getKey().equalsIgnoreCase(category)){
                isExisting = true;
            }
        }
        return isExisting;
    }

    public HashMap<String , Product>  createCategoryInventory(HashMap<String, Product> categoryHashMap,  int categoryTotalQuantity, String categoryId, int idIncreament) throws IOException {
        String[] inventory = new String[0];
        while ((this.line = this.reader.readLine()) != null) {
            while ((this.line = this.reader.readLine()) != null) {
                inventory = this.line.split(",");
             //   if (inventory[3].equalsIgnoreCase(categoryName)){
                    if (checkDuplicate(categoryHashMap , inventory[4])){
                        categoryTotalQuantity += Integer.parseInt(inventory[5]);
                        categoryHashMap.put(inventory[4] , new Product(categoryId + (idIncreament++) , inventory[4], inventory[3] , categoryTotalQuantity ,Double.parseDouble(inventory[6]) ));
                    }else {
                        categoryHashMap.put(inventory[4] , new Product(categoryId + (idIncreament++) , inventory[4], inventory[3] , Integer. parseInt(inventory[5]) ,Double.parseDouble(inventory[6]) ));
                    }
              //  }
            }
        }
        return categoryHashMap;
    }


    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getBarCategoryProductId() {
        return barCategoryProductId;
    }

    public void setBarCategoryProductId(String barCategoryProductId) {
        this.barCategoryProductId = barCategoryProductId;
    }

    public int getBarIdIncreament() {
        return barIdIncreament;
    }

    public void setBarIdIncreament(int barIdIncreament) {
        this.barIdIncreament = barIdIncreament;
    }
}
