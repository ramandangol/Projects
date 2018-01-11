/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utills;

import java.sql.*;
import javax.swing.*;

public class DbConnection {

    public Connection cn = null;

    public DbConnection() {
        try {
//           H2 embedded database 
            Class.forName("org.h2.Driver");
            cn = DriverManager.getConnection("jdbc:h2:~/Account_database/indreni", "raman", "");

            //MySql database
//            Class.forName("com.mysql.jdbc.Driver");
//            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountarnika", "root", "");
        
//                    Class.forName("com.mysql.jdbc.Driver");
//            cn = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/sql2208423", "sql2208423", "mJ1%pE2!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e);
        }
    }

}
