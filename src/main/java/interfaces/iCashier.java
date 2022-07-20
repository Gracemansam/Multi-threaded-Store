package interfaces;

import models.Customer;
import models.Product;


import java.util.HashMap;

public interface iCashier {
    public boolean checkDuplicate(String productId, HashMap<String , Product> cart);

    public Product findProduct(String productKey, HashMap<String , Product> cart);

    public String addToCart(String productToBeAdded , int quantityToAdd,  HashMap<String , Product> inventory , Customer customer);

    public String sellProduct(Customer customer);
}
