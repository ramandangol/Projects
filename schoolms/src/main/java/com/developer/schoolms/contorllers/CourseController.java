package com.developer.schoolms.contorllers;

import com.developer.schoolms.entity.Course;
import com.developer.schoolms.services.CourseService;
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

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    private Course course;
    private SearchCriteria searchCriteria;
    private List<Course>  courseList;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    //Course list
    @GetMapping(value = {"/listcourse"})
    @PreAuthorize("hasAuthority('Course_List')")
    public String retriveAllCourse(Model model){
        model.addAttribute("courses",courseService.getSearchedCourse(null));
        return "/course/courselist";
    }

    //add course page
    @GetMapping(value = {"/coursedetails"})
    @PreAuthorize("hasAuthority('Course_Add')")
    public String addCoursePage(Model model) {
        this.course = new Course();
        model.addAttribute("courses", this.course);
        model.addAttribute("teachers", teacherService.getSearchedteacher(null));
        return "/course/addupdatecourse";
    }


    //Edit Course page
    @GetMapping(value = {"/coursedetails/edit/{id}"})
    @PreAuthorize("hasAuthority('Course_Update')")
    public String editCourse(@PathVariable("id") Long id, Model model) {
        try{
            searchCriteria = new SearchCriteria();
            searchCriteria.setId(id);
            this.courseList = courseService.getSearchedCourse(searchCriteria);
            if(courseList!=null && !courseList.isEmpty()) {
                this.course = courseList.get(0);
                model.addAttribute("courses", this.course);
                model.addAttribute("teachers", teacherService.getSearchedteacher(null));
            } else {
                DemoLogger.error("Course data could not be retrieved !");
            }
        } catch(Exception ex){
            DemoLogger.error("Exception in Course  Edit : " + ex.getMessage());
            ex.printStackTrace();
        }
        return "/course/addupdatecourse";
    }


    //Add or update Course redirect to course list
    @PostMapping(value = {"/coursedetails"})
    @PreAuthorize("hasAuthority('Course_Add')")
    public String addTeacherDetail(Course course, RedirectAttributes redirectAttributes){
        try {
            if(course.getId() == null){
                courseService.saveCourse(course);
                DemoLogger.info("Course Successfully Added.");
                redirectAttributes.addFlashAttribute("message","Course Successfully Added..");
                redirectAttributes.addFlashAttribute("alertClass","alert-success");

            }else {
                courseService.updateCourse(course);
                DemoLogger.info("Course Successfully Updated.");
                redirectAttributes.addFlashAttribute("message","Course Successfully Updated.");
                redirectAttributes.addFlashAttribute("alertClass","alert-success");
            }

        }catch (Exception ex){
            DemoLogger.error("Exception in Course Add : " + ex.getMessage());
            ex.printStackTrace();
        }
        return "redirect:/course/listcourse";
    }

    @GetMapping(value = {"/coursedetails/delete/{id}"})
    @PreAuthorize("hasAuthority('Course_Delete')")
    public String deleteCourseDetails(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        try {
            courseService.deleteCourse(id);
            DemoLogger.info("Course Successfully Deleted !!");
            redirectAttributes.addFlashAttribute("message","Course Successfully Deleted");
            redirectAttributes.addFlashAttribute("alertClass","alert-danger");
        }catch (Exception ex){
            DemoLogger.error("Exception in Course Delete : " + ex.getMessage());
            ex.printStackTrace();
        }
        return "redirect:/course/listcourse";
    }

}
