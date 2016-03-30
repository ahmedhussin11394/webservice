/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.MedicineDAO;
import java.util.ArrayList;
import pojos.Medicine;
import pojos.MedicineId;

/**
 *
 * @author Ahmed_telnet
 */
public class MedicineService {

    MedicineDAO medicineDAO;

    public MedicineService() {
        medicineDAO = new MedicineDAO();
    }

    public int addMedicine(Medicine medicine) {
        return medicineDAO.addMedicine(medicine);
    }

    public void removeMedicines(MedicineId medicineId) {
            medicineDAO.deleteMedicine(medicineId);
    }
    
       public void updateMedicines(Medicine medicine) {
            medicineDAO.updateMedicine(medicine);
    }
}
