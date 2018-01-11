/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.developer.controller;

import com.developer.dao.StudentDao;
import com.developer.entity.Student;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import java.io.File;
import java.nio.file.Files;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Bladestorm
 */
@Controller
public class StudentController {

    @Autowired
    StudentDao studentDao;

    @RequestMapping(value = "/stdRegist", method = RequestMethod.POST)
    public String StudentRegistration(String path, HttpServletRequest request, Model model, @ModelAttribute("student") Student student) {
        try {
            path = request.getServletContext().getRealPath("") + "/assets/studentImages";
            MultipartRequest mrequest = new MultipartRequest(request, path, 300000000, new FileRenamePolicy() {
                @Override
                public File rename(File file) {
                    String name = String.valueOf(new Date().getTime());
                    String ext = FilenameUtils.getExtension(file.getName());
                    return new File(file.getParentFile(), name + "." + ext);
                }
            });

            Enumeration files = mrequest.getFileNames();
            String filename = null;
            if (files.hasMoreElements()) {
                filename = mrequest.getFilesystemName(files.nextElement().toString());

            }
            String ext = FilenameUtils.getExtension(filename);
            String firstname = mrequest.getParameter("firstname");
            String lastname = mrequest.getParameter("lastname");
            String address = mrequest.getParameter("address");
            String city = mrequest.getParameter("city");
            String state = mrequest.getParameter("state");
            String zip = mrequest.getParameter("zip");
            String gender = mrequest.getParameter("gender");
            String day = mrequest.getParameter("day");
            String month = mrequest.getParameter("month");
            String year = mrequest.getParameter("year");
            String grade = mrequest.getParameter("grade");
            String parentname = mrequest.getParameter("parent");
            String phone = mrequest.getParameter("phone");
            String email = mrequest.getParameter("email");
            String username = mrequest.getParameter("username");
            String password = mrequest.getParameter("password");
            String repassword = mrequest.getParameter("repassword");
            String file = filename;

            //validation firstname
            if (firstname.isEmpty()) {
                model.addAttribute("firstnamemsg", "Firstname is required");
            } else if (!firstname.matches("[a-zA-Z_]+")) {
                model.addAttribute("firstnamemsg", "Alphabets only numbers not valid");
            } //validation Lastname
            else if (lastname.isEmpty()) {
                model.addAttribute("lastnamemsg", "lastname is required");
            } else if (!lastname.matches("[a-zA-Z_]+")) {
                model.addAttribute("lastnamemsg", "Alphabets only numbers not valid");
            } //validation address
            else if (address.isEmpty()) {
                model.addAttribute("addressmsg", "Address is required");
            } else if (city.isEmpty()) {
                model.addAttribute("citymsg", "City name required");
            } else if (state.isEmpty()) {
                model.addAttribute("statemsg", "State required");
            }//validation zipcode 
            else if (zip.isEmpty()) {
                model.addAttribute("zipmsg", "Zip Code is empty");
            } else if (!zip.matches("[0-9_]+")) {
                model.addAttribute("zipmsg", "zip code must be numbers");
            } else if (day.isEmpty()) {
                model.addAttribute("daymsg", "Day required");
            } else if (month.isEmpty()) {
                model.addAttribute("monthmsg", "Month required");
            } else if (year.isEmpty()) {
                model.addAttribute("yearmsg", "Year required");
            } else if (grade.isEmpty()) {
                model.addAttribute("grademsg", "Grade is empty");
            }//validation parent name 
            else if (parentname.isEmpty()) {
                model.addAttribute("parentmsg", "Parents name required");
            } //validation phone no.
            else if (phone.isEmpty()) {
                model.addAttribute("phonemsg", "Phone number required");
            } else if (!phone.matches("[0-9_]+")) {
                model.addAttribute("phonemsg", "Phone no. must be numbers");
            } else if (email.isEmpty()) {
                model.addAttribute("emailmsg", "Email Address is empty");
            } else if (username.isEmpty()) {
                model.addAttribute("usernamemsg", "Username  is empty");
            } else if (password.isEmpty()) {
                model.addAttribute("passwordmsg", "Password is empty");
            } else if (repassword.isEmpty()) {
                model.addAttribute("repasswordmsg", "Confirm password is empty");
            } else if (!password.equals(repassword)) {
                model.addAttribute("passwordvalid", "password not mached");
            }
            else if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("png")) {
                student.setFirstname(firstname);
                student.setLastname(lastname);
                student.setAddress(address);
                student.setCity(city);
                student.setState(state);
                student.setZip(zip);
                student.setGender(gender);
                student.setDay(day);
                student.setMonth(month);
                student.setYear(year);
                student.setGrade(grade);
                student.setParentname(parentname);
                student.setPhone(phone);
                student.setEmail(email);
                student.setUsername(username);
                student.setPassword(password);
                student.setImage(file);
                
                boolean check=studentDao.StudentRegister(student);
                if(check){
                model.addAttribute("msg", "Data inserted success");    
                }else{
                    model.addAttribute("msg", "Data  not inserted");
                }
                
            } else {
                Files.delete(new File(path + "/" + filename).toPath());
                model.addAttribute("msg", "File not supported");
            }

        } catch (Exception e) {
        }
        return "redirect:/dashstudent";
    }
    
     //Student 
    @RequestMapping("/dashstudent")
    public String Student(Model model) {
        
        model.addAttribute("studentlist", studentDao.getAllStudent());
        return "adminStudent";
    }
    
    //to view student details by id
     @RequestMapping("view/{id}")
    public String studentView(Model model,@PathVariable("id") int id){
        model.addAttribute("student", studentDao.getById(id));
        return "studentViewDetails";
    }
    
    //Update student edit
    @RequestMapping("/edit/{id}")
    public String studentEdit(Model model,@PathVariable("id") int id){
         model.addAttribute("studentlist", studentDao.getAllStudent());
         model.addAttribute("student", studentDao.getById(id));
        return "studentEdit";
    }
    
    //Student update
    @RequestMapping(value = "/stdUpdate",method = RequestMethod.POST)
    public String studentEditProcess(String path, HttpServletRequest request, Model model, @ModelAttribute("student") Student student,@RequestParam("firstname") String firstname){
       if(firstname.isEmpty()){
           model.addAttribute("firstnamemsg", "Firstname is Empty !!");
           return "studentEdit";
       }else{
           studentDao.StudentUpdate(student);
            return "studentEdit";
       }
       
    }

    @RequestMapping("delete/{id}")
    public String deleteStudent(@ModelAttribute("id") int id,Model model,String path,HttpServletRequest request){
        path = request.getServletContext().getRealPath("") + "/assets/studentImages";
       
        studentDao.delete(studentDao.getById(id));
        model.addAttribute("msgdelete", "Record deleted successuflly");
        return "redirect:/dashstudent";
    }
    
    
    
}
