/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.developer.dao;

import com.developer.entity.Admin;
import java.util.List;

/**
 *
 * @author Bladestorm
 */
public interface AdminDao {

    boolean login(String username, String password);

    boolean insert(Admin admin);

//    public List<Admin> getAdminData(String username, String password);

    Admin getAdminDetails(String username, String password);

    boolean forgotPassword(Admin admin);

    boolean adminUpdatePassword(Admin admin,String newpassword);
}
