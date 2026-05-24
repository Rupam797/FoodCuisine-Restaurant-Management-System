package com.rms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class representing a shopping cart.
 * Stored in the HTTP session.
 */
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem newItem) {
        for (CartItem item : items) {
            if (item.getId() == newItem.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(newItem);
    }

    public void removeItem(int foodId) {
        items.removeIf(item -> item.getId() == foodId);
    }

    public void increaseQuantity(int foodId) {
        for (CartItem item : items) {
            if (item.getId() == foodId) {
                item.setQuantity(item.getQuantity() + 1);
                break;
            }
        }
    }

    public void decreaseQuantity(int foodId) {
        for (CartItem item : items) {
            if (item.getId() == foodId && item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                break;
            }
        }
    }

    public int getTotal() {
        int total = 0;
        for (CartItem item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getItemCount() {
        return items.size();
    }
}
