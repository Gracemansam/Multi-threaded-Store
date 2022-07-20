package services;

import interfaces.iCustomer;
import interfaces.iCustomer;
import models.Customer;
import models.Product;
import java.util.HashMap;
import java.util.Map;

public class CustomerServiceImpl implements iCustomer {


    @Override
    public boolean checkDuplicate(String productId, HashMap<String, Product> cart){
        boolean isAdded = false;
        for (Map.Entry<String , Product> productInCart : cart.entrySet()){
            if (productInCart.getValue().getProductId().equalsIgnoreCase(productId)){
                isAdded = true;
            }
        }
        return isAdded;
    }

    @Override
    public Product findProduct(String productKey, HashMap<String, Product> cart){
        Product product = null;
        for (Map.Entry<String , Product> productInCart : cart.entrySet()){
            if (productKey.equalsIgnoreCase(productInCart.getKey())){
                product = productInCart.getValue();
            }
        }
        return product;
    }

    @Override
    public String addToCart(String productToBeAdded, int quantityToAdd, HashMap<String, Product> inventory, Customer customer){
        Product product =  null;
        String message = "";
        for (Map.Entry<String , Product> productInInventory : inventory.entrySet()){
            //check if selected product is in store
            if (productToBeAdded.equalsIgnoreCase(productInInventory.getValue().getProductId())){
                //Check if the product is still in stock
                if (productInInventory.getValue().getProductQty() > 1 && productInInventory.getValue().getProductQty() >= quantityToAdd){
                    //check if product already exist in cart
                    if (checkDuplicate(productToBeAdded , customer.getCart())){
                        product = findProduct(productInInventory.getKey() , customer.getCart());
                        product.setProductQty(product.getProductQty() + quantityToAdd);
                        System.out.println(quantityToAdd + " more "+ product.getProductName() + " Has Been Added To Cart");
                        message =  "Updated";
                    }else{
                        customer.getCart().put(productInInventory.getKey() , new Product(productInInventory.getValue().getProductId(),productInInventory.getValue().getProductName() , productInInventory.getValue().getProductCategory() , quantityToAdd , productInInventory.getValue().getPrice()) );
                        productInInventory.getValue().setProductQty(productInInventory.getValue().getProductQty() - quantityToAdd);
                        System.out.println(productInInventory.getValue().getProductName() + " has been added to cart! ");
                        message = "Added";
                    }
                }else{
                    System.out.println(productInInventory.getValue().getProductName() + " is Currently Out of stock!");
                    message = "NoStock";
                }
            }
        }
        return message;
    }



    @Override
    public String buyProduct(Customer customer){
        String message = "";
        if(customer.getCart().size() > 0){
            int total = 0;
            for(Map.Entry<String, Product> customerCart : customer.getCart().entrySet()){
                total += customerCart.getValue().getPrice() * customerCart.getValue().getProductQty();
            }
            if(total < customer.getWalletBalance()){
                customer.setWalletBalance(customer.getWalletBalance() - total);
                System.out.println("Purchase Successfull");
                System.out.println(customer.getWalletBalance());
                message = "successfull";
            }else{
                System.out.println("Insufficient Funds");
                message = "insufficient";
            }
        }else{
            System.out.println("Cart Cannot Be Empty!!");
            message = "empty";
        }
        return message;
    }
}
