/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import model.util.Utitlity;
import pojos.User;
import service.LoginService;
//Path: http://localhost/<appln-folder-name>/login


/**
 *
 * @author ics
 */
@Path("/login")
public class Login {
    
    // HTTP Get Method
	@GET 
	// Path: http://localhost/<appln-folder-name>/login/dologin
	@Path("/dologin")
	// Produces JSON as response
	@Produces(MediaType.APPLICATION_JSON) 
	// Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
	public String doLogin(@QueryParam("username") String uname, @QueryParam("password") String pwd){
		String response = "";
                int idCheck=checkCredentials(uname, pwd);
		if(idCheck!=0){
			response = Utitlity.constructJSON("login",idCheck);
		}else{
			response = Utitlity.constructJSON("login", 0, "Incorrect Email or Password");
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
	private int checkCredentials(String uname, String pwd){
		System.out.println("Inside checkCredentials");
		int result = 0;
                
		if(Utitlity.isNotNull(uname) && Utitlity.isNotNull(pwd)){
			try {
                            LoginService loginService=new LoginService();
                            User user=loginService.Login(uname, pwd);
				if(user!=null){
                                    result=user.getUserId();
                                }
				//System.out.println("Inside checkCredentials try "+result);
			} catch (Exception e) {
				e.printStackTrace();
				result = 0;
			}
		}else{
			//System.out.println("Inside checkCredentials else");
			result = 0;
		}
			
		return result;
	}
    
}
