package com.developer.schoolms.contorllers;


import com.developer.schoolms.entity.Teacher;
import com.developer.schoolms.services.TeacherService;
import com.developer.schoolms.utils.DemoLogger;
import com.developer.schoolms.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private SearchCriteria searchCriteria;
    private Teacher teacher;
    private List<Teacher> teacherList;

    @Autowired
    TeacherService teacherService;

    @GetMapping(value = {"/listteacher"})
    @PreAuthorize("hasAuthority('Teacher_List')")
    public String retriveAllTeacher(Model model){
        model.addAttribute("teachers", teacherService.getSearchedteacher(null ));
        return "/teacher/teacherlist";
    }

    @GetMapping(value = {"/teacherdtl"})
    @PreAuthorize("hasAuthority('Teacher_Add')")
    public String addTeacherPage(Model model){
        this.teacher = new Teacher();
        model.addAttribute("msg","Teacher Registration Form");
        model.addAttribute("teacher",this.teacher);
        return "/teacher/addupdateteacher";
    }

    @PostMapping(value = {"/teacherdtl"})
    @PreAuthorize("hasAuthority('Teacher_Add')")
    public String addTeacher(Teacher teacher, RedirectAttributes redirectAttributes) {

        teacher.setDateOfJoin(LocalDateTime.now());
        try {
            if(teacher.getId()==null) {
                teacher.setPassword(teacher.getNewPassword()[0]);
                if (teacher.getFirstName().isEmpty()){
                    redirectAttributes.addFlashAttribute("message","First name is Empty !!");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                }else if (!teacher.getFirstName().matches("[a-zA-Z_]+")){
                    redirectAttributes.addFlashAttribute("message","Alphabets only numbers or symbol not valid !! in First name");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                }else if (teacher.getLastName().isEmpty()){
                    redirectAttributes.addFlashAttribute("message","Last Name is Empty !!");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                }else if (!teacher.getLastName().matches("[a-zA-Z_]+")){
                    redirectAttributes.addFlashAttribute("message","Alphabets only numbers or symbol not valid !! in Last Name");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                }else if (teacher.getAddress().isEmpty()){
                    redirectAttributes.addFlashAttribute("message","Address is Empty !!");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                }else if (!teacher.getAddress().matches("[a-zA-Z_]+")){
                    redirectAttributes.addFlashAttribute("message","Alphabets only numbers or symbol not valid !! in Address");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                }else if (teacher.getCity().isEmpty()){
                    redirectAttributes.addFlashAttribute("message","City is Empty !!");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                }else if (!teacher.getCity().matches("[a-zA-Z_]+")){
                    redirectAttributes.addFlashAttribute("message","Alphabets only numbers or symbol not valid !! in City");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                }else if (teacher.getState().isEmpty()){
                    redirectAttributes.addFlashAttribute("message","State is Empty !!");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                }else if (!teacher.getState().matches("[a-zA-Z_]+")){
                    redirectAttributes.addFlashAttribute("message","Alphabets only numbers or symbol not valid !! in State");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                } else if (teacher.getZipCode().isEmpty()){
                    redirectAttributes.addFlashAttribute("message","Zip Code is Empty !!");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                }else if (!teacher.getZipCode().matches("[0-9_]+")){
                    redirectAttributes.addFlashAttribute("message","Alphabets not valid !! in Zip code");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                }else if (teacher.getPhone().isEmpty()){
                    redirectAttributes.addFlashAttribute("message","Phone Number is Empty !!");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                }else if (!teacher.getPhone().matches("[0-9_]+")){
                    redirectAttributes.addFlashAttribute("message","Alphabets not valid !! in Phone no");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                }else if (teacher.getEmail().isEmpty()){
                    redirectAttributes.addFlashAttribute("message","Email is Empty !!");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                }else if (teacher.getNewPassword()[0].isEmpty()){
                    redirectAttributes.addFlashAttribute("message","Password is Empty !!");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                }else if (teacher.getNewPassword()[1].isEmpty()){
                    redirectAttributes.addFlashAttribute("message","Confirm Password is Empty !!");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                } else if(!teacher.getNewPassword()[0].equals(teacher.getNewPassword()[1])){
                   redirectAttributes.addFlashAttribute("message","Password didn't matched !!");
                   redirectAttributes.addFlashAttribute("alertClass","alert-danger");
               }else {
                   teacherService.saveTeacherDetail(teacher);
                   redirectAttributes.addFlashAttribute("message","Teacher Successfully Added.");
                   redirectAttributes.addFlashAttribute("alertClass","alert-success");
                   DemoLogger.info("Teacher Successfully Added.");

               }
                return "redirect:/teacher/teacherdtl";
            } else {
                teacherService.updateTeacherDetail(teacher);
                redirectAttributes.addFlashAttribute("message","Teacher Successfully Updated..");
                redirectAttributes.addFlashAttribute("alertClass","alert-success");
                DemoLogger.info("Teacher Successfully Updated.");
                return "redirect:/teacher/listteacher";
            }
        } catch (Exception ex) {
            DemoLogger.error("Exception in Teacher Add : " + ex.getMessage());
            ex.printStackTrace();
            return "redirect:/teacher/listteacher";
        }


    }

    @GetMapping(value = {"/teacherdtl/edit/{id}"})
    @PreAuthorize("hasAuthority('Teacher_Update')")
    public String editTeacher(@PathVariable("id") Long id, Model model) {
        try{
            searchCriteria = new SearchCriteria();
            searchCriteria.setId(id);
            teacherList= teacherService.getSearchedteacher(searchCriteria);
            if(teacherList!=null && !teacherList.isEmpty()) {
                this.teacher = teacherList.get(0);
                model.addAttribute("teacher", this.teacher);
                model.addAttribute("msg","Teacher Update Form");
            } else {
                DemoLogger.error("Teacher could not be retrieved !");
            }
        } catch(Exception ex){
            DemoLogger.error("Exception in teacher Edit : " + ex.getMessage());
            ex.printStackTrace();
        }
        return "/teacher/addupdateteacher";
    }

    @GetMapping(value = {"/teacherdtl/delete/{id}"})
    @PreAuthorize("hasAuthority('Teacher_Delete')")
    public String deleteProductCategory(@PathVariable("id") Long id,RedirectAttributes redirectAttributes) {
        try{
            teacherService.deleteTeacherDetail(id);
            redirectAttributes.addFlashAttribute("message","Teacher Successfully Deleted.");
            redirectAttributes.addFlashAttribute("alertClass","alert-danger");
            DemoLogger.info("Teacher Successfully Deleted.");
        } catch(Exception ex) {
            DemoLogger.error("Exception in teacher Delete : " + ex.getMessage());
            ex.printStackTrace();
        }
        return "redirect:/teacher/listteacher";
    }


}
