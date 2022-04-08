package ca.sait.project.services;

import ca.sait.project.dataaccess.ItemDB;
import ca.sait.project.models.Category;
import ca.sait.project.models.Item;
import ca.sait.project.models.User;
import java.util.List;

public class ItemService {
    private ItemDB itemDB = new ItemDB();
    
    public Item get(int itemId) throws Exception {
        Item item = this.itemDB.get(itemId);
        return item;
    }
    
    public List<Item> getAll() throws Exception {
        List<Item> items = this.itemDB.getAll();
        return items;
    }
    
    public boolean insert(int itemId, Category category, String itemName, double price, User owner) throws Exception {
        Item item = new Item(itemId, category, itemName, price, owner);
        return this.itemDB.insert(item);
    }
    
    public boolean update(int itemId, Category category, String itemName, double price, User owner) throws Exception {
        Item item = new Item(itemId, category, itemName, price, owner);
        return this.itemDB.update(item);
    }
    
    public boolean delete(int itemId) throws Exception {
        Item item = new Item();
        item.setItemId(itemId);
        return this.itemDB.delete(item);
    }
}
