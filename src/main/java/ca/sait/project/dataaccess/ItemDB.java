package ca.sait.project.dataaccess;

import ca.sait.project.models.Category;
import ca.sait.project.models.Item;
import ca.sait.project.models.Role;
import ca.sait.project.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDB {

    public List<Item> getAll() throws Exception {
        List<Item> items = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        //String sql = "SELECT * FROM user";
        //String sql = "SELECT * FROM user INNER JOIN role ON role.role_id = user.role WHERE active != 0"; // = 1;
        //String sql = "SELECT * FROM user INNER JOIN role ON role.role_id = user.role";
        //(1)email (2)active (3) firstName (4) lastName (5)password (6)role_id (7) role_name
        String sql = "SELECT * FROM item \n" +
                    "INNER JOIN category ON category.category_id = item.category \n" +
                    "INNER JOIN user ON user.email = item.owner\n" +
                    "INNER JOIN role ON role.role_id = user.role";
        // (1) itemId (2) itemName (3) price (4) categoryId (5) categoryName 
        // (6) email (7) active (8) firstName (9) lastName (10) password  (13*) role [(11) roleId (12) roleName]
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            //rs = con.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                int itemId = rs.getInt(1);               // or rs.getString("email")
                String itemName = rs.getString(2);
                double price = rs.getDouble(3);
                int categoryId = rs.getInt(4);
                String categoryName = rs.getString(5);
                String email = rs.getString(6);
                boolean active = rs.getBoolean(7);
                String firstName = rs.getString(8);
                String lastName = rs.getString(9);
                String password = rs.getString(10);
                int roleId = rs.getInt(11);
                String roleName = rs.getString(12);
                
                Role role = new Role(roleId, roleName);
                User user = new User(email, active, firstName, lastName, password, role);
                Category category = new Category(categoryId, categoryName);
                Item item = new Item(itemId, category, itemName, price, user);
                
                items.add(item);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return items;
    }

    public Item get(int itemId) throws Exception {
        Item item = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        //String sql = "SELECT * FROM user WHERE email=?";
        //String sql = "SELECT * FROM user INNER JOIN role ON role.role_id = user.role WHERE email = ? LIMIT 1";
        String sql = "SELECT * FROM item \n" +
                    "INNER JOIN category ON category.category_id = item.category \n" +
                    "INNER JOIN user ON user.email = item.owner\n" +
                    "INNER JOIN role ON role.role_id = user.role\n" +
                    "WHERE item_id = ? LIMIT 1";
        // (1) itemId (2) itemName (3) price (4) categoryId (5) categoryName 
        // (6) email (7) active (8) firstName (9) lastName (10) password  (13*) role [(11) roleId (12) roleName]
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, itemId);
            rs = ps.executeQuery();
            if (rs.next()) {
                String itemName = rs.getString(2);
                double price = rs.getDouble(3);
                int categoryId = rs.getInt(4);
                String categoryName = rs.getString(5);
                String email = rs.getString(6);
                boolean active = rs.getBoolean(7);
                String firstName = rs.getString(8);
                String lastName = rs.getString(9);
                String password = rs.getString(10);
                int roleId = rs.getInt(11);
                String roleName = rs.getString(12);
                
                Role role = new Role(roleId, roleName);
                Category category = new Category(categoryId, categoryName);
                User user = new User(email, active, firstName, lastName, password, role);
                item = new Item(itemId, category, itemName, price, user);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return item;
    }

    public boolean insert(Item item) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        //String sql = "INSERT INTO note (title, contents, owner) VALUES (?, ?, ?)";
        //String sql = "INSERT INTO user ('email', 'firstName', 'lastName', 'password', 'role') VALUES (?, ?, ?, ?, ?)";
        //String sql = "INSERT INTO user (`email`, `first_name`, `last_name`, `password`, `role`) VALUES (?, ?, ?, ?, ?)";
        String sql = "INSERT INTO item (`item_id`, `category`, `item_name`, `price`, `owner`) VALUES (?, ?, ?, ?, ?)";
        
        boolean inserted = false;
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, item.getItemId());
            ps.setInt(2, item.getCategory().getCategoryId());
            ps.setString(3, item.getItemName());
            ps.setDouble(4, item.getPrice());
            ps.setString(5, item.getOwner().getEmail());
            
            /*
            if (ps.executeUpdate() != 0) {
                inserted = true;
            } else {
                inserted = false;
            }*/
            
            //inserted = ps.executeUpdate() != 0 ? true : false;
            inserted = ps.executeUpdate() != 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return inserted;
    }

    public boolean update(Item item) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        //String sql = "UPDATE user SET title=?, contents=? WHERE note_id=?";
        //String sql = "UPDATE user SET `first_name` = ?, `last_name` = ?, `password` = ?, `role` = ? WHERE `email`=?";
        String sql = "UPDATE item SET `category` = ?, `item_name` = ?, `price` = ?, `owner` = ? WHERE `item_id`=?";
        
        boolean updated = false;
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, item.getCategory().getCategoryId());
            ps.setString(2, item.getItemName());
            ps.setDouble(3, item.getPrice());
            ps.setString(4, item.getOwner().getEmail());

            // updated = ps.executeUpdate() != 0; or
            updated = ps.executeUpdate() != 0 ? true : false;
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return updated;
    }

    public boolean delete(Item item) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        //String sql = "DELETE FROM user WHERE email = ?";                    // hard delete
        //String sql = "UPDATE user SET active = 0 WHERE email = ?";        // soft delete
        String sql = "DELETE FROM item WHERE item_id = ?";                    // hard delete
        
        boolean deleted = false;
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, item.getItemId());
            
            deleted = ps.executeUpdate() != 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return deleted;
    }

}
