package ca.sait.project.dataaccess;

import ca.sait.project.models.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDB {

    public List<Role> getAll() throws Exception {
        List<Role> roles = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM role";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            //rs = con.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                int roleId = rs.getInt(1);
                String roleName = rs.getString(2);
                
                Role role = new Role(roleId, roleName);
                roles.add(role);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return roles;
    }
    /*
    public String getName(int roleId) throws Exception {   
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT role_name FROM role WHERE role_id = ?";

        String roleName;
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();                      
            roleName = rs.getString(2);
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return roleName;
    }
*/
}
