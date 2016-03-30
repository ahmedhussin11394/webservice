/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import pojos.Medicine;
import pojos.User;

/**
 *
 * @author Ahmed_telnet
 */
public class UserDAO {

    Session session;

    public UserDAO() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public User getUser(int userId) {
        User user = (User) session.get(User.class, userId);
        return user;
    }

    public void addUser(User user) {
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    public void updateUser(User user) {
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
    }

    public void deleteUser(User user) {
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
    }

    public User getUserbyMail(String email, String password) {
        Query query = session.createQuery("from User u where u.email=:email and u.password=:password");
        query.setString("email", email);
        query.setString("password", password);
        return (User)query.uniqueResult();
    }
    
     public ArrayList<Medicine> getMedicines(User user) {
        User user1 = (User) session.get(User.class, user.getUserId());
         session.beginTransaction();
         session.persist(user1);
         session.getTransaction().commit();
        return new ArrayList<>(user1.getMedicines());
    }
}
