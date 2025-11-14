package com.example.order.management.order.management.service;

import com.example.order.management.order.management.dto.OrderDTO;
import com.example.order.management.order.management.model.Order;
import com.example.order.management.order.management.repo.OrderRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<OrderDTO> getAllOrders() {
        return modelMapper.map(orderRepo.findAll(),new TypeToken<List<OrderDTO>>(){}.getType());
    };

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order savedOrder = orderRepo.save(modelMapper.map(orderDTO, Order.class));
        return modelMapper.map(savedOrder,OrderDTO.class);
    }

    public OrderDTO updateOrder(OrderDTO orderDTO) {
        Order updatedOrder = orderRepo.save(modelMapper.map(orderDTO, Order.class));
        return modelMapper.map(updatedOrder,OrderDTO.class);
    }

    public String deleteOrder(int id) {
        orderRepo.deleteById(id);
        return "Order deleted succesfully!";
    }
}
