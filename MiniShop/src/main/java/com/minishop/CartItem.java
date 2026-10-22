package com.minishop;

public class CartItem {

    private int id;
    private String name;
    private double price;
    private int quantity;

    public CartItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = 1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public void decreaseQuantity() {
        if (quantity > 1) {
            quantity--;
        }
    }

    public double getTotal() {
        return price * quantity;
    }
}