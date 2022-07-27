package models;

import services.*;
import utility.Inventory;
import models.Product;
import models.Cashier;
import models.Customer;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws IOException {

        Inventory inventory = new Inventory();
        CashierServiceImplementation cashierService = new CashierServiceImplementation();
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        CategoryImplementation categoryService = new CategoryImplementation();
        ManagerServiceImpl managerService = new ManagerServiceImpl();
        Customer customer = new Customer("bb" , "Sam" , "Lagos" , 5000000);
        Customer customer1 = new Customer("bb" , "Graceman" , "Lagos" , 5000000);
        Customer customer2 = new Customer("bb" , "grace" , "Lagos" , 5000000);
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



        cashierService.invoice(customer);
        cashierService.invoice(customer1);
        cashierService.invoice(customer2);

        CopyOnWriteArrayList<Thread> threadList = new CopyOnWriteArrayList<>();

        var sellWithTread = new SellWithTread(cashierService.getPotatoChipsQueue());
        var sellWithTread2 = new SellWithTread(cashierService.getBananaQueue());
        var sellWithTread3 = new SellWithTread(cashierService.getWholeWheatQueue());
        var sellWithTread4 = new SellWithTread(cashierService.getPretzelsQueue());

        Thread thread = new Thread(sellWithTread);
        var thread2 = new Thread(sellWithTread2);
        var thread3 = new Thread(sellWithTread3);
        var thread4 = new Thread(sellWithTread4);

        thread.setName("Potato Chip Queue");
        thread2.setName("Banana Queue");
        thread3.setName("Wholewheat Queue");
        thread4.setName("Pretzel Queue");

        threadList.add(thread);
        threadList.add(thread2);
        threadList.add(thread3);
        threadList.add(thread4);

        for (Thread inThread : threadList){
            inThread.start();
            try{
                inThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        cashierService.sellBySortedProuctQuantity(cashierService.getPotatoChipsQueue());
        cashierService.sellBySortedProuctQuantity(cashierService.getBananaQueue());
        cashierService.sellBySortedProuctQuantity(cashierService.getWholeWheatQueue());
        cashierService.sellBySortedProuctQuantity(cashierService.getPretzelsQueue());

        CopyOnWriteArrayList<Thread> threadList2 = new CopyOnWriteArrayList<>();

        var purchaseWithTread = new PurchaseWithTread("P224",2000,inventory.getBarsCategory(),customer);
        var purchaseWithTread1 = new PurchaseWithTread("P242",200,inventory.getBarsCategory(),customer);
        var purchaseWithTread2 = new PurchaseWithTread("P213",200,inventory.getBarsCategory(),customer);
        var purchaseWithTread3 = new PurchaseWithTread("P189",270,inventory.getBarsCategory(),customer);

        var purchase1 = new Thread(purchaseWithTread);
        var purchase2 = new Thread(purchaseWithTread1);
        var purchase3 = new Thread(purchaseWithTread2);
        var purchase4 = new Thread(purchaseWithTread3);

        purchase1.setName("Customer thread");
        purchase2.setName("Customer thread");
        purchase3.setName("Customer thread");
        purchase4.setName("Customer thread");


        threadList2.add(purchase1);
        threadList2.add(purchase2);
        threadList2.add(purchase3);
        threadList2.add(purchase4);

        for (Thread inThread2 : threadList2){
            inThread2.start();
            try{
                inThread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }





    }






}


