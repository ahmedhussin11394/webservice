/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import pojos.Medicine;

/**
 *
 * @author Ahmed_telnet
 */
public class Utitlity {
    
    /**
	 * Null check Method
	 * 
	 * @param txt
	 * @return
	 */
	public static boolean isNotNull(String txt) {
		// System.out.println("Inside isNotNull");
		return txt != null && txt.trim().length() >= 0 ? true : false;
	}

	/**
	 * Method to construct JSON
	 * 
	 * @param tag
	 * @param status
	 * @return
	 */
	public static String constructJSON(String tag, boolean status) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString();
	}
        
        
        public static String constructJSON(String tag, int remoteId) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("remoteId", new Integer(remoteId));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString();
	}

	/**
	 * Method to construct JSON with Error Msg
	 * 
	 * @param tag
	 * @param status
	 * @param err_msg
	 * @return
	 */
	public static String constructJSON(String tag, boolean status,String err_msg) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
			obj.put("error_msg", err_msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString(); 
	}
        
        public static String constructJSON(String tag, int status,String err_msg) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("remoteId", new Integer(status));
			obj.put("error_msg", err_msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString(); 
	}
        
        public static String constructJSON(ArrayList<Medicine> list) {
            System.out.println(list);
		JSONObject obj = new JSONObject();
                JSONArray listMedicien = new JSONArray();
                   for(int i=0;i<list.size();i++)
                   {
                        JSONArray medicienItems = new JSONArray();
                        medicienItems.put(list.get(i).getMedicienName());
                        medicienItems.put(list.get(i).getMedicienDate());
                        medicienItems.put(list.get(i).getMedicienTime());
                        medicienItems.put(list.get(i).isNotification());
                        medicienItems.put(list.get(i).getMedicienInterval());
                        medicienItems.put(list.get(i).getMedicienAppearance());
                        medicienItems.put(list.get(i).getMedicienDose());
                         medicienItems.put(list.get(i).getId().getMedicienId());
                        listMedicien.put(medicienItems);
                   }
            try {
                obj.put("mediciens", listMedicien);
            } catch (JSONException ex) {
                Logger.getLogger(Utitlity.class.getName()).log(Level.SEVERE, null, ex);
            }
                System.out.println(obj.toString());
		return obj.toString(); 
	}
    
}
