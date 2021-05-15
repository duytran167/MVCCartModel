package component;

import entity.OrderDetailsEntity;
import entity.ProductEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import repository.ProductRepository;

@Component
@Scope(value = "session")
public class CartEntity {

    private int order;
    List<OrderDetailsEntity> orderDetailsList;
    ProductEntity product;
    ProductRepository productRepo;
    CartEntity cart;

    public CartEntity() {
        orderDetailsList = new ArrayList<>();
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<OrderDetailsEntity> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetailsEntity> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    //Add Item
    public void addItem(ProductEntity product) {
        boolean t = false;
        for (int i = 0; i < orderDetailsList.size(); i++) {
            if (orderDetailsList.get(i).getProduct().getId() == product.getId()) {
                OrderDetailsEntity orderDetails = orderDetailsList.get(i);
                orderDetails.setQuantity(orderDetails.getQuantity() + 1);
                orderDetailsList.set(i, orderDetails);
                t = true;
            }
        }
        if (!t) {
            OrderDetailsEntity orderDetails = new OrderDetailsEntity();
            orderDetails.setProduct(product);
            orderDetails.setQuantity(1);
            orderDetails.getProduct().getUnitPrice();
            orderDetailsList.add(orderDetails);
        }
    }

    //Remove Item
    public void removeItem(ProductEntity product) {
        for (int i = 0; i < orderDetailsList.size(); i++) {
            if (orderDetailsList.get(i).getProduct().getId() == product.getId()) {
                orderDetailsList.remove(i);
            }
        }
    }


}
