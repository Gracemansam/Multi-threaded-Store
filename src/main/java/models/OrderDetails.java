package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;

@Data
@AllArgsConstructor
public class OrderDetails {
    private String customerName;

    private Product product;
    //TODO : Add a product priorityValue of integer value, assign id to the products using the products in the inventory, Create a condition checking name matches and prioritize based on Quantity and priority Value!!
    // TODO
}
