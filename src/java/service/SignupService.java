/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDAO;
import pojos.User;

/**
 *
 * @author Ahmed_telnet
 */
public class SignupService {
     UserDAO userDAO;

    public SignupService() {
        userDAO = new UserDAO();
    }

    public boolean Signup(User user) {
        boolean result;
        try {
            userDAO.addUser(user);
            result=true;
        } catch (Exception e) {
            e.printStackTrace();
            result=false;
        }
        return result;
    }
    
}
