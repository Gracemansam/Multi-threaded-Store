package services;

//import base.User;
import interfaces.iCashier;

import models.*;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public  class CashierServiceImplementation  implements iCashier {

    private Customer nextCustomerInLine;
    private Queue<OrderDetails> OatmealRaisinQueue = new PriorityQueue<>(new OrderCompare());
    private Queue<OrderDetails>   CarrotQueue = new PriorityQueue<>(new OrderCompare());
    private Queue<OrderDetails> BranQueue = new PriorityQueue<>(new OrderCompare());
    private Queue<OrderDetails> ArrowRootQueue = new PriorityQueue<>(new OrderCompare());
    private Queue<OrderDetails> PotatoChipsQueue = new PriorityQueue<>(new OrderCompare());
    private Queue<OrderDetails> PretzelsQueue = new PriorityQueue<>(new OrderCompare());
    private Queue<OrderDetails> WholeWheatQueue = new PriorityQueue<>(new OrderCompare());
    private Queue<OrderDetails> BananaQueue = new PriorityQueue<>(new OrderCompare());


    @Override
    public boolean checkDuplicate(String productId, HashMap<String , Product> cart){
        boolean isAdded = false;
        for (Map.Entry<String , Product> productInCart : cart.entrySet()){
            if (productInCart.getValue().getProductId().equalsIgnoreCase(productId)){
                isAdded = true;
            }
        }
        return isAdded;
    }

    public Product findProduct(String productKey, HashMap<String , Product> cart){
        Product product = null;
        for (Map.Entry<String , Product> productInCart : cart.entrySet()){
            if (productKey.equalsIgnoreCase(productInCart.getKey())){
                product = productInCart.getValue();
            }
        }
        return product;
    }

    @Override
    public String addToCart(String productToBeAdded , int quantityToAdd, HashMap<String , Product> inventory, Customer customer){
        Product product =  null;
        String message = "";
        for (Map.Entry<String , Product> productInInventory : inventory.entrySet()){
            //check if selected product is in store
            if (productToBeAdded.equalsIgnoreCase(productInInventory.getValue().getProductId())){
                //Check if the product is still in stock
                if (productInInventory.getValue().getProductQty() > 1 && productInInventory.getValue().getProductQty() > quantityToAdd){
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
            }else{
                message = "NotFound";
            }
        }
        return message;
    }

    //Method to add customer to Queue provided Cart is not empty!.









    @Override
     public String    sellProduct(Customer customer){
        String message = "";
        if(customer.getCart().size() > 0){
            int total = 0;
            for(Map.Entry<String, Product> customerCart : customer.getCart().entrySet()){
                total += customerCart.getValue().getPrice() * customerCart.getValue().getProductQty();
            }
            if(total < customer.getWalletBalance()){
                customer.setWalletBalance(customer.getWalletBalance() - total);
                System.out.println("Purchase Successful");
                invoice(customer);
               // System.out.println(customer.getWalletBalance());
                message = "successful";
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





    public String sortProductsAndAddToIndividualQueue(Customer customer ){

        String message = "";
        if (!customer.getCart().isEmpty()){
            for (Map.Entry<String , Product> singleProductInCart : customer.getCart().entrySet()){
                if(singleProductInCart.getValue().getProductName().equalsIgnoreCase("Potato Chips")){
                    PotatoChipsQueue.add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
                    message = "PChipAdded";
                } else if (singleProductInCart.getValue().getProductName().equalsIgnoreCase("Pretzels")) {
                    PretzelsQueue.add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
                    message = "PretzelsAdded";
                }else if (singleProductInCart.getValue().getProductName().equalsIgnoreCase("Whole Wheat")) {
                    WholeWheatQueue.add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
                    message = "WWAdded";
                }else if (singleProductInCart.getValue().getProductName().equalsIgnoreCase("Banana")) {
                    BananaQueue.add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
                    message = "BananaAdded";
                }else if (singleProductInCart.getValue().getProductName().equalsIgnoreCase("Bran")) {
                    BranQueue.add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
                    message = "BranAdded";
                }else if (singleProductInCart.getValue().getProductName().equalsIgnoreCase("Carrot")) {
                    CarrotQueue.add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
                    message = "CarrotAdded";
                }else if (singleProductInCart.getValue().getProductName().equalsIgnoreCase("Oatmeal Raisin")) {
                    OatmealRaisinQueue.add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
                    message = "ORAdded";
                }else if (singleProductInCart.getValue().getProductName().equalsIgnoreCase("Arrowroot")) {
                    ArrowRootQueue.add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
                    message = "ARAdded";
                }
             //   cashier.getQueuedCustomers().add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
            }
        }else {
            message = "cartEmpty";
        }
        return message;
    }

    public boolean sellBySortedProuctQuantity(Queue<OrderDetails> queue){
       // assert queue.poll() != null;
        while (!queue.isEmpty()){
            System.out.println( queue.poll());
        }
        return true;
    }
    public boolean sellByPriority(Queue<Customer> queue){
        while(!queue.isEmpty()){
            sellProduct(queue.poll());
        }
        return true;
    }


    public void invoice(Customer customer){
        System.out.printf("%40s" , "INVOICE");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("%-8s %20s %20s %20s %n" , "PRODUCT ID", "PRODUCTS","QUANTITY" ,"TOTAL PRICE");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println();
//        var c = this.cart;
        int total = 0;
        for (Map.Entry<String, Product> productPurchased : customer.getCart().entrySet()){
            System.out.printf("%-8s %20s %20s %20f %n" , productPurchased.getValue().getProductId(), productPurchased.getValue().getProductName(), productPurchased.getValue().getProductQty() , (productPurchased.getValue().getPrice() * productPurchased.getValue().getProductQty()));
            total += productPurchased.getValue().getPrice() * productPurchased.getValue().getProductQty();
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Total Paid: " + total +  " Wallet Balance: " + (customer.getWalletBalance() - total));
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Customer Details " + "Name: " + customer.getName() + "  Address: " + customer.getAddress());
        System.out.println("--------------------------------------------------------------------------");
    }

    public Queue<OrderDetails> getOatmealRaisinQueue() {
        return OatmealRaisinQueue;
    }

    public void setOatmealRaisinQueue(Queue<OrderDetails> oatmealRaisinQueue) {
        OatmealRaisinQueue = oatmealRaisinQueue;
    }

    public Queue<OrderDetails> getCarrotQueue() {
        return CarrotQueue;
    }

    public void setCarrotQueue(Queue<OrderDetails> carrotQueue) {
        CarrotQueue = carrotQueue;
    }

    public Queue<OrderDetails> getBranQueue() {
        return BranQueue;
    }

    public void setBranQueue(Queue<OrderDetails> branQueue) {
        BranQueue = branQueue;
    }

    public Queue<OrderDetails> getArrowRootQueue() {
        return ArrowRootQueue;
    }

    public void setArrowRootQueue(Queue<OrderDetails> arrowRootQueue) {
        ArrowRootQueue = arrowRootQueue;
    }

    public Queue<OrderDetails> getPotatoChipsQueue() {
        return PotatoChipsQueue;
    }

    public void setPotatoChipsQueue(Queue<OrderDetails> potatoChipsQueue) {
        PotatoChipsQueue = potatoChipsQueue;
    }

    public Queue<OrderDetails> getPretzelsQueue() {
        return PretzelsQueue;
    }

    public void setPretzelsQueue(Queue<OrderDetails> pretzelsQueue) {
        PretzelsQueue = pretzelsQueue;
    }

    public Queue<OrderDetails> getWholeWheatQueue() {
        return WholeWheatQueue;
    }

    public void setWholeWheatQueue(Queue<OrderDetails> wholeWheatQueue) {
        WholeWheatQueue = wholeWheatQueue;
    }

    public Queue<OrderDetails> getBananaQueue() {
        return BananaQueue;
    }

    public void setBananaQueue(Queue<OrderDetails> bananaQueue) {
        BananaQueue = bananaQueue;
    }
}
