/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.controller;

import com.spring.dao.UserDao;
import com.spring.entity.User;
import javax.servlet.http.HttpSession;
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
//@SessionAttributes("username")
public class MainController {
    @Autowired
    UserDao uDao;
    
    //Index Page
    @RequestMapping(value= "/",method = RequestMethod.GET)
    public String index(){
       return "index";
    }
    
    //login Form page
    @RequestMapping("/loginpage")
    public String loginPage(){
        return "login_form";
    }
    
    
    @RequestMapping("/welcome")
    public String welcom(){
        return "welcome";
    }
    
    //Login code from database
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,@RequestParam("password") String password,Model model,HttpSession session){
        if(username.isEmpty() || password.isEmpty()){
            model.addAttribute("msg", "Field is empty");
            return "redirect:/loginpage";
        }else{
            boolean check = uDao.login(username, password);
       if(check){
           session.setAttribute("username", username);
           return "redirect:/welcome";
           
       }else{
           model.addAttribute("msg", "username or password is wrong");
           return "redirect:/loginpage";
       }
        }
    }
    
    //user regist form
    @RequestMapping("/regist")
    public String userRegist(){
        return "user_regist";
    }
    
   //User regsit
    @RequestMapping(value = "/uRegist",method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user,@RequestParam("repassword") String repassword,@RequestParam("pin") String pin,Model model){
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty() || repassword.isEmpty() || pin.isEmpty()){
            model.addAttribute("msg", "Field is empty...try again");
        }else if(!user.getPassword().equals(repassword)){
            model.addAttribute("msg", "Password are not same..");
        }else if(!pin.equals("1995")){
            model.addAttribute("msg", "pin not match");
        }else{
            uDao.regists(user);
            model.addAttribute("msg", "data has been regist");
        }
        
        return "redirect:/regist";
    }
    
    
    
    //Delete Admin Record
    @RequestMapping(value = "admindelete", method = RequestMethod.GET)
    public String AdministratorDelete(@RequestParam("id") int id, Model model) {
        uDao.deleteAdmin(id);
        model.addAttribute("msg", "record has been deleted");
        return "redirect:/loginDetails";
    }

    
    //Forgot_account page
    @RequestMapping("/updatePassword")
    public String forgotPage(){
        return "forget_password";
    }
   
    //Forgot password update code
    @RequestMapping(value = "updatePass",method = RequestMethod.POST)
    public String forgotPassword(@ModelAttribute("fUser") User fUser,Model model,@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("repassword") String repassword){
        if(username.isEmpty() || password.isEmpty() || repassword.isEmpty()){
            model.addAttribute("msg", "Fields are empty");
        }
        else if(!repassword.equals(password)){
            model.addAttribute("msg","Password didn't matched");
        }
        else{
            boolean check = uDao.forgot(fUser);
        if(check){
            model.addAttribute("msg1", "Password has been updated");
        }else{
            model.addAttribute("msg", "Data not exist in Database");
        }
        }
        return "redirect:/updatePassword";
    }
    
    @RequestMapping("/loginDetails")
    public String loginList(Model model){
        model.addAttribute("ul", uDao.getAllUser());
        return "loginDetail";
    }
    
    @RequestMapping("/logout")
    public String logouts(HttpSession session){
        session.invalidate();
        
        return "index";
    }
    
    @RequestMapping("/f")
    public String form(){
        return "testForm";
    }
    
    @RequestMapping(value = "send",method = RequestMethod.POST)
    public String formDetails(Model model,@RequestParam("firstnumber") String firstnumber,@RequestParam("name") String name,int fn){
     
       
        if( name.isEmpty()){
        model.addAttribute("msg","Field is empty"); 
        return "redirect:/f";
     }else if(!firstnumber.isEmpty()){
            try {
                fn = Integer.parseInt(firstnumber);
            } catch (NumberFormatException ex) {
                model.addAttribute("msg","input number");
            }
         return "redirect:/f";
     }
     else{
            
      model.addAttribute("name", name);
        model.addAttribute("msg","data has been regist");
        return "testView";
     }
     
    }
    
}
 