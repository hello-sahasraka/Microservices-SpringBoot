package com.example.order.management.order.management.dto;

import java.time.LocalDate;

public class OrderDTO {
    private int id;
    private int itemId;
    private int amount;
    private LocalDate orderDate;

    public OrderDTO() {
    }

    public OrderDTO(int id, int itemId, int amount, LocalDate orderDate) {
        this.id = id;
        this.itemId = itemId;
        this.amount = amount;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
