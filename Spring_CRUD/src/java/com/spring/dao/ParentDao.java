/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.dao;

import com.spring.entity.Student;
import com.spring.entity.StudentModel;
import com.spring.utill.DbConnection;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bladestorm
 */

@Repository("pDao")
public class ParentDao {
    
    @Autowired
    DbConnection pdb;
    
    //Parents login
    public boolean pLogin(String username,String password){
        boolean check=false;
        try {
            PreparedStatement pstmt = pdb.cn.prepareStatement("select * from student_regist where username=? and password =?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                check= true;
            }
        } catch (Exception e) {
        }
        return check;
    }
    
    public StudentModel getStudentById(String username){
        HttpServletRequest request = null;
    StudentModel student = new StudentModel();
        try {
           PreparedStatement pstmt = pdb.cn.prepareStatement("Select * from student_regist where username=?");
           pstmt.setString(1, username);
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()){

               student.setId(rs.getInt("id"));
               student.setFirstname(rs.getString("firstname"));
               student.setLastname(rs.getString("lastname"));
               student.setAddress(rs.getString("address"));
               student.setCity(rs.getString("city"));
               student.setState(rs.getString("state"));
               student.setZip(rs.getString("zip"));
               student.setGender(rs.getString("gender"));
               student.setDay(rs.getString("day"));
               student.setMonth(rs.getString("month"));
               student.setYear(rs.getString("year"));
               student.setGrade(rs.getString("grade"));
               student.setParent(rs.getString("parent"));
               student.setPhone(rs.getString("phone"));
               student.setEmail(rs.getString("email"));
               student.setUsername(rs.getString("username"));
               student.setPassword(rs.getString("password"));
               
              
           }
        } catch (Exception e) {
        }
        return student;
    }
    
}
