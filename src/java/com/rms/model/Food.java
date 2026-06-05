package com.rms.model;

/**
 * Model class representing a Food item.
 */
public class Food {

    private int foodId;
    private String foodName;
    private int foodPrice;
    private String foodCategory;
    private String foodImg;
    private String foodDesc;

    public Food() {}

    public Food(int foodId, String foodName, int foodPrice, String foodCategory, String foodImg) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodCategory = foodCategory;
        this.foodImg = foodImg;
        this.foodDesc = "";
    }

    public Food(int foodId, String foodName, int foodPrice, String foodCategory, String foodImg, String foodDesc) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodCategory = foodCategory;
        this.foodImg = foodImg;
        this.foodDesc = foodDesc;
    }

    public int getFoodId() { return foodId; }
    public void setFoodId(int foodId) { this.foodId = foodId; }

    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }

    public int getFoodPrice() { return foodPrice; }
    public void setFoodPrice(int foodPrice) { this.foodPrice = foodPrice; }

    public String getFoodCategory() { return foodCategory; }
    public void setFoodCategory(String foodCategory) { this.foodCategory = foodCategory; }

    public String getFoodImg() { return foodImg; }
    public void setFoodImg(String foodImg) { this.foodImg = foodImg; }

    public String getFoodDesc() { return foodDesc; }
    public void setFoodDesc(String foodDesc) { this.foodDesc = foodDesc; }
}
