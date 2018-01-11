/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.controller;

import com.spring.dao.ParentDao;
import com.spring.entity.StudentModel;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Bladestorm
 */
@Controller
public class ParentController {
    
    @Autowired
    ParentDao pdao;
    
    @RequestMapping("/parentlogin")
    public String parentlog(){
        return "Parents_Loginpage";
    }
    
    @RequestMapping(value = "/plogin",method = RequestMethod.POST)
    public String parLogin(@RequestParam("parentuser") String username,@RequestParam("parentpass") String password,Model model,HttpServletRequest request){
        if(username.isEmpty() || password.isEmpty()){
            model.addAttribute("msg", "fields is empty");
            return "redirect:/parentlogin";
        }else{
            boolean check = pdao.pLogin(username, password);
            if(check){
                StudentModel student = pdao.getStudentById(username);
                model.addAttribute("student", student);
                return "student_info";
            }else{
                model.addAttribute("msg", "username of password is wrong");
                return "redirect:/parentlogin";
            }
        }
        
    }
    
}
