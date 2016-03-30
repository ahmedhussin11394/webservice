/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDAO;
import java.util.ArrayList;
import pojos.Medicine;
import pojos.User;

/**
 *
 * @author Ahmed_telnet
 */
public class RetriveMedicineService {
    UserDAO userDAO;
     public RetriveMedicineService() {
        userDAO = new UserDAO();
    }
  public ArrayList<Medicine> getAllMedicines(User user)
     {
         return userDAO.getMedicines(user);
     }
}
