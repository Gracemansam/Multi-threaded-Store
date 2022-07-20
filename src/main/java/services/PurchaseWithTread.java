package services;

import models.Customer;
import models.Product;

import java.util.HashMap;

public class PurchaseWithTread implements Runnable{
// String productToBeAdded , int quantityToAdd, HashMap<String , Product> inventory, Customer customer

    private String productToBeAdded;
    private int quantityToAdd;
    private HashMap<String , Product> inventory;
    private Customer customer;

    public PurchaseWithTread(String productToBeAdded, int quantityToAdd, HashMap<String, Product> inventory, Customer customer) {
        this.productToBeAdded = productToBeAdded;
        this.quantityToAdd = quantityToAdd;
        this.inventory = inventory;
        this.customer = customer;
    }

    CashierServiceImplementation cashierServiceImplementation = new CashierServiceImplementation();

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " is running");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        cashierServiceImplementation.addToCart(productToBeAdded,quantityToAdd, inventory,customer);

        System.out.println(Thread.currentThread().getName() + " has Stopped running");
        System.out.println(" ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}

