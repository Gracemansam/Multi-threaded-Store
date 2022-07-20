package models;

import services.CashierServiceImplementation;
import services.CategoryImplementation;
import services.CustomerServiceImpl;
import services.ManagerServiceImpl;
import utility.Inventory;
import models.Product;
import models.Cashier;
import models.Customer;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws IOException {

        Inventory inventory = new Inventory();
        CashierServiceImplementation cashierService = new CashierServiceImplementation();
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        CategoryImplementation categoryService = new CategoryImplementation();
        ManagerServiceImpl managerService = new ManagerServiceImpl();
        Customer customer = new Customer("bb" , "Vincent" , "Lagos" , 5000000);
        Customer customer1 = new Customer("bb" , "Enwere" , "Lagos" , 5000000);
        Customer customer2 = new Customer("bb" , "Charles" , "Lagos" , 5000000);
        Cashier cashier = new Cashier();
        Cashier cashier1 = new Cashier("1" , "Cashier" , "25");


        cashierService.addToCart("P224" , 2000, inventory.getBarsCategory(), customer);
        cashierService.addToCart("P242" , 200, inventory.getBarsCategory(), customer);
        cashierService.addToCart("P213" , 20, inventory.getBarsCategory(), customer);
        cashierService.addToCart("P189" , 270, inventory.getBarsCategory(), customer);
        cashierService.addToCart("P224" , 290, inventory.getBarsCategory(), customer1);
        cashierService.addToCart("P242" , 200, inventory.getBarsCategory(), customer1);
        cashierService.addToCart("P189" , 200, inventory.getBarsCategory(), customer1);
        cashierService.addToCart("P224" , 200, inventory.getBarsCategory(), customer2);
        cashierService.addToCart("P242" , 200, inventory.getBarsCategory(), customer2);
        cashierService.addToCart("P189" , 401, inventory.getBarsCategory(), customer2);



        cashierService.sortProductsAndAddToIndividualQueue(customer);
        cashierService.sortProductsAndAddToIndividualQueue(customer2);
        cashierService.sortProductsAndAddToIndividualQueue(customer1);

        cashierService.sellBySortedProuctQuantity(cashierService.getPotatoChipsQueue());
        cashierService.sellBySortedProuctQuantity(cashierService.getBananaQueue());
        cashierService.sellBySortedProuctQuantity(cashierService.getWholeWheatQueue());
        cashierService.sellBySortedProuctQuantity(cashierService.getPretzelsQueue());

        cashierService.invoice(customer);
        cashierService.invoice(customer1);
        cashierService.invoice(customer2);

    }






}


