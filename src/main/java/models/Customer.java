package models;
//import base.User;
import base.User;
import models.Product;

import java.util.HashMap;

public class Customer extends User {

    public Customer(String id, String name, String address, double walletBalance) {
        super(id, name, address, walletBalance);
    }

    public Customer() {
        super();
    }


    @Override
    public HashMap<String, Product> getCart() {
        return super.getCart();
    }
}
