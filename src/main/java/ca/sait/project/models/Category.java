package ca.sait.project.models;

import java.io.Serializable;

/**
 * Represents a category
 * @author Alan(Dong O) Kim
 */
public class Category implements Serializable {
    private int categoryId;                 // category_id;
    private String categoryName;            // category_name; 
    
    public Category() {
        
    }
    
    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    
}
