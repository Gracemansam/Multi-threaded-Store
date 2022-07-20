package services;

import models.Customer;
import models.OrderCompare;
import models.OrderDetails;
import models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
class CashierServiceImplementationTest {

    CashierServiceImplementation cashier;
    Customer customer;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        cashier  = new CashierServiceImplementation();
        customer = new Customer("ID" , "Vincent" , "Lagos" , 7000);
    }



    @org.junit.jupiter.api.Test
    void checkDuplicate_true() {

        HashMap<String , Product> testCart = new HashMap<>();
        testCart.put("Sugar" , new Product("P244" , "Brans" , "Cookies", 10 ,1.98));
        boolean actual = cashier.checkDuplicate("p244" , testCart);
        boolean expected = true;
        assertEquals(expected , actual);
    }

    @Test
    void checkDuplicate_false() {

        HashMap<String , Product> testCart = new HashMap<>();
        testCart.put("Sugar" , new Product("P244" , "Brans" , "Cookies", 10 ,1.98));
        boolean actual = cashier.checkDuplicate("p243" , testCart);
        boolean expected = false;
        assertEquals(expected , actual);
    }

    @org.junit.jupiter.api.Test
    void findProduct_real_product() {
        HashMap<String , Product> testCart = new HashMap<>();
        Product product = new Product("P244" , "Brans" , "Cookies", 10 ,1.98);
        testCart.put("Brans" , product);

        var actual = cashier.findProduct("Brans" , testCart);
        assertEquals(product , actual);
    }

    @org.junit.jupiter.api.Test
    void findProduct_null_product() {
        HashMap<String , Product> testCart = new HashMap<>();
        Product product = new Product("P244" , "Brans" , "Cookies", 10 ,1.98);
        testCart.put("Sugar" , product);
        var actual = cashier.findProduct("Brans" , testCart);
        Product expected = null;
        assertNull(actual);
    }

    @org.junit.jupiter.api.Test
    void addToCart_Product_Not_Found() {
        HashMap<String , Product> testInventory = new HashMap<>();
        Product product = new Product("P244" , "Sugar" , "Cookies", 10 ,1.98);
        testInventory.put("Sugar" , product);

        var actual = cashier.addToCart("p243" , 20 , testInventory, customer);
        var expected = "NotFound";
        assertEquals(expected , actual);
    }

    @Test
    void addToCart_Product_Updated() {
        HashMap<String , Product> testInventory = new HashMap<>();
        Product product = new Product("P244" , "Sugar" , "Cookies", 10 ,1.98);
        testInventory.put("Sugar" , product);
        cashier.addToCart("p244" , 2 , testInventory, customer);
        var actual = cashier.addToCart("p244" , 2 , testInventory, customer);
        var expected = "Updated";
        assertEquals(expected , actual);
    }

    @Test
    void addToCart_Product_Added() {
        HashMap<String , Product> testInventory = new HashMap<>();
        Product product = new Product("P244" , "Sugar" , "Cookies", 10 ,1.98);
        testInventory.put("Sugar" , product);
        //  cashier.addToCart("p244" , 2 , testInventory, customer);
        var actual = cashier.addToCart("p244" , 2 , testInventory, customer);
        var expected = "Added";
        assertEquals(expected , actual);
    }

    @Test
    void addToCart_Product_NoStock() {
        HashMap<String , Product> testInventory = new HashMap<>();
        Product product = new Product("P244" , "Sugar" , "Cookies", 10 ,1.98);
        testInventory.put("Sugar" , product);
        //  cashier.addToCart("p244" , 2 , testInventory, customer);
        var actual = cashier.addToCart("p244" , 200 , testInventory, customer);
        var expected = "NoStock";
        assertEquals(expected , actual);
    }

    @org.junit.jupiter.api.Test
    void sellProduct_Empty_cart() {
        var actual =  cashier.sellProduct(customer);
        var expected = "empty";

        assertEquals(expected , actual);
    }

    @Test
    void sellProduct_insufficient() {
        HashMap<String , Product> testInventory = new HashMap<>();
        Product product = new Product("P244" , "Sugar" , "Cookies", 10 ,1.98);
        testInventory.put("Sugar" , product);
        Customer customerTest = new Customer("1" , "Vincent" , "Lagos" , 1);
        cashier.addToCart("p244" , 2 , testInventory, customerTest);
        var actual =  cashier.sellProduct(customerTest);
        var expected = "insufficient";

        assertEquals(expected , actual);
    }


    @Test
    void sellProduct_successfull() {
        HashMap<String , Product> testInventory = new HashMap<>();
        Product product = new Product("P244" , "Sugar" , "Cookies", 10 ,1.98);
        testInventory.put("Sugar" , product);
        Customer customerTest = new Customer("1" , "Vincent" , "Lagos" , 1000);
        cashier.addToCart("p244" , 2 , testInventory, customerTest);
        var actual =  cashier.sellProduct(customerTest);
        var expected = "successful";

        assertEquals(expected , actual);
    }

    @Test
    void sortProductsAndAddToIndividualQueue_Test_Empty_Cart(){
        //Given
        CashierServiceImplementation cashierImplTest = new CashierServiceImplementation();
        Customer customerTest = new Customer("1" , "Vincent" , "Lagos" , 500000);


        //When
        var actual = cashierImplTest.sortProductsAndAddToIndividualQueue(customerTest);
        var expected = "cartEmpty";
        assertEquals(expected , actual);

    }

    @Test
    void sortProductsAndAddToIndividualQueue_Test_Potato_Chips_Added(){
        //Given
        HashMap<String , Product> testInventory = new HashMap<>();
        Product product = new Product("P244" , "Potato Chips" , "Cookies", 10 ,1.98);
        testInventory.put("Potato Chips" , product);
        CashierServiceImplementation cashierImplTest = new CashierServiceImplementation();
        Customer customerTest = new Customer("1" , "Vincent" , "Lagos" , 500000);
        cashierImplTest.addToCart("p244", 3, testInventory, customerTest);

        //When
        var actual = cashierImplTest.sortProductsAndAddToIndividualQueue(customerTest);
        var expected = "PChipAdded";
        assertEquals(expected , actual);

    }
    @Test
    void sortProductsAndAddToIndividualQueue_Test_Pretzels_Added(){
        //Given
        HashMap<String , Product> testInventory = new HashMap<>();
        Product product = new Product("P244" , "Pretzels" , "Cookies", 10 ,1.98);
        testInventory.put("Pretzels" , product);
        CashierServiceImplementation cashierImplTest = new CashierServiceImplementation();
        Customer customerTest = new Customer("1" , "Vincent" , "Lagos" , 500000);
        cashierImplTest.addToCart("p244", 3, testInventory, customerTest);

        //When
        var actual = cashierImplTest.sortProductsAndAddToIndividualQueue(customerTest);
        var expected = "PretzelsAdded";
        assertEquals(expected , actual);

    }

    @Test
    void sortProductsAndAddToIndividualQueue_Test_WholeWheat_Added(){
        //Given
        HashMap<String , Product> testInventory = new HashMap<>();
        Product product = new Product("P244" , "Whole Wheat" , "Cookies", 10 ,1.98);
        testInventory.put("Whole Wheat" , product);
        CashierServiceImplementation cashierImplTest = new CashierServiceImplementation();
        Customer customerTest = new Customer("1" , "Vincent" , "Lagos" , 500000);
        cashierImplTest.addToCart("p244", 3, testInventory, customerTest);

        //When
        var actual = cashierImplTest.sortProductsAndAddToIndividualQueue(customerTest);
        var expected = "WWAdded";
        assertEquals(expected , actual);

    }

    @Test
    void sortProductsAndAddToIndividualQueue_Test_Banana_Added(){
        //Given
        HashMap<String , Product> testInventory = new HashMap<>();
        Product product = new Product("P244" , "Banana" , "Cookies", 10 ,1.98);
        testInventory.put("Whole Wheat" , product);
        CashierServiceImplementation cashierImplTest = new CashierServiceImplementation();
        Customer customerTest = new Customer("1" , "Vincent" , "Lagos" , 500000);
        cashierImplTest.addToCart("p244", 3, testInventory, customerTest);

        //When
        var actual = cashierImplTest.sortProductsAndAddToIndividualQueue(customerTest);
        var expected = "BananaAdded";
        assertEquals(expected , actual);

    }

    @Test
    void sortProductsAndAddToIndividualQueue_Test_Bran_Added(){
        //Given
        HashMap<String , Product> testInventory = new HashMap<>();
        Product product = new Product("P244" , "Bran" , "Cookies", 10 ,1.98);
        testInventory.put("Bran" , product);
        CashierServiceImplementation cashierImplTest = new CashierServiceImplementation();
        Customer customerTest = new Customer("1" , "Vincent" , "Lagos" , 500000);
        cashierImplTest.addToCart("p244", 3, testInventory, customerTest);

        //When
        var actual = cashierImplTest.sortProductsAndAddToIndividualQueue(customerTest);
        var expected = "BranAdded";
        assertEquals(expected , actual);

    }

    @Test
    void sortProductsAndAddToIndividualQueue_Test_Carrot_Added(){
        //Given
        HashMap<String , Product> testInventory = new HashMap<>();
        Product product = new Product("P244" , "Carrot" , "Cookies", 10 ,1.98);
        testInventory.put("Carrot" , product);
        CashierServiceImplementation cashierImplTest = new CashierServiceImplementation();
        Customer customerTest = new Customer("1" , "Vincent" , "Lagos" , 500000);
        cashierImplTest.addToCart("p244", 3, testInventory, customerTest);

        //When
        var actual = cashierImplTest.sortProductsAndAddToIndividualQueue(customerTest);
        var expected = "CarrotAdded";
        assertEquals(expected , actual);

    }

    @Test
    void sortProductsAndAddToIndividualQueue_Test_OatMeal_Added(){
        //Given
        HashMap<String , Product> testInventory = new HashMap<>();
        Product product = new Product("P244" , "Oatmeal Raisin" , "Cookies", 10 ,1.98);
        testInventory.put("Oatmeal Raisin" , product);
        CashierServiceImplementation cashierImplTest = new CashierServiceImplementation();
        Customer customerTest = new Customer("1" , "Vincent" , "Lagos" , 500000);
        cashierImplTest.addToCart("p244", 3, testInventory, customerTest);

        //When
        var actual = cashierImplTest.sortProductsAndAddToIndividualQueue(customerTest);
        var expected = "ORAdded";
        assertEquals(expected , actual);

    }
    @Test
    void sortProductsAndAddToIndividualQueue_Test_Arrowroot_Added(){
        //Given
        HashMap<String , Product> testInventory = new HashMap<>();
        Product product = new Product("P244" , "Arrowroot" , "Cookies", 10 ,1.98);
        testInventory.put("Arrowroot" , product);
        CashierServiceImplementation cashierImplTest = new CashierServiceImplementation();
        Customer customerTest = new Customer("1" , "Vincent" , "Lagos" , 500000);
        cashierImplTest.addToCart("p244", 3, testInventory, customerTest);

        //When
        var actual = cashierImplTest.sortProductsAndAddToIndividualQueue(customerTest);
        var expected = "ARAdded";
        assertEquals(expected , actual);

    }
    @Test
    void sellBySortedProuctQuantity(){
        //Given
        Product product = new Product("P244" , "Arrowroot" , "Cookies", 10 ,1.98);
        Customer customerTest = new Customer("1" , "Vincent" , "Lagos" , 500000);
        OrderDetails orderDetailsTest = new OrderDetails("Vincent" , product);
        Queue<OrderDetails> queueTest = new PriorityQueue<>(new OrderCompare());
        queueTest.add(orderDetailsTest);
        CashierServiceImplementation cashierImplTest = new CashierServiceImplementation();

        //When
        var actual = cashierImplTest.sellBySortedProuctQuantity(queueTest);
       // var expected =  true;
        assertTrue(actual);
    }


//
//    public String sortProductsAndAddToIndividualQueue(Customer customer ){
//
//        String message = "";
//        if (!customer.getCart().isEmpty()){
//            for (Map.Entry<String , Product> singleProductInCart : customer.getCart().entrySet()){
//                if(singleProductInCart.getValue().getProductName().equalsIgnoreCase("Potato Chips")){
//                    PotatoChipsQueue.add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
//                    message = "PChipAdded";
//                } else if (singleProductInCart.getValue().getProductName().equalsIgnoreCase("Pretzels")) {
//                    PretzelsQueue.add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
//                    message = "PretzelsAdded";
//                }else if (singleProductInCart.getValue().getProductName().equalsIgnoreCase("Whole Wheat")) {
//                    WholeWheatQueue.add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
//                    message = "WWAdded";
//                }else if (singleProductInCart.getValue().getProductName().equalsIgnoreCase("Banana")) {
//                    BananaQueue.add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
//                    message = "BananaAdded";
//                }else if (singleProductInCart.getValue().getProductName().equalsIgnoreCase("Bran")) {
//                    BranQueue.add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
//                    message = "BranAdded";
//                }else if (singleProductInCart.getValue().getProductName().equalsIgnoreCase("Carrot")) {
//                    CarrotQueue.add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
//                    message = "CarrotAdded";
//                }else if (singleProductInCart.getValue().getProductName().equalsIgnoreCase("Oatmeal Raisin")) {
//                    OatmealRaisinQueue.add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
//                    message = "ORAdded";
//                }else if (singleProductInCart.getValue().getProductName().equalsIgnoreCase("Arrowroot")) {
//                    ArrowRootQueue.add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
//                    message = "ARAdded";
//                }
//                //   cashier.getQueuedCustomers().add(new OrderDetails(customer.getName() , singleProductInCart.getValue()));
//            }
//        }else {
//            message = "cartEmpty";
//        }
//        return message;
//    }





}