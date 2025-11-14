package com.example.order.management.order.management.controller;

import com.example.order.management.order.management.dto.OrderDTO;
import com.example.order.management.order.management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getallorders")
    public List<OrderDTO> getOrder() {
        return orderService.getAllOrders();
    }

    @PostMapping("/createorder")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    @PutMapping("/updateorder")
    public OrderDTO updateProduct(@RequestBody OrderDTO orderDTO) {return orderService.updateOrder(orderDTO);}

    @DeleteMapping("/deleteorder/{id}")
    public String deleteOrder(@PathVariable int id) {
        return orderService.deleteOrder(id);
    }

    @GetMapping("/test")
    public Map<String, String> test() {
        return Map.of("msg", "ok");
    }

}
