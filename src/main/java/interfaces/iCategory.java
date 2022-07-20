package interfaces;

import models.Product;

import java.util.HashMap;

public interface iCategory {
     void searchByCategory(String categoryName , HashMap<String , Product> inventory);
     void getAllCategory( HashMap<String , Product> inventory);
}
