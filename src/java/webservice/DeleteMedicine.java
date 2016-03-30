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
@Path("/deletemedicien")
public class DeleteMedicine {
       
       // HTTP Get Method

    @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/dodeletemedicien")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON)
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
    public String addMedicien(@QueryParam("userId") int userId,@QueryParam("remoteId") int RemoteId) {
        String response = "";
        
        if (checkCredentials(userId,RemoteId)) {
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
    private boolean checkCredentials(int userId, int remoteId) {
        System.out.println("Inside checkCredentials");
        boolean result = false;
        if (userId != 0&& remoteId!=0) {
            try {
                MedicineId medicineId = new MedicineId();
                medicineId.setUserId(userId);
                medicineId.setMedicienId(remoteId);
                MedicineService medicineService = new MedicineService();
//                Medicine medicine = new Medicine();
//                medicine.setId(medicineId);
              //System.out.println("i am alive");
                medicineService.removeMedicines(medicineId);
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