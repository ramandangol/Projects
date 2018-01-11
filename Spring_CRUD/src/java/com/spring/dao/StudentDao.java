/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.dao;

import com.spring.entity.Student;
import com.spring.entity.StudentModel;
import com.spring.utill.DbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bladestorm
 */
@Repository("sDao")
public class StudentDao {

    @Autowired
    DbConnection db;

//    public boolean insert(Student st) {
////        int status;
////        if (st.getStatus() == null) {
////            status = 0;
////        } else {
////            status = 1;
////        }
//
//        
//      
//        boolean check = false;
//        try {
//            PreparedStatement pstmt = db.cn.prepareStatement("insert into student(firstname,lastname,address,gender,facalty,status,username,password,file) values(?,?,?,?,?,?,?,?,?)");
//            pstmt.setString(1, st.getFirstname());
//            pstmt.setString(2, st.getLastname());
//            pstmt.setString(3, st.getAddress());
//            pstmt.setString(4, st.getGender());
//            pstmt.setString(5, st.getFacalty());
//            pstmt.setBoolean(6, st.getStatus());
//            pstmt.setString(7, st.getUsername());
//            pstmt.setString(8, st.getPassword());
//            pstmt.setString(9, st.getFile());
//            
//            
//            int result = pstmt.executeUpdate();
//            if (result > 0) {
//                check = true;
//            }
//        } catch (Exception e) {
//        }
//        return check;
//    }
     
    public boolean stdRegist(StudentModel stdModel){
        boolean check=false;
        try {
            PreparedStatement pstmt = db.cn.prepareStatement("insert into student_regist(firstname,lastname,address,city,state,zip,gender,day,month,year,grade,parent,phone,email,username,password) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, stdModel.getFirstname());
            pstmt.setString(2, stdModel.getLastname());
            pstmt.setString(3, stdModel.getAddress());
            pstmt.setString(4, stdModel.getCity());
            pstmt.setString(5, stdModel.getState());
            pstmt.setString(6, stdModel.getZip());
            pstmt.setString(7, stdModel.getGender());
            pstmt.setString(8, stdModel.getDay());
            pstmt.setString(9, stdModel.getMonth());
            pstmt.setString(10, stdModel.getYear());
            pstmt.setString(11, stdModel.getGrade());
            pstmt.setString(12, stdModel.getParent());
            pstmt.setString(13, stdModel.getPhone());
            pstmt.setString(14, stdModel.getEmail());
            pstmt.setString(15, stdModel.getUsername());
            pstmt.setString(16, stdModel.getPassword());
            int result = pstmt.executeUpdate();
            if(result>0){
                check=true;
            }
        } catch (Exception e) {
        }
        return check;
    }
    
//    //student Details list 
//    public List<Student> getAllStudent(){
//        HttpServletRequest request = null;
//        List<Student> sl = new ArrayList<>();
//        try {
//            PreparedStatement pstmt = db.cn.prepareStatement("select * from student");
//            ResultSet rs = pstmt.executeQuery();
//            while(rs.next()){
//                Student stu = new Student();
////                String path = request.getServletContext().getContextPath() + "/images/" +rs.getString(10);
//                stu.setId(rs.getInt("id"));
//                stu.setFirstname(rs.getString("firstname"));
//                stu.setLastname(rs.getString("lastname"));
//                stu.setAddress(rs.getString("address"));
//                stu.setGender(rs.getString("gender"));
//                stu.setFacalty(rs.getString("facalty"));
//                stu.setStatus(rs.getBoolean("status"));
//                stu.setUsername(rs.getString("username"));
//                stu.setPassword(rs.getString("password"));
////                stu.setFile(path);
//                
//                sl.add(stu);
//            }
//        } catch (Exception e) {
//        }
//        return sl;
//    }
    
    //student details
    public List<StudentModel> getAllStudent(){
       List<StudentModel> sl = new ArrayList<>();
        try {
          PreparedStatement pstmt = db.cn.prepareStatement("select * from student_regist");
          ResultSet rs = pstmt.executeQuery();
          while(rs.next()){
              StudentModel st = new StudentModel();
              st.setId(rs.getInt("id"));
              st.setFirstname(rs.getString("firstname"));
              st.setLastname(rs.getString("lastname"));
              st.setAddress(rs.getString("address"));
              st.setCity(rs.getString("city"));
              st.setState(rs.getString("state"));
              st.setZip(rs.getString("zip"));
              st.setGender(rs.getString("gender"));
              st.setDay(rs.getString("day"));
              st.setMonth(rs.getString("month"));
              st.setYear(rs.getString("year"));
              st.setGrade(rs.getString("grade"));
              st.setParent(rs.getString("parent"));
              st.setPhone(rs.getString("phone"));
              st.setEmail(rs.getString("email"));
              st.setUsername(rs.getString("username"));
              st.setPassword(rs.getString("password"));
              sl.add(st);
              
          }
        } catch (Exception e) {
        }
        return sl;
    }
    
    
    //Student update Form according to ID base....
    public StudentModel getStudentById(int id){
      
        StudentModel studModel = new StudentModel();
        try {
            PreparedStatement pstmt = db.cn.prepareStatement("select * from student_regist where id=?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                studModel.setId(rs.getInt("id"));
                 studModel.setFirstname(rs.getString("firstname"));
              studModel.setLastname(rs.getString("lastname"));
              studModel.setAddress(rs.getString("address"));
              studModel.setCity(rs.getString("city"));
              studModel.setState(rs.getString("state"));
              studModel.setZip(rs.getString("zip"));
              studModel.setGender(rs.getString("gender"));
              studModel.setDay(rs.getString("day"));
              studModel.setMonth(rs.getString("month"));
              studModel.setYear(rs.getString("year"));
              studModel.setGrade(rs.getString("grade"));
              studModel.setParent(rs.getString("parent"));
              studModel.setPhone(rs.getString("phone"));
              studModel.setEmail(rs.getString("email"));
              studModel.setUsername(rs.getString("username"));
              studModel.setPassword(rs.getString("password"));
               
            }
        } catch (Exception e) {
        }
        return studModel;
    }
    
    //Student update code
    public boolean updateStudent(StudentModel std){
        
        boolean check = false;
        try {
             PreparedStatement pstmt = db.cn.prepareStatement("update student_regist set firstname=?,lastname=?,address=?,city=?,state=?,zip=?,gender=?,day=?,month=?,year=?,grade=?,parent=?,phone=?,email=?,username=?,password=? where id=?");
             pstmt.setString(1, std.getFirstname());
            pstmt.setString(2, std.getLastname());
            pstmt.setString(3, std.getAddress());
            pstmt.setString(4, std.getCity());
            pstmt.setString(5, std.getState());
            pstmt.setString(6, std.getZip());
            pstmt.setString(7, std.getGender());
            pstmt.setString(8, std.getDay());
            pstmt.setString(9, std.getMonth());
            pstmt.setString(10, std.getYear());
            pstmt.setString(11, std.getGrade());
            pstmt.setString(12, std.getParent());
            pstmt.setString(13, std.getPhone());
            pstmt.setString(14, std.getEmail());
            pstmt.setString(15, std.getUsername());
            pstmt.setString(16, std.getPassword());
           pstmt.setInt(17, std.getId());
            
            int result = pstmt.executeUpdate();
            if(result>0){
                check=true;
            }
        } catch (Exception e) {
        }
        return check;
    }
    
    //Student delete by ID
    public void deleteStudent(int id){
        try {
            PreparedStatement pstmt = db.cn.prepareStatement("delete from student_regist where id=?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    //Student serach  by firstname
    public List<StudentModel> getSearch(String search){
        List<StudentModel> sl = new ArrayList<>();
        try {
            PreparedStatement pstmt = db.cn.prepareStatement("select * from student_regist where firstname LIKE  ?");
            pstmt.setString(1,search);
             ResultSet rs = pstmt.executeQuery();
             while(rs.next()){
                StudentModel stu = new StudentModel();
                stu.setId(rs.getInt("id"));
                stu.setFirstname(rs.getString("firstname"));
                stu.setLastname(rs.getString("lastname"));
                stu.setAddress(rs.getString("address"));
              stu.setCity(rs.getString("city"));
              stu.setState(rs.getString("state"));
              stu.setZip(rs.getString("zip"));
              stu.setGender(rs.getString("gender"));
              stu.setDay(rs.getString("day"));
              stu.setMonth(rs.getString("month"));
              stu.setYear(rs.getString("year"));
              stu.setGrade(rs.getString("grade"));
              stu.setParent(rs.getString("parent"));
              stu.setPhone(rs.getString("phone"));
              stu.setEmail(rs.getString("email"));
              stu.setUsername(rs.getString("username"));
              stu.setPassword(rs.getString("password"));
                sl.add(stu);
            }

        } catch (Exception e) {
        }
        return sl;
    }
    
    
}
