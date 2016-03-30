/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import model.util.Utitlity;
import pojos.User;
import service.LoginService;
import service.RetriveMedicineService;

/**
 *
 * @author Ahmed_telnet
 */
@Path("/getmedicien")
public class RetrieveMedicine {
    // HTTP Get Method
	@GET 
	// Path: http://localhost/<appln-folder-name>/login/dologin
	@Path("/dogetmedicien")
	// Produces JSON as response
	@Produces(MediaType.APPLICATION_JSON) 
	// Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
	public String doLogin(@QueryParam("userId") String id){
		String response = "";
                ArrayList list=checkCredentials(id);
		if(list!=null){
			response = Utitlity.constructJSON(list);
		}else{
			response = Utitlity.constructJSON("login", false, "Incorrect Email or Password");
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
	private ArrayList checkCredentials(String id){
		System.out.println("Inside checkCredentials");
		boolean result = false;
                 ArrayList medicineList=null;
		if(Utitlity.isNotNull(id)){
			try {
                            int userId=Integer.parseInt(id);
                            LoginService loginService=new LoginService();
                            User user=loginService.getUserbyId(userId);
                            RetriveMedicineService retriveMedicineService=new RetriveMedicineService();
                            medicineList=retriveMedicineService.getAllMedicines(user);
                            System.out.println(medicineList);
				if(medicineList!=null&&medicineList.size()>0){
                                   return medicineList;
                                }
				//System.out.println("Inside checkCredentials try "+result);
			} catch (Exception e) {
				e.printStackTrace();
				medicineList=null;
			}
		}else{
			//System.out.println("Inside checkCredentials else");
			medicineList=null;
		}
			
		return medicineList;
	}
}
