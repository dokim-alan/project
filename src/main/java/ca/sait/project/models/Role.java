package ca.sait.project.models;

import java.io.Serializable;

/**
 * Represents a role
 * @author Alan(Dong O) Kim
 */
public class Role implements Serializable{
    private int roleId;         // id
    private String roleName;    // name
    
    public Role() {
        
    }
    
    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    
}
