package controller;

import component.CartEntity;
import entity.OrderDetailsEntity;
import entity.OrdersEntity;
import entity.ProductEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import repository.OrderDetailsRepository;
import repository.OrdersRepository;
import repository.ProductRepository;

@Controller
@Scope(value =  "session")
public class CartController {
    
    @Autowired
    ProductRepository productRepo;
    
    @Autowired
    OrderDetailsRepository orderDetailsRepo;
    
    
    @Autowired
    OrdersRepository ordersRepo;
    
    @Autowired
    CartEntity cart;
        
    //Add to cart
    @RequestMapping(value = "/addToCart/{id}", method = RequestMethod.GET)
    public String addToCarts(@PathVariable(value = "id") int id, Model model){
        ProductEntity product =(ProductEntity) productRepo.findById(id);
        cart.addItem(product);
        
        model.addAttribute("cart", cart);
        
        return "Cart"; //Return Cart.jsp
    }
    
    //Remove product
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeItem(@PathVariable(value = "id") int id, Model model) {
        //Get Item
        ProductEntity product =(ProductEntity) productRepo.findById(id);
        
        //Remove Item
        cart.removeItem(product);
        
        //List lai trang Cart
        model.addAttribute("cart", cart);
        return "Cart"; 
        
    }
    
    //Update quantity
    
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String updateQuantity(Model model,@RequestParam(name= "id")int id,
            @RequestParam(name= "quantity")int quantity, ProductEntity product){
       List<OrderDetailsEntity> orderDetailsList = cart.getOrderDetailsList();
       for(int i= 0; i<orderDetailsList.size();i++){
          if(orderDetailsList.get(i).getProduct().getId() == product.getId()){
              OrderDetailsEntity orderDetail = orderDetailsList.get(i);
              orderDetail.setQuantity(quantity);
              orderDetailsList.set(i, orderDetail);
              cart.setOrderDetailsList(orderDetailsList);
          }
        }
       model.addAttribute("cart", cart);
        return "Cart";
    }
    
    //Checkout
    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String checkout(Model model){
        
        model.addAttribute("cart", cart);
        
        model.addAttribute("orders", new OrdersEntity()); //model.addAttribute ben modelAttribute ben checkout.jsp
        model.addAttribute("orderDetails", new OrderDetailsEntity());
        return "Checkout"; //Return Checkout.jsp
    }
    //Save checkout
    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST) //update ben action ben editBook.jsp
    public String saveCheckout(OrdersEntity orders,  Model model) {
        //Save vao bang Orders
        orders.setOrderDate(LocalDate.now());       
        OrdersEntity newOrder = ordersRepo.save(orders);
        
        //Save vao bang Order Details
        List<OrderDetailsEntity> orderDetailsList = cart.getOrderDetailsList();
        for(OrderDetailsEntity orderDetails : orderDetailsList){
            orderDetails.setOrders(newOrder);
            orderDetailsRepo.save(orderDetails);
        } 
        return "redirect:/"; //goi lai home.jsp
    }

    
}
