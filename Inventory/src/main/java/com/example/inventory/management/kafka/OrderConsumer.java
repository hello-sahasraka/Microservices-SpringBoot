package com.example.inventory.management.kafka;

import com.example.base.base.dto.OrderEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
     private final Logger LOGGER = LoggerFactory.getLogger(OrderEventDTO.class);

     @KafkaListener(
             topics="${spring.kafka.template.default-topic}",
             groupId = "${spring.kafka.consumer.group-id}"
     )
    public void consume(OrderEventDTO orderEventDTO) {
         LOGGER.info("Received OrderEventDTO {}", orderEventDTO);
     }
}
