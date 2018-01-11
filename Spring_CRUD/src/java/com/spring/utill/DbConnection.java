/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.utill;

import java.sql.*;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bladestorm
 */
@Repository("db")
public class DbConnection {

    public Connection cn = null;

    public DbConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring_crud", "root", "");

//            Class.forName("com.mysql.jdbc.Driver");
//            cn = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/sql2208423", "sql2208423", "mJ1%pE2!");

        } catch (Exception e) {
        }
    }
}
