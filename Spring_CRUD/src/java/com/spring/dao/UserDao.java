
package com.spring.dao;

import com.spring.entity.User;
import com.spring.utill.DbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("uDao")
public class UserDao {
    @Autowired
    DbConnection db;
    
    //Login Code
    public boolean login(String username,String password){
     boolean check=false;
        try {
            PreparedStatement pstmt = db.cn.prepareStatement("select * from user where username=? and password=?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                check = true;
            }
        } catch (Exception e) {
        }
     return check;
    }
    
    //Login account Regist
    public boolean regists(User user){
        boolean check = false;
        try {
            PreparedStatement pstmt = db.cn.prepareStatement("insert into user(username,password) values(?,?)");
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            int result = pstmt.executeUpdate();
            if(result>0){
                check=true;
            }
        } catch (Exception e) {
        }
        return check;
    }
    
    
    
    //Student delete by ID
    public void deleteAdmin(int id){
        try {
            PreparedStatement pstmt = db.cn.prepareStatement("delete from user where id=?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    //Login Forgot Password (update password)
    public boolean forgot(User fUser){
        boolean check = false;
        try {
            PreparedStatement pstmt = db.cn.prepareStatement("select * from user where username=?");
            pstmt.setString(1, fUser.getUsername());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                PreparedStatement stmt= db.cn.prepareStatement("update user set password=? where username=?");
                stmt.setString(1, fUser.getPassword());
                stmt.setString(2, fUser.getUsername());
                int result = stmt.executeUpdate();
                if(result>0){
                    check=true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }
    
   
    
    //Login List View
    public List<User> getAllUser(){
        List<User> ul = new ArrayList<>();
        try {
            Statement stmt = db.cn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user");
            while (rs.next()) {                
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                ul.add(user);
            }
        } catch (Exception e) {
        }
        return ul;
    }
    
}
