package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private String customerName;

    private Product product;
    //TODO : Add a product priorityValue of integer value, assign id to the products using the products in the inventory, Create a condition checking name matches and prioritize based on Quantity and priority Value!!
    // TODO
}
