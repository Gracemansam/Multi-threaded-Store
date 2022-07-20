package models;

import java.util.Comparator;

public class OrderCompare  implements Comparator<OrderDetails> {

    @Override
    public int compare(OrderDetails o1, OrderDetails o2) {
       // if (o1.getProduct().getProductName().equalsIgnoreCase(o2.getProduct().getProductName())){
            return o1.getProduct().getProductQty() < o2.getProduct().getProductQty() ? 1 : -1;// && priority value is less than
      //  }

       //return 0;
    }
}
