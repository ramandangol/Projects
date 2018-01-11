/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.controller;

import com.spring.dao.StudentDao;
import com.spring.entity.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    StudentDao sDao;

    @RequestMapping("/studentForm")
    public String registForm() {
        return "student_register";
    }

//    @RequestMapping(value = "/pregist", method = RequestMethod.POST)
//    public String StudentInsert(@ModelAttribute("student") Student student, Model model, String path, HttpServletRequest request) {
//        try {
//            path = request.getServletContext().getRealPath("") + "assets/images";
//            MultipartRequest mrequest = new MultipartRequest(request, path, 300000000, new FileRenamePolicy() {
//                @Override
//                public File rename(File file) {
//                    String name = String.valueOf(new Date().getTime());
//                    String ext = FilenameUtils.getExtension(file.getName());
//                    return new File(file.getParentFile(), name + "." + ext);
//                }
//            });
//            Enumeration files = mrequest.getFileNames();
//            String filename = null;
//            if (files.hasMoreElements()) {
//                filename = mrequest.getFilesystemName(files.nextElement().toString());
//            }
//            String ext = FilenameUtils.getExtension(filename);
//            String file = filename;
//            String firstname = mrequest.getParameter("firstname");
//            String lastname = mrequest.getParameter("lastname");
//            String address = mrequest.getParameter("address");
//            String gender = mrequest.getParameter("gender");
//            String facalty = mrequest.getParameter("facalty");
//            String username = mrequest.getParameter("username");
//            String password = mrequest.getParameter("password");
//            boolean status = Boolean.parseBoolean(mrequest.getParameter("status"));
//            if (status == true) {
//                status = true;
//            } else {
//                status = false;
//            }
//
//            if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("png")) {
//                student.setFirstname(firstname);
//                student.setLastname(lastname);
//                student.setAddress(address);
//                student.setGender(gender);
//                student.setFacalty(facalty);
//                student.setStatus(status);
//                student.setUsername(username);
//                student.setPassword(password);
//                student.setFile(file);
//                if (student.getFirstname().isEmpty() || student.getLastname().isEmpty()) {
//                    model.addAttribute("msg", "Fields are empty");
//                } else {
//                    sDao.insert(student);
//                    model.addAttribute("msg", "student has been regist");
//                }
//
//            } else {
//                Files.delete(new File(path + "/" + filename).toPath());
//                model.addAttribute("msg", "file format not valied");
//
//            }
//
//        } catch (Exception e) {
//        }
//
//        return "redirect:/studentForm";
//    }
    //Student Details page
    @RequestMapping("/studentDetail")
    public String stdDetails(Model model) {
        model.addAttribute("sl", sDao.getAllStudent());
        return "StudentDetails";
    }

    //student update form according to ID base
    @RequestMapping("/updateForm")
    public String stdUpdate(@RequestParam("id") int id, Model model) {
        StudentModel studModel = sDao.getStudentById(id);
        model.addAttribute("student", studModel);
        return "Student_Edit";
    }
    
    //student single details
    @RequestMapping("/stdSingleDetails")
    public String stdSDetails(@RequestParam("id") int id,Model model){
        StudentModel studMod = sDao.getStudentById(id);
        model.addAttribute("student", studMod );
        return "stdSingDetails";
    }

    //update student
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String studentUpdate(@ModelAttribute("studentModel") StudentModel studentModel, Model model) {
        boolean ch = sDao.updateStudent(studentModel);

        if (ch) {
            model.addAttribute("msg", "Record has been updated");
        } else {
            model.addAttribute("msg", "Record is not updated");
        }
        return "redirect:/studentDetail";
    }

    //Delete Student Record
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String studentDelete(@RequestParam("id") int id, Model model) {
        sDao.deleteStudent(id);
        model.addAttribute("msg", "record has been deleted");
        return "redirect:/studentDetail";
    }

    //Search student by firstname
    @RequestMapping(value = "doSearch", method = RequestMethod.POST)
    public String studentSearch(Model model, @RequestParam("search") String search) {
        model.addAttribute("sl", sDao.getSearch(search));

        return "StudentDetails";

    }

    //Student Registration Form 
    @RequestMapping(value = "stdRegist", method = RequestMethod.POST)
    public String studentRegist(Model model, @ModelAttribute("registStudent") StudentModel stdModel,
            /* 1*/ @RequestParam("firstname") String firstname,
            /* 2*/ @RequestParam("lastname") String lastname,
            /* 3*/ @RequestParam("address") String address,
            /* 4*/ @RequestParam("city") String city,
            /* 5*/ @RequestParam("state") String state,
            /* 6*/ @RequestParam("zip") String zip,
            /* 7*/ @RequestParam("gender") String gender,
            /* 8*/ @RequestParam("day") String day,
            /* 9*/ @RequestParam("month") String month,
            /* 10*/ @RequestParam("year") String year,
            /* 11*/ @RequestParam("grade") String grade,
            /* 12*/ @RequestParam("parent") String parent,
            /* 13*/ @RequestParam("phone") String phone,
            /* 14*/ @RequestParam("email") String email,
            /* 15*/ @RequestParam("username") String username,
            /* 16*/ @RequestParam("password") String password,
            /* 17*/ @RequestParam("repassword") String repassword) {

        stdModel.setFirstname(firstname);
        stdModel.setLastname(lastname);
        stdModel.setAddress(address);
        stdModel.setCity(city);
        stdModel.setState(state);
        stdModel.setZip(zip);
        stdModel.setGender(gender);
        stdModel.setDay(day);
        stdModel.setMonth(month);
        stdModel.setYear(year);
        stdModel.setGrade(grade);
        stdModel.setParent(parent);
        stdModel.setPhone(phone);
        stdModel.setEmail(email);
        stdModel.setUsername(username);
        stdModel.setPassword(password);

        //validation firstname
        if (stdModel.getFirstname().isEmpty()) {
            model.addAttribute("firstnamemsg", "Firstname is required");
        } else if (!stdModel.getFirstname().matches("[a-zA-Z_]+")) {
            model.addAttribute("firstnamemsg", "Alphabets only numbers not valid");
        } //validation Lastname
        else if (stdModel.getLastname().isEmpty()) {
            model.addAttribute("lastnamemsg", "lastname is required");
        } else if (!stdModel.getLastname().matches("[a-zA-Z_]+")) {
            model.addAttribute("lastnamemsg", "Alphabets only numbers not valid");
        } //validation address
        else if (stdModel.getAddress().isEmpty()) {
            model.addAttribute("addressmsg", "Address is required");
        } else if (stdModel.getCity().isEmpty()) {
            model.addAttribute("citymsg", "City name required");
        } else if (stdModel.getState().isEmpty()) {
            model.addAttribute("statemsg", "State required");
        }//validation zipcode 
        else if (zip.isEmpty()) {
            model.addAttribute("zipmsg", "Zip Code is empty");
        }else if(!zip.matches("[0-9_]+")){
            model.addAttribute("zipmsg", "zip code must be numbers");
        }
        else if (stdModel.getDay().isEmpty()) {
            model.addAttribute("daymsg", "Day required");
        } else if (stdModel.getMonth().isEmpty()) {
            model.addAttribute("monthmsg", "Month required");
        } else if (stdModel.getYear().isEmpty()) {
            model.addAttribute("yearmsg", "Year required");
        } else if (stdModel.getGrade().isEmpty()) {
            model.addAttribute("grademsg", "Grade is empty");
        }//validation parent name 
        else if (stdModel.getParent().isEmpty()) {
            model.addAttribute("parentmsg", "Parents name required");
        }
        //validation phone no.
        else if (stdModel.getPhone().isEmpty()) {
            model.addAttribute("phonemsg", "Phone number required");
        }else if(!stdModel.getPhone().matches("[0-9_]+")){
            model.addAttribute("phonemsg", "Phone no. must be numbers");
        } 
        else if (stdModel.getEmail().isEmpty()) {
            model.addAttribute("emailmsg", "Email Address is empty");
        } else if (stdModel.getUsername().isEmpty()) {
            model.addAttribute("usernamemsg", "Username  is empty");
        } else if (stdModel.getPassword().isEmpty()) {
            model.addAttribute("passwordmsg", "Password is empty");
        } else if (repassword.isEmpty()) {
            model.addAttribute("repasswordmsg", "Confirm password is empty");
        } else if (!stdModel.getPassword().equals(repassword)) {
            model.addAttribute("passwordvalid", "password not mached");
        } else {
            sDao.stdRegist(stdModel);
            model.addAttribute("msg", "Student Has been Register");
        }
        return "redirect:/studentForm";
    }
}
