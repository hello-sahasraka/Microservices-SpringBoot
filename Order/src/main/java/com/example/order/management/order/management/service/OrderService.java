package com.example.order.management.order.management.service;

import com.example.inventory.management.dto.InventoryDTO;
import com.example.product.product.management.dto.ProductDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.example.order.management.order.management.common.ErrorOrderResponse;
import com.example.order.management.order.management.common.OrderResponse;
import com.example.order.management.order.management.common.SuccessOrderResponse;
import com.example.order.management.order.management.dto.OrderDTO;
import com.example.order.management.order.management.model.Order;
import com.example.order.management.order.management.repo.OrderRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@Transactional
public class OrderService {
    private final WebClient.Builder webClientBuilder;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public OrderService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public List<OrderDTO> getAllOrders() {
        return modelMapper.map(orderRepo.findAll(), new TypeToken<List<OrderDTO>>() {
        }.getType());
    };

    public OrderResponse createOrder(OrderDTO orderDTO) {
        int itemId = orderDTO.getItemId();

        try {
            InventoryDTO inventoryResponse = webClientBuilder.build()
                    .get()
                    .uri("http://inventory-management/api/v1/getinventoryitembyitemid/{itemId}", itemId)
                    .retrieve()
                    .bodyToMono(InventoryDTO.class)
                    .block();

            assert inventoryResponse != null;
            Integer productId = inventoryResponse.getProductId();

            ProductDTO productResponse = webClientBuilder.build()
                    .get()
                    .uri("http://product-management/api/v1/getproductbyproductid/{productId}", productId)
                    .retrieve()
                    .bodyToMono(ProductDTO.class)
                    .block();

            if (inventoryResponse.getQuantity() > 0) {

                if (productResponse.isForSale()) {
                    Order mappedOrder = modelMapper.map(orderDTO, Order.class);
                    java.util.Objects.requireNonNull(mappedOrder, "Mapped order is null");
                    Order savedOrder = orderRepo.save(mappedOrder);
                    return new SuccessOrderResponse(modelMapper.map(savedOrder, OrderDTO.class));
                } else {
                    return new ErrorOrderResponse("Item is not for sale!");
                }
            } else {
                return new ErrorOrderResponse("Item not available!");
            }
        } catch (WebClientResponseException e) {
            if (e.getStatusCode().is5xxServerError()) {
                return new ErrorOrderResponse("Item not found!");
            }
        }
        return null;
    }

    public OrderDTO updateOrder(OrderDTO orderDTO) {
        Order mappedOrder = modelMapper.map(orderDTO, Order.class);
        java.util.Objects.requireNonNull(mappedOrder, "Mapped order is null");
        Order updatedOrder = orderRepo.save(mappedOrder);
        return modelMapper.map(updatedOrder, OrderDTO.class);
    }

    public String deleteOrder(int id) {
        orderRepo.deleteById(id);
        return "Order deleted succesfully!";
    }
}
