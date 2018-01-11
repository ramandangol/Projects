/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.controller;

import com.spring.dao.TeacherDao;
import com.spring.entity.Teacher;
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
public class TeacherController {

    @Autowired
    TeacherDao tDao;

    @RequestMapping("/newteacher")
    public String Teacher() {
        return "teacher_register";
    }

    @RequestMapping(value = "/teachRegist", method = RequestMethod.POST)
    public String TeacherRegister(Model model, @ModelAttribute("teachers") Teacher teacher,
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("zip") String zip,
            @RequestParam("gender") String gender,
            @RequestParam("day") String day,
            @RequestParam("month") String month,
            @RequestParam("year") String year,
            @RequestParam("faculty") String faculty,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email) {

        teacher.setFirstname(firstname);
        teacher.setLastname(lastname);
        teacher.setAddress(address);
        teacher.setCity(city);
        teacher.setState(state);
        teacher.setZip(zip);
        teacher.setGender(gender);
        teacher.setDay(day);
        teacher.setMonth(month);
        teacher.setYear(year);
        teacher.setFacalty(faculty);
        teacher.setPhone(phone);
        teacher.setEmail(email);

        //firstname validation
        if (firstname.isEmpty()) {
            model.addAttribute("firstnamemsg", "Firstname required");
        } else if (!firstname.matches("[a-zA-Z_]+")) {
            model.addAttribute("firstnamemsg", "Alphabet only number not valid");
        } //lastname validation
        else if (lastname.isEmpty()) {
            model.addAttribute("lastnamemsg", "Lastname required");
        } else if (!lastname.matches("[a-zA-Z_]+")) {
            model.addAttribute("lastnamemsg", "Alphabet only number not valid");
        } //address validaiton
        else if (address.isEmpty()) {
            model.addAttribute("addressmsg", "Address required");
        } else if (!address.matches("[a-zA-Z_]+")) {
            model.addAttribute("addressmsg", "Alphabet only number not valid");
        } //City validation
        else if (city.isEmpty()) {
            model.addAttribute("citymsg", "City name required");
        } else if (!city.matches("[a-zA-Z_]+")) {
            model.addAttribute("citymsg", "Alphabet only number not valid");
        } //state validation
        else if (state.isEmpty()) {
            model.addAttribute("statemsg", "State name required");
        } else if (!state.matches("[a-zA-Z_]+")) {
            model.addAttribute("statemsg", "Alphabet only number not valid");
        } //zip code validation
        else if (zip.isEmpty()) {
            model.addAttribute("zipmsg", "Zip Code required");
        } else if (!zip.matches("[0-9_]+")) {
            model.addAttribute("zipmsg", "numbers only alphabet not valid");
        } else if (day.isEmpty()) {
            model.addAttribute("daymsg", "select Day");
        } else if (month.isEmpty()) {
            model.addAttribute("monthmsg", "select Month");
        } else if (year.isEmpty()) {
            model.addAttribute("yearmsg", "Select Year");
        } else if (faculty.isEmpty()) {
            model.addAttribute("facultymsg", "Select Faculty");
        }
        //phone no validation
        if (phone.isEmpty()) {
            model.addAttribute("phonemsg", "Phone no required");
        } else if (!phone.matches("[0-9_]+")) {
            model.addAttribute("phonemsg", "Numbers only Alphabet not valid");
        } //email validation
        else if (email.isEmpty()) {
            model.addAttribute("emailmsg", "Email address required");
        } else {
            tDao.teacherRegisters(teacher);
            model.addAttribute("msg", "Teacher has been register");
        }

        return "redirect:/newteacher";
    }

    
    @RequestMapping("/teacherdetails")
    public String teacherDetail(Model model){
        model.addAttribute("tl", tDao.getAllTeacher());
        return "teacher_detail";
    }
    
      //Delete Student Record
    @RequestMapping(value = "deleteT", method = RequestMethod.GET)
    public String teacherDelete(@RequestParam("id") int id, Model model) {
        tDao.deleteTeacher(id);
        model.addAttribute("msg", "record has been deleted");
        return "redirect:/teacherdetails";
    }
    
    
}
