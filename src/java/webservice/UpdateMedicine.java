/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import dao.UserDAO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import model.util.Utitlity;
import pojos.Medicine;
import pojos.MedicineId;
import pojos.User;
import service.MedicineService;

/**
 *
 * @author Ahmed_telnet
 */
@Path("/updatemedicien")
public class UpdateMedicine {
    
       // HTTP Get Method

    @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/doupdatemedicien")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON)
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
    public String addMedicien(@QueryParam("userId") int userId, @QueryParam("medicienName") String medicienName, @QueryParam("notification") boolean notification, @QueryParam("medicienDate") String medicienDate, @QueryParam("medicienTime") String medicienTime, @QueryParam("medicienInterval") String medicienInterval, @QueryParam("medicienAppearance") String medicienAppearance, @QueryParam("medicienDose") int medicienDose, @QueryParam("remoteId") int RemoteId) {
        String response = "";
        System.out.println("id: "+RemoteId);
        if (checkCredentials(userId, medicienName, notification, medicienDate, medicienTime, medicienInterval, medicienAppearance, medicienDose,RemoteId)) {
            response = Utitlity.constructJSON("login", true);
        } else {
            response = Utitlity.constructJSON("login", false, "Can't update medicien");
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
    private boolean checkCredentials(int userId, String medicienName, boolean notification, String medicienDate, String medicienTime, String medicienInterval, String medicienAppearance, int medicienDose,int remoteId) {
        System.out.println("Inside checkCredentials");
        boolean result = false;
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUser(userId);
        if (Utitlity.isNotNull(medicienName) && Utitlity.isNotNull(medicienTime) && Utitlity.isNotNull(medicienDate) && Utitlity.isNotNull(medicienInterval) && Utitlity.isNotNull(medicienAppearance) && user != null) {
            try {
                
                MedicineId medicineId = new MedicineId();
                medicineId.setUserId(userId);
                medicineId.setMedicienId(remoteId);
                MedicineService medicineService = new MedicineService();
                Medicine medicine = new Medicine(medicineId, user, medicienName, notification, medicienDate, medicienTime, medicienInterval, medicienAppearance, medicienDose);
              //System.out.println("i am alive");
                medicineService.updateMedicines(medicine);
                    result =true;
                
                //System.out.println("Inside checkCredentials try "+result);
            } catch (Exception e) {
                e.printStackTrace();
                result = false;
            }
        } else {
            //System.out.println("Inside checkCredentials else");
            result = false;
        }

        return result;
    }

    
}
