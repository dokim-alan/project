package ca.sait.project.dataaccess;

import ca.sait.project.models.Category;
import ca.sait.project.models.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDB {

    public List<Category> getAll() throws Exception {
        List<Category> categories = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM category";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            //rs = con.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                int categoryId = rs.getInt(1);
                String categoryName = rs.getString(2);
                
                Category category = new Category(categoryId, categoryName);
                categories.add(category);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return categories;
    }
}
