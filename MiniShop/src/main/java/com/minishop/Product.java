package com.minishop;

public class Product {

    private int id;
    private String name;
    private double price;
    private String description;
    private String emoji;

    public Product() {
    }

    public Product(
            int id,
            String name,
            double price,
            String description,
            String emoji) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.emoji = emoji;
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

    public String getDescription() {
        return description;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }
}