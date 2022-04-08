package ca.sait.project.services;

import ca.sait.project.dataaccess.RoleDB;
import ca.sait.project.models.Role;
import java.util.List;

public class RoleService {
    private RoleDB roleDB = new RoleDB();
    
    public List<Role> getAll() throws Exception {
        List<Role> roles = this.roleDB.getAll();
        return roles;
    }
    /*
    public String getName(int roleId) throws Exception { 
        String name = this.roleDB.getName(roleId);
        return name;
    }
*/
}
