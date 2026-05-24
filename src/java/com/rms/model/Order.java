package com.rms.model;

/**
 * Model class representing a food Order.
 */
public class Order {

    private int orderId;
    private String customerName;
    private String phoneNo;
    private String foodDetails;
    private int totalAmount;
    private String paymentStatus;
    private String orderDate;

    public Order() {}

    public Order(String customerName, String phoneNo, String foodDetails,
                 int totalAmount, String paymentStatus) {
        this.customerName = customerName;
        this.phoneNo = phoneNo;
        this.foodDetails = foodDetails;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    public String getFoodDetails() { return foodDetails; }
    public void setFoodDetails(String foodDetails) { this.foodDetails = foodDetails; }

    public int getTotalAmount() { return totalAmount; }
    public void setTotalAmount(int totalAmount) { this.totalAmount = totalAmount; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }
}
