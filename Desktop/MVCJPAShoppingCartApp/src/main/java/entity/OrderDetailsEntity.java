package entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderDetails")
public class OrderDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderDetailsID")
    private int id;
    @Column(name = "Quantity")
    private int quantity;
    
    //Set up relationshipss with Product n_1
    @ManyToOne
    @JoinColumn(name = "productID")
    private ProductEntity product; //(*)
    
    //Set up relationshipss with Orders n_1
    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrdersEntity orders; //(*)


    public OrderDetailsEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public OrdersEntity getOrders() {
        return orders;
    }

    public void setOrders(OrdersEntity orders) {
        this.orders = orders;
    }

     public double getTotalAmount(){
        return  getQuantity() * product.getUnitPrice();
    }

}

//form thay doi so luong ben cart.jsp, ; ben canh la lutt check