package services;

import models.CustomerDTO;

import java.util.Queue;

public class SellWithTread implements Runnable{
    private Queue<CustomerDTO> queue;
    CashierServiceImplementation cashierServiceImplementation = new CashierServiceImplementation();

    public SellWithTread(Queue<CustomerDTO> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {



            System.out.println(Thread.currentThread().getName() + " is running");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        cashierServiceImplementation.sellBySortedProuctQuantity(queue);

            System.out.println(Thread.currentThread().getName() + " has Stopped running");
            System.out.println(" ");

        }

    }

