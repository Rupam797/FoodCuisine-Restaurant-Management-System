package com.rms.model;

import java.io.Serializable;

/**
 * Model class representing a single item in the shopping cart.
 */
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private int price;
    private String image;
    private int quantity;

    public CartItem() {}

    public CartItem(int id, String name, int price, String image, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getSubtotal() { return price * quantity; }
}
