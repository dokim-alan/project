package ca.sait.project.servlets;

import ca.sait.project.models.Role;
import ca.sait.project.models.User;
import ca.sait.project.services.RoleService;
import ca.sait.project.services.UserService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Process the user data from/to MariaDB
 * @author Alan(Dong O) Kim
 */
public class UserServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService us = new UserService();
        RoleService rs = new RoleService(); //
        
        try {
            List<User> users = us.getAll();
            List<Role> roles = rs.getAll(); //
            
            request.setAttribute("users", users);
            request.setAttribute("roles", roles); //
            
            this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        request.setAttribute("message", "");

         if (action != null && action.equals("create")) {
            try {
                RoleService rs = new RoleService();
                UserService us = new UserService();

                int roleId = 0;
                String email = request.getParameter("email");
                boolean active = request.getParameter("active") != null;
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String password = request.getParameter("password");
                String roleName = request.getParameter("role");

                List<Role> roles;
                roles = rs.getAll();

                for (Role role : roles) {
                    if (role.getRoleName().equals(roleName)) {
                        roleId = role.getRoleId();
                    }
                }
                Role role = new Role(roleId, roleName);
                us.insert(email, active, firstName, lastName, password, role);
                request.setAttribute("message", "User is created successfully.");
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "User is created unsuccessfully.");
            }
        } else  if (action != null && action.equals("update")) {
            try {
                RoleService rs = new RoleService();
                UserService us = new UserService();

                int roleId = 0;

                String email = request.getParameter("email");
                boolean active = request.getParameter("active") != null;
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String password = request.getParameter("password");
                String roleName = request.getParameter("role");

                List<Role> roles;
                roles = rs.getAll();

                for (Role role : roles) {
                    if (role.getRoleName().equals(roleName)) {
                    roleId = role.getRoleId();
                    }
                }

                Role role = new Role(roleId, roleName);
                us.update(email, active, firstName, lastName, password, role);
                request.setAttribute("message", "User is updated successfuly.");

                } catch (Exception ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("message", "User is updated unsuccessfuly.");
                }
        } else  if (action != null && action.contains("edit?")) {
            try {
                String email = action.split("\\?", 2)[1];
                
                UserService us = new UserService();
                //String email = action;
                User user = us.get(email);
                request.setAttribute("user", user);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else  if (action != null && action.contains("delete?")) {
            try {
                String email = action.split("\\?", 2)[1];
                //String email = request.getParameter("email");

                UserService us = new UserService();
                us.delete(email);

                request.setAttribute("message", "User is deleted sucessfully.");

            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        try {
            UserService us = new UserService();
            RoleService rs = new RoleService();

            List<User> users = us.getAll();
            List<Role> roles = rs.getAll();

            request.setAttribute("users", users);
            request.setAttribute("roles", roles);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
}
