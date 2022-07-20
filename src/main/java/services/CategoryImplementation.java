package services;

import interfaces.iCategory;
import models.Product;

import java.util.HashMap;
import java.util.Map;

public class CategoryImplementation implements iCategory {

    @Override
    public  void searchByCategory(String categoryName , HashMap<String , Product> inventory){
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.printf("%-8s %20s %20s %20s %20s %n" , "PRODUCT ID", "PRODUCT NAME", "CATEGORY" ,  "PRICE", "QUANTITY");
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println();
        for (Map.Entry<String, Product> categoryProduct : inventory.entrySet()){
            if (categoryProduct.getValue().getProductCategory().equalsIgnoreCase(categoryName)){
                System.out.printf("%-8s %20s %20s %20s %20s %n" , categoryProduct.getValue().getProductId(), categoryProduct.getValue().getProductName(), categoryProduct.getValue().getProductCategory() , categoryProduct.getValue().getPrice() , categoryProduct.getValue().getProductQty());
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------");

    }

    @Override
    public  void getAllCategory( HashMap<String , Product> inventory){
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.printf("%-8s %20s %20s %20s %20s %n" , "PRODUCT ID", "PRODUCT NAME", "CATEGORY" ,  "PRICE", "QUANTITY");
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println();
        for (Map.Entry<String, Product> categoryProduct : inventory.entrySet()){
            System.out.printf("%-8s %20s %20s %20s %20s %n" , categoryProduct.getValue().getProductId(), categoryProduct.getValue().getProductName(), categoryProduct.getValue().getProductCategory() , categoryProduct.getValue().getPrice() , categoryProduct.getValue().getProductQty());
        }
        System.out.println("----------------------------------------------------------------------------------------------");

    }
}
