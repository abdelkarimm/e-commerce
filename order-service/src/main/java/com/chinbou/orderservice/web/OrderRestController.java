package com.chinbou.orderservice.web;

import com.chinbou.orderservice.entities.Order;
import com.chinbou.orderservice.model.Customer;
import com.chinbou.orderservice.model.Product;
import com.chinbou.orderservice.repo.OrderRepository;
import com.chinbou.orderservice.repo.ProductItemRepository;
import com.chinbou.orderservice.service.CustomerRestClientService;
import com.chinbou.orderservice.service.InventoryRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClientService customerRestClientService;
    private InventoryRestClientService inventoryRestClientService;

    public OrderRestController(OrderRepository orderRepository, ProductItemRepository productItemRepository, CustomerRestClientService customerRestClientService, InventoryRestClientService inventoryRestClientService) {
        this.orderRepository = orderRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClientService = customerRestClientService;
        this.inventoryRestClientService = inventoryRestClientService;
    }

    @GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable  Long id){
        Order order = orderRepository.findById(id).get();
        Customer customer = customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach(productItem -> {
            Product product=inventoryRestClientService.productById(productItem.getProductId());
            productItem.setProduct(product);
        });
        return order;
    }
}
