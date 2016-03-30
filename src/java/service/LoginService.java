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
public class LoginService {

    UserDAO userDAO;

    public LoginService() {
        userDAO = new UserDAO();
    }

    public User Login(String email, String password) {

        return userDAO.getUserbyMail(email, password);
    }
    
     public User getUserbyId(int id) {

        return userDAO.getUser(id);
    }

}
