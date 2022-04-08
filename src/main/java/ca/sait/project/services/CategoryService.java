package ca.sait.project.services;

import ca.sait.project.dataaccess.CategoryDB;
import ca.sait.project.models.Category;
import java.util.List;

public class CategoryService {
    private CategoryDB categoryDB = new CategoryDB();
    
    public List<Category> getAll() throws Exception {
        List<Category> categories = this.categoryDB.getAll();
        return categories;
    }
}
