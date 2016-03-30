/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import pojos.Medicine;
import pojos.MedicineId;
/**
 *
 * @author Ahmed_telnet
 */
public class MedicineDAO {
    Session session;

    public MedicineDAO() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public Medicine getMedicine(int medicineId) {
        Medicine medicine = (Medicine) session.get(Medicine.class, medicineId);
        return medicine;
    }

    public Integer addMedicine(Medicine medicine) {
        
        session.beginTransaction();
        MedicineId id=(MedicineId) session.save(medicine);
        session.getTransaction().commit();
        
        Query query=session.createQuery("select max(med.id.medicienId) from Medicine med");
        int myid=(int)query.uniqueResult();
       //int medid=myid.getMedicienId();
       // System.out.println(medicine.getId().getUserId()+"\t"+medicine.getId().getMedicienId());
        //System.out.println(myid.getMedicienId());
        return myid;
    }
    
    public void updateMedicine(Medicine medicine) {
        System.out.println(medicine.getId().getMedicienId());
        System.out.println(medicine.getId().getUserId());
        session.beginTransaction();
        session.update(medicine);
        session.getTransaction().commit();
    }
    public void deleteMedicine(MedicineId medicineId) {
        
        session.beginTransaction();
        Medicine medicine = (Medicine) session.get(Medicine.class, medicineId);
        session.delete(medicine);
        session.getTransaction().commit();
    }
    
   
}
