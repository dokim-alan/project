package ca.sait.project.models;

import java.io.Serializable;

/**
 * Represents a item
 * @author Alan(Dong O) Kim
 */
public class Item implements Serializable {
        private int itemId;         // item_id
        private Category category;  // category
	private String itemName;    // item_name;
	private double price;       // price
	private User owner;         // owner
        
    public Item() {
        
    }
    
    public Item(int itemId, Category category, String itemName, double price, User owner) {
        this.itemId = itemId;
        this.category = category;
        this.itemName = itemName;
        this.price = price;
        this.owner = owner;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

   
}
