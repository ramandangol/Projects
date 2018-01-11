/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.dao;

import com.spring.entity.Teacher;
import com.spring.utill.DbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bladestorm
 */
@Repository("tDao")
public class TeacherDao {
    
    @Autowired
    DbConnection db;
    
    public boolean teacherRegisters(Teacher tch){
        boolean check= false;
        try {
            PreparedStatement pstmt = db.cn.prepareStatement("insert into teacher_regist(firstname,lastname,address,city,state,zip,gender,day,month,year,faculty,phone,email) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, tch.getFirstname());
            pstmt.setString(2, tch.getLastname());
            pstmt.setString(3, tch.getAddress());
            pstmt.setString(4, tch.getCity());
            pstmt.setString(5, tch.getState());
            pstmt.setString(6, tch.getZip());
            pstmt.setString(7, tch.getGender());
            pstmt.setString(8, tch.getDay());
            pstmt.setString(9, tch.getMonth());
            pstmt.setString(10, tch.getYear());
            pstmt.setString(11, tch.getFaculty());
            pstmt.setString(12, tch.getPhone());
            pstmt.setString(13, tch.getEmail());
            int result = pstmt.executeUpdate();
            if(result>0){
                check=true;
            }
            
            
        } catch (Exception e) {
        }
        return check;
    }
    
    
   public List<Teacher> getAllTeacher(){
       List<Teacher> tl = new ArrayList<>();
        try {
          PreparedStatement pstmt = db.cn.prepareStatement("select * from teacher_regist");
          ResultSet rs = pstmt.executeQuery();
          while(rs.next()){
              Teacher tche = new Teacher();
              tche.setId(rs.getInt("id"));
              tche.setFirstname(rs.getString("firstname"));
              tche.setLastname(rs.getString("lastname"));
              tche.setAddress(rs.getString("address"));
              tche.setCity(rs.getString("city"));
              tche.setState(rs.getString("state"));
              tche.setZip(rs.getString("zip"));
              tche.setGender(rs.getString("gender"));
              tche.setDay(rs.getString("day"));
              tche.setMonth(rs.getString("month"));
              tche.setYear(rs.getString("year"));
              tche.setFacalty(rs.getString("faculty"));
              tche.setPhone(rs.getString("phone"));
              tche.setEmail(rs.getString("email"));
              tl.add(tche);
              
          }
        } catch (Exception e) {
        }
        return tl;
    }
   
   public void deleteTeacher(int id){
       try {
            PreparedStatement pstmt = db.cn.prepareStatement("delete from teacher_regist where id=?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
        }
   }
    
}
