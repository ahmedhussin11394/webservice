/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import dao.UserDAO;
import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import model.util.Utitlity;
import pojos.User;
//Path: http://localhost/<appln-folder-name>/register

/**
 *
 * @author ics
 */
@Path("/register")
public class Register {

    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/register/doregister
    @Path("/doregister")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON)
    // Query parameters are parameters: http://localhost/<appln-folder-name>/register/doregister?name=pqrs&username=abc&password=xyz
    public String doLogin(@QueryParam("fname") String fname, @QueryParam("lname") String lname, @QueryParam("email") String email, @QueryParam("phone") String phone, @QueryParam("password") String password) {
        String response = "";
        //System.out.println("Inside doLogin "+uname+"  "+pwd);
        int retCode = registerUser(fname, lname, email, phone, password);
        if (retCode == 0) {
            response = Utitlity.constructJSON("register", true);
        } else if (retCode == 1) {
            response = Utitlity.constructJSON("register", false, "You are already registered");
        } else if (retCode == 2) {
            response = Utitlity.constructJSON("register", false, "Special Characters are not allowed in Username and Password");
        } else if (retCode == 3) {
            response = Utitlity.constructJSON("register", false, "Error occured");
        }
        return response;

    }

    private int registerUser(String fname, String lname, String email, String phone, String password) {
        System.out.println("Inside checkCredentials");
        int result = 3;
        UserDAO userDAO = new UserDAO();
        if (Utitlity.isNotNull(fname) && Utitlity.isNotNull(lname) && Utitlity.isNotNull(email) && Utitlity.isNotNull(phone) && Utitlity.isNotNull(password)) {
            try {
                User user=new User(fname, lname, email, password);
                user.setPhoneNumber(phone);
                userDAO.addUser(user);
                System.out.println("RegisterUSer if");
                result = 0;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("Inside checkCredentials catch e ");
                result = 3;
            }
        } else {
            System.out.println("Inside checkCredentials else");
            result = 3;
        }

        return result;
    }

}
