/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utills.DbConnection;



/**
 *
 * @author Bladestorm
 */
public class AdminDao {
    DbConnection dataCon = new DbConnection();
    PreparedStatement pstmt;
    ResultSet rs;
    boolean check= false;
    
   public void createTable() {
        try {
            pstmt = dataCon.cn.prepareStatement("create table if not exists user(id int primary key auto_increment not null,username varchar(255),password varchar(255))");
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
      // Login from database
   public boolean login(String username, String password) {
        boolean check = false;
        try {
            PreparedStatement pstmt =dataCon.cn.prepareStatement("select * from user where username=? and password=?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
           if (rs.next()) {
              check = true;
            }
        } catch (Exception e) {
        }
        return check;
    }
    
    
    public boolean regist(String username,String passowrd){
        try {
            pstmt = dataCon.cn.prepareStatement("insert into user(username,password) values(?,?) ");
            pstmt.setString(1, username);
            pstmt.setString(2, passowrd);
            int result = pstmt.executeUpdate();
            if(result>0){
                check= true;
            }
        } catch (Exception e) {
        }
        return check;
    }
    
   //Delete login datas
   public void deleteData(int id){
       try {
           PreparedStatement pstmt = dataCon.cn.prepareStatement("delete from user where id=?");
           pstmt.setInt(1, id);
           pstmt.executeUpdate();
       } catch (Exception e) {
       }
   }
    
}
