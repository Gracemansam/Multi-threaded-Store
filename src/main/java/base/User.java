package base;
import models.*;

import java.util.*;


public abstract class User{
    private String id;
    private String name;
    private String address;
    private String age;
    private double walletBalance;

    private Queue<CustomerDTO> QueuedCustomers;
    private HashMap<String , Product> cart;




    public Queue<CustomerDTO> getQueuedCustomers() {
        return QueuedCustomers;
    }

    public void setQueuedCustomers(Queue<CustomerDTO> queuedCustomers) {
        QueuedCustomers = queuedCustomers;
    }



    //Manager Constructor
    public User(String id, String name){
        this.id = id;
        this.name = name;
    }

    //Cashier Constructor
    public User(String id, String name, String age){
        this.id = id;
        this.name = name;
        this.age = age;
        this.QueuedCustomers = new PriorityQueue<>(new OrderCompare());
    }

    //Customer Constructor
    public User(String id, String name ,  String address , double walletBalance){
        this.id = id;
        this.name = name;
        this.address = address;
        this.walletBalance = walletBalance;
        this.cart = new HashMap<>();

    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age='" + age + '\'' +
                ", walletBalance=" + walletBalance +
                ", cart=" + cart +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }



    public HashMap<String, Product> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, Product> cart) {
        this.cart = cart;
    }
}
