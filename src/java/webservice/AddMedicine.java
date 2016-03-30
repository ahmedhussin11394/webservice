/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import dao.UserDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import model.util.Utitlity;
import pojos.Medicine;
import pojos.MedicineId;
import pojos.User;
import service.LoginService;
import service.MedicineService;

/**
 *
 * @author Ahmed_telnet
 */
@Path("/addmedicien")
public class AddMedicine {

    // HTTP Get Method

    @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/doaddmedicien")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON)
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
    public String addMedicien(@QueryParam("userId") int userId, @QueryParam("medicienName") String medicienName, @QueryParam("notification") boolean notification, @QueryParam("medicienDate") String medicienDate, @QueryParam("medicienTime") String medicienTime, @QueryParam("medicienInterval") String medicienInterval, @QueryParam("medicienAppearance") String medicienAppearance, @QueryParam("medicienDose") int medicienDose) {
        String response = "";
        
        int id=checkCredentials(userId, medicienName, notification, medicienDate, medicienTime, medicienInterval, medicienAppearance, medicienDose);
        if (id!=0) {
            response = Utitlity.constructJSON("add", id);
        } else {
            response = Utitlity.constructJSON("add", 0, "Can't add medicien");
        }
        return response;
    }

    /**
     * Method to check whether the entered credential is valid
     *
     * @param uname
     * @param pwd
     * @return
     */
    private int checkCredentials(int userId, String medicienName, boolean notification, String medicienDate, String medicienTime, String medicienInterval, String medicienAppearance, int medicienDose) {
        System.out.println("Inside checkCredentials");
        int result = 0;
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUser(userId);
        System.out.println(medicienName);
        System.out.println(medicienTime);
        
                System.out.println(medicienDate);
                System.out.println(medicienInterval);
                System.out.println(medicienAppearance);
                System.out.println(user.getFirstName());
                System.out.println();
        if (Utitlity.isNotNull(medicienName) && Utitlity.isNotNull(medicienTime) && Utitlity.isNotNull(medicienDate) && Utitlity.isNotNull(medicienInterval) && Utitlity.isNotNull(medicienAppearance) && user != null) {
            try {
                MedicineId medicineId = new MedicineId();
                medicineId.setUserId(userId);
                MedicineService medicineService = new MedicineService();
                Medicine medicine = new Medicine(medicineId, user, medicienName, notification, medicienDate, medicienTime, medicienInterval, medicienAppearance, medicienDose);
              System.out.println("i am alive");
                result =medicineService.addMedicine(medicine);
                    
                
                //System.out.println("Inside checkCredentials try "+result);
            } catch (Exception e) {
                e.printStackTrace();
                result = 0;
            }
        } else {
            System.out.println("Inside checkCredentials else");
            result = 0;
        }

        return result;
    }

}
