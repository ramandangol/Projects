/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.developer.controller;

import com.developer.dao.AdminDao;
import com.developer.entity.Admin;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import java.io.File;
import java.nio.file.Files;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
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
public class MainController {

    @Autowired
    AdminDao adminDao;

    //Home page Index page
    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }

    //admin login Form page
    @RequestMapping("/adminloginpage")
    public String adminLogin() {
        return "adminLoginForm";
    }

//logout 
    @RequestMapping("/logout")
    public String DashLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    //Dashboard
    @RequestMapping("/dashboard")
    public String Dashboard() {
        return "adminDash";
    }

   

    //Login code from database
    @RequestMapping(value = "/dashboard", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session, HttpServletRequest request) {
        if (username.isEmpty()) {
            model.addAttribute("msg", "username is Empty");
            return "redirect:/adminloginpage";
        } else if (password.isEmpty()) {
            model.addAttribute("msg", "password is Empty !!");
            return "redirect:/adminloginpage";
        } else {
            boolean check = adminDao.login(username, password);
            if (check) {
//                String imgname=null;
                session.setAttribute("username", username);
                session.setAttribute("admindetails", adminDao.getAdminDetails(username, password));
                return "adminDash";
            } else {
                model.addAttribute("msg", "username or password is wrong");
                return "redirect:/adminloginpage";
            }
        }

    }

    //admin Registration Form
    @RequestMapping("/regist")
    public String adminRegisterForm() {
        return "user_regist";
    }

    //Admin Registration Sign up
    @RequestMapping(value = "/uRegist", method = RequestMethod.POST)
    public String adminRegist(HttpServletRequest request, String path, Model model, @ModelAttribute("admin") Admin admin) {
        try {
            path = request.getServletContext().getRealPath("") + "/assets/adminImages";
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
            String fullname = mrequest.getParameter("fullname");
            String username = mrequest.getParameter("username");
            String password = mrequest.getParameter("password");
            String repassword = mrequest.getParameter("repassword");
            String pin = mrequest.getParameter("pin");
            String file = filename;
            
            if (fullname.isEmpty()) {
                model.addAttribute("msg", "Fullname is Empty !!");
            } else if (username.isEmpty()) {
                model.addAttribute("msg", "Username is Empty !!");
            } else if (password.isEmpty()) {
                model.addAttribute("msg", "Password is Empty !!");
            } else if (repassword.isEmpty()) {
                model.addAttribute("msg", "Confirm Password is Empty !!");
            } else if (pin.isEmpty()) {
                model.addAttribute("msg", "Pin is Empty !!");
            } else if (!password.equals(repassword)) {
                model.addAttribute("msg", "Password didn't matched");
            } else if (!pin.equals("1995")) {
                model.addAttribute("msg", "Pin invalid !!");
            }
           else if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("png")) {
                admin.setFullname(fullname);
                admin.setUsername(username);
                admin.setPassword(password);
                admin.setFile(file);
          
                adminDao.insert(admin);
                model.addAttribute("msg", "Data inserted success");
            } else {
                Files.delete(new File(path + "/" + filename).toPath());
                model.addAttribute("msg", "File not supported");
            }
         
        } catch (Exception e) {
        }
        return "redirect:/regist";
    }

    //Forgot password (update password) Form
    @RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
    public String updatePassword() {
        return "adminForgotPassword";
    }

    //Admin Forgot password update Code from database
    @RequestMapping(value = "/updatePass", method = RequestMethod.POST)
    public String forgotPassword(@RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("repassword") String repassword,
            @RequestParam("pin") String pin, Model model,@ModelAttribute("admin") Admin admin) {
        if (username.isEmpty()) {
            model.addAttribute("msg", "Username is Empty !!");
        } else if (password.isEmpty()) {
            model.addAttribute("msg", "Password is Empty");
        } else if (repassword.isEmpty()) {
            model.addAttribute("msg", "Confirm password is Empty");
        } else if (pin.isEmpty()) {
            model.addAttribute("msg", "Pin is Empty");
        } else if (!password.equals(repassword)) {
            model.addAttribute("msg", "Password didn't Matched !!");
        } else if (!pin.matches("1995")) {
            model.addAttribute("msg", "Pin Invalid !!");
        } else {
            admin.setUsername(username);
            admin.setPassword(password);
            boolean check = adminDao.forgotPassword(admin);
            if(check){
            model.addAttribute("msg1", "Successfully Updated");
            }else{
                model.addAttribute("msg", "Data not Valided !!");
            }
        }

        return "adminForgotPassword";
    }

    //Admin Profile
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String adminProfile() {
        return "adminProfile";
    }
    
    @RequestMapping(value = "/adminprofileu",method = RequestMethod.POST)
    public String adminProfileUpdate(Model model,@RequestParam("fullname") String fullname,
            @RequestParam("username") String username,
            @RequestParam("currentpassword") String password,
            @RequestParam("newpassword") String newpassword,
            @RequestParam("confirmpassword") String confirmpassword,@ModelAttribute("admin") Admin admin){
        if(fullname.isEmpty()){
            model.addAttribute("msg", "Full name is Empty !!");
        }else if(username.isEmpty()){
            model.addAttribute("msg", "Username is Empty !!");
        }else if(password.isEmpty()){
            model.addAttribute("msg", "Password is Empty !!");
        }else if(newpassword.isEmpty()){
            model.addAttribute("msg", "new password is Empty !!");
        }else if(confirmpassword.isEmpty()){
            model.addAttribute("msg", "Confirm Password is Empty !!");
        }else if(!newpassword.equals(confirmpassword)){
            model.addAttribute("msg", "Password are not Same !!");
        }else{
            admin.setFullname(fullname);
            admin.setUsername(username);
            admin.setPassword(password);
            boolean check = adminDao.adminUpdatePassword(admin, newpassword);
            if(check){
               model.addAttribute("msg1", "successfully Update Profile !!");
            }else{
                model.addAttribute("msg", "Current password Wrong !!");
            }
        }
        
        
        return "adminProfile";
        
    }

}
