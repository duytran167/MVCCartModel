package controller;

import entity.OrdersEntity;
import entity.ProductEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import repository.OrderDetailsRepository;
import repository.OrdersRepository;
import repository.ProductRepository;

@Controller
public class ProductController {
    @Autowired
    OrderDetailsRepository orderDetailsRepo;
    
    @Autowired
    OrdersRepository ordersRepo;
    
    @Autowired
    ProductRepository productRepo;
    
    //List All Book
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String ListAllOrder(Model model) {
        List<ProductEntity> product = (List<ProductEntity>) productRepo.findAll();
        
        model.addAttribute("product", product);
        
        return "ProductList"; //return ProductList.jsp
    }
    
}
