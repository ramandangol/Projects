package com.developer.schoolms.contorllers;


import com.developer.schoolms.entity.Course;
import com.developer.schoolms.entity.Grade;
import com.developer.schoolms.services.CourseService;
import com.developer.schoolms.services.GradeService;
import com.developer.schoolms.services.TeacherService;
import com.developer.schoolms.utils.DemoLogger;
import com.developer.schoolms.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/grade")
public class GradeController {

    private SearchCriteria searchCriteria;
    private List<Grade> gradeList;
    private List<Course> courseList;
    private Grade grade;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    // View List of grade in table page
    @GetMapping(value = {"/listgrade"})
    @PreAuthorize("hasAuthority('Grade_List')")
    public String retriveAllGrade(Model model){
        model.addAttribute("grades", this.gradeService.getSearchedGrade(null));
        return "grade/gradelist";
    }

    //Add Grade Form page
    @GetMapping(value = {"/gradedetails"})
    @PreAuthorize("hasAuthority('Grade_Add')")
    public String addGradeDetailsPage(Model model){
      try {
          this.grade = new Grade();
          this.grade.setCourseList(courseService.getSearchedCourse(null));
          model.addAttribute("grades",this.grade);
          model.addAttribute("teachers", teacherService.getSearchedteacher(null));
          model.addAttribute("msg","Add Grade");
      }catch (Exception ex){
          DemoLogger.error("Exception in Course Retrieval : " + ex.getMessage());
          ex.printStackTrace();
      }
        return "grade/addupdategrade";
    }

    //Add or update grade redirect to grade list
    @PostMapping(value = {"/gradedetails"})
    @PreAuthorize("hasAuthority('Grade_Add')")
    public String addGradeDetail(Grade grd, RedirectAttributes redirectAttributes){
        this.courseList = new ArrayList<>();
        grd.getCourseList().forEach(course ->{
            if (course.getSelected()) {
                this.courseList.add(course);
            }
        });
        grd.setGradeCourse(new HashSet<>(this.courseList));
       try {
           if(grd.getId() == null){
               gradeService.saveGradeDetail(grd);
               DemoLogger.info("Grade Successfully Added.");
               redirectAttributes.addFlashAttribute("message","Grade Successfully Added !!");
               redirectAttributes.addFlashAttribute("alertClass","alert-success");
           }else {
               gradeService.updateGradeDetail(grd);
               DemoLogger.info("Grade Successfully Updated.");
               redirectAttributes.addFlashAttribute("message","Grade Successfully Update !!");
               redirectAttributes.addFlashAttribute("alertClass","alert-success");
           }

       }catch (Exception ex){
        DemoLogger.error("Exception in Grade Add : " + ex.getMessage());
        ex.printStackTrace();
       }
       return "redirect:/grade/listgrade";
    }

    //To update get values through id
    @GetMapping(value = "/gradedetails/edit/{id}")
    @PreAuthorize("hasAuthority('Grade_Update')")
    public String editGradeDetailForm(@PathVariable("id") Long id, Model model){
        try {
            searchCriteria = new SearchCriteria();
            searchCriteria.setId(id);
            gradeList = gradeService.getSearchedGrade(searchCriteria);
            courseList = courseService.getSearchedCourse(null);
            if (gradeList != null && !gradeList.isEmpty()){
                this.grade = gradeList.get(0);
                this.grade.setCourseList(this.courseList);
                model.addAttribute("grades", this.grade);
                model.addAttribute("teachers", teacherService.getSearchedteacher(null));
                model.addAttribute("msg","update Grade");
                this.grade.getGradeCourse().forEach(gradeCourse ->{
                    grade.getCourseList().forEach(course -> {
                        if (gradeCourse.getId().equals(course.getId()))
                            course.setSelected(true);
                    });
                });

            }else {
                DemoLogger.error("Grade could not be retrieved");
            }
        }catch (Exception ex){
            DemoLogger.error("Exception in Grade Edit :" + ex.getMessage());
        }
        return "/grade/addupdategrade";
    }

    @GetMapping(value = {"/gradedetails/delete/{id}"})
    @PreAuthorize("hasAuthority('Grade_Delete')")
    public String deleteGradeDetails(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        try {
            gradeService.deleteGradeDetail(id);
            DemoLogger.info("Grade successfully Deleted !!");
            redirectAttributes.addFlashAttribute("message","Grade Successfully Deleted !!");
            redirectAttributes.addFlashAttribute("alertClass","alert-danger");
        }catch (Exception ex){
            DemoLogger.error("Exception in Grade Delete : " + ex.getMessage());
        }
        return "redirect:/grade/listgrade";
    }

}
