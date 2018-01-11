/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.developer.dao.Impl;

import com.developer.dao.AdminDao;
import com.developer.entity.Admin;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bladestorm
 */

@Repository("adminDao")
public class AdminImpl implements AdminDao{
    @Autowired
    SessionFactory sessionFactory;

  @Transactional
    public boolean login(String username, String password) {
    boolean check = false;
      try {
         Admin ad = (Admin) sessionFactory.getCurrentSession().createQuery("from Admin where username=:x and password=:y")
                  .setParameter("x", username)
                  .setParameter("y", password)
                  .uniqueResult();
         if(ad!=null){
             check = true;
         }
      } catch (Exception e) {
      }
    
      return check;
    }

    @Transactional
    public boolean insert(Admin admin) {
        boolean check = false;
        try {
            sessionFactory.getCurrentSession().save(admin);
            check= true;
        } catch (Exception e) {
        }
    return check;
    }
//Forgot password
      @Transactional
    public boolean forgotPassword(Admin fadmin) {
        boolean check = false;
        try {
          Admin fad=(Admin)  sessionFactory.getCurrentSession().createQuery("from Admin where username=:username")
                    .setParameter("username", fadmin.getUsername())
                    .uniqueResult();
            if(fad!=null){
                sessionFactory.getCurrentSession().createQuery("update Admin set password=:password where username=:username")
                        .setParameter("password", fadmin.getPassword())
                        .setParameter("username", fadmin.getUsername())
                        .executeUpdate();
        
                check=true;
            }
        } catch (Exception e) {
        }
        return check;
    }
    
    @Transactional
    public boolean adminUpdatePassword(Admin admin,String newpassword) {
    boolean check = false;
        try {
         Admin ad = (Admin)   sessionFactory.getCurrentSession().createQuery("from Admin where fullname=:fullname and username=:username and password=:password")
                    .setParameter("fullname", admin.getFullname())
                    .setParameter("username", admin.getUsername())
                    .setParameter("password", admin.getPassword())
                    .uniqueResult();
            if(ad!=null){
                sessionFactory.getCurrentSession().createQuery("update Admin set password=:password where username=:username")
                        .setParameter("password", newpassword)
                        .setParameter("username", admin.getUsername())
                        .executeUpdate();
                check=true;
            }
        } catch (Exception e) {
        }
        return check;
    }
    
//    @Transactional
//    public List<Admin> getAdminData(String username, String password) {
//   return sessionFactory.getCurrentSession().createQuery("from Admin where username=:username and password=:password")
//           .setParameter("username", username)
//           .setParameter("password", password)
//           .list();
//    }

    @Transactional
    public Admin getAdminDetails(String username, String password) {
    Admin adm = null;
        try {
        adm =(Admin) sessionFactory.getCurrentSession().createQuery("from Admin where username=:username and password=:password")
           .setParameter("username", username)
           .setParameter("password", password)
                    .uniqueResult();
        } catch (Exception e) {
        }
        return adm;
    }

    

  

   
    
}
