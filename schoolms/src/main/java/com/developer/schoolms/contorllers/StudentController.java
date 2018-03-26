package com.developer.schoolms.contorllers;


import com.developer.schoolms.entity.Attendance;
import com.developer.schoolms.entity.Student;
import com.developer.schoolms.services.AttendanceService;
import com.developer.schoolms.services.GradeService;
import com.developer.schoolms.services.StudentHistoryService;
import com.developer.schoolms.services.StudentService;
import com.developer.schoolms.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {


    private Student student;
    private Attendance attendance;
    private SearchCriteria searchCriteria;
    private List<Student> studentList;
    private ClassLoader classLoader = getClass().getClassLoader();
    private List<Attendance> attendanceList;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private StudentHistoryService studentHistoryService;

    @GetMapping(value = {"/liststudent"})
    @PreAuthorize("hasAuthority('Student_List')")
    public String retrieveAllStudent(Model model){
        this.student = new Student();
        model.addAttribute("students",this.studentService.getSearchedStudent(null));
        model.addAttribute("student",this.student);
        model.addAttribute("grades",this.gradeService.getSearchedGrade(null));

        return "student/studentlist";
    }

    @PostMapping(value = {"/liststudent"})
    @PreAuthorize("hasAuthority('Student_List')")
    public String filterByBatchGrade(Model model, Student std){
        try {
            searchCriteria = new SearchCriteria();
            searchCriteria.setBatchYear(std.getBatchYear());
            searchCriteria.setGradeId(std.getGrade().getId());
            this.student = new Student();
            model.addAttribute("students",this.studentService.getSearchedStudent(searchCriteria));
            model.addAttribute("student",this.student);
            model.addAttribute("grades",this.gradeService.getSearchedGrade(null));
            model.addAttribute("btchyr",std.getBatchYear());
        }catch (Exception ex){

        }
        return "student/studentlist";
    }


    @GetMapping(value = {"/studentdtl"})
    @PreAuthorize("hasAuthority('Student_Add')")
    public String addStudentDetailsPage(Model model){
        this.student = new Student();
        model.addAttribute("student",this.student);
        model.addAttribute("grades",this.gradeService.getSearchedGrade(null));
        model.addAttribute("msg","Student Registration Form");
        return "/student/addupdatestudent";
    }

    @PostMapping(value = {"/studentdtl"})
    @PreAuthorize("hasAuthority('Student_Add')")
    public String addStudentDetail(Student std, RedirectAttributes redirectAttributes){
        try {
            if (std.getMultipartFile()!=null && !std.getMultipartFile().isEmpty()){
                std.setImageUrl(FileUtil.saveFile(DemoConstants.STUDENT_REPO,
                        std.getMultipartFile().getBytes(),
                        MimeTypeToExtension.MIME_TYPE_TO_EXTENSION.get(std.getMultipartFile().getContentType())));
            }
//
            LocalDate localDate = LocalDate.parse(std.getDob());
            std.setDateOfBirth(localDate);

            std.setJoinDate(LocalDateTime.now());
            if(std.getId()==null){
                if(!std.getNewPassword()[0].equals(std.getNewPassword()[1])){
                    redirectAttributes.addFlashAttribute("message","Password Didn't matched");
                    redirectAttributes.addFlashAttribute("alertClass","alert-danger");
                    return "redirect:/student/studentdtl";
                }else {
                    std.setPassword(std.getNewPassword()[0]);
                    studentService.saveStudentDetail(std);
                    DemoLogger.info("Student Successfully Added.");
                    redirectAttributes.addFlashAttribute("message","Student Successfully Added.");
                    redirectAttributes.addFlashAttribute("alertClass","alert-success");
                }
            }else {
                studentService.updateStudentDetail(std);
                DemoLogger.info("Student Successfully Updated.");
                redirectAttributes.addFlashAttribute("message","Student Successfully Updated.");
                redirectAttributes.addFlashAttribute("alertClass","alert-success");
            }
        }catch (Exception ex){
            DemoLogger.error("Exception in Student Add : "+ ex.getMessage());
            ex.printStackTrace();
        }
        return "redirect:/student/liststudent";
    }

    @GetMapping(value = {"/studentdtl/edit/{id}"})
    @PreAuthorize("hasAuthority('Student_Update')")
    public String editStudentDetailForm(@PathVariable("id") Long id, Model model){
        try {
            searchCriteria = new SearchCriteria();
            searchCriteria.setId(id);
            studentList = studentService.getSearchedStudent(searchCriteria);

            if(studentList != null && !studentList.isEmpty()){
                this.student = studentList.get(0);
                model.addAttribute("grades",this.gradeService.getSearchedGrade(null));
                model.addAttribute("student",this.student);
                model.addAttribute("msg","student Update Form");
            }else {
                Console.out("Student could not be retrieved");
            }
        }catch (Exception ex){
            DemoLogger.error("Exception in Course Edit : " + ex.getMessage());
            ex.printStackTrace();
        }
        return "/student/addupdatestudent";
    }

    @GetMapping(value = {"/studentdtl/delete/{id}"})
    @PreAuthorize("hasAuthority('Student_Delete')")
    public String deleteStudentDetail(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        studentService.deleteStudentDetail(id);
        redirectAttributes.addFlashAttribute("message","Student Successfully Deleted.");
        redirectAttributes.addFlashAttribute("alertClass","alert-danger");
        return "redirect:/student/liststudent";
    }

    @GetMapping(value = {"/studentimage/{fileName}"})
    @PreAuthorize("hasAuthority('Student_Update')")
    public void studentImage(HttpServletRequest request,
                             HttpServletResponse response, Model model,
                             @PathVariable("fileName") String fileName)throws IOException,URISyntaxException{
        if(fileName != null){
            response.setContentType("image/jpeg, image/png");
            Path path = Paths.get(DemoConstants.STUDENT_REPO + File.separator+fileName);
            if (!path.toFile().exists()){
                path = Paths.get(classLoader.getResource("public/img/image-not-available.png").toURI());
            }
            response.getOutputStream().write(Files.readAllBytes(path));
        }
        response.getOutputStream().close();
    }

    @GetMapping(value = {"/studentimage/delete/{studentId}"})
    @PreAuthorize("hasAuthority('Student_Update')")
    public String deleteStudentImage(@PathVariable("studentId") Long studentId,RedirectAttributes redirectAttributes,Model model){
        try {
            searchCriteria = new SearchCriteria();
            searchCriteria.setId(studentId);
            this.studentList = studentService.getSearchedStudent(searchCriteria);
            if(studentList!=null &&  !studentList.isEmpty()){
                this.student = this.studentList.get(0);
                this.student.setImageUrl(null);
                model.addAttribute("grades",this.gradeService.getSearchedGrade(null));
                model.addAttribute("student",this.student);
                model.addAttribute("msg","student Update Form");
            }
        }catch (Exception ex){
            DemoLogger.error("Exception in Student Edit : " + ex.getMessage());
            redirectAttributes.addFlashAttribute("message","Exception in Student Edit");
            redirectAttributes.addFlashAttribute("alertClass","alert-danger");
            ex.printStackTrace();
        }
        return "/student/addupdatestudent";
    }

    //Student Attendance

    @GetMapping(value = {"/attendance"})
    @PreAuthorize("hasAuthority('Attendance_List')")
    public String retrieveAllStudentattendance(Model model){
        searchCriteria = new SearchCriteria();
        this.student = new Student();
        model.addAttribute("student",this.student);
        model.addAttribute("grades",this.gradeService.getSearchedGrade(null));
        this.attendance = new Attendance();
        model.addAttribute("attendance",this.attendance);
        return "/student/studentAttendancePage";
    }

    @PostMapping(value = {"/attendance"})
    public String filterByBatchGradeattendance(Model model, Student std){
        try {
            searchCriteria = new SearchCriteria();
            searchCriteria.setBatchYear(std.getBatchYear());
            searchCriteria.setGradeId(std.getGrade().getId());
            this.student = new Student();
            this.attendance = new Attendance();
            this.student.setTempAttendanceList(attendanceService.getSearchedAttendance(null));
            model.addAttribute("student",this.student);
            model.addAttribute("grades",this.gradeService.getSearchedGrade(null));
            this.attendance = new Attendance();
            this.attendance.setStudentList(studentService.getSearchedStudent(searchCriteria));
            model.addAttribute("attendance",this.attendance);
        }catch (Exception ex){

        }
        return "/student/studentAttendancePage";
    }


    @PostMapping(value = {"/saveattendance"})
    public String saveAttendanceData(Attendance attenStudent,RedirectAttributes redirectAttributes){
        try {
            this.attendanceList = new ArrayList<>();

           studentList = attenStudent.getStudentList();
            studentList.forEach(stu-> {
                this.attendance = new Attendance();
               for(int i=0; i<studentList.size();i++){
                   attendance.setDateTime(LocalDate.now());
                   attendance.setStudentId(stu.getId());
                   attendance.setStatus(stu.getAttendanceStatus());
                   attendanceList.add(attendance);
               }
                attendanceService.saveAllAttendance(attendanceList);
           });
            redirectAttributes.addFlashAttribute("message","Student Attendance as on date of " + LocalDate.now());
            redirectAttributes.addFlashAttribute("alertClass","alert-success");
       }catch (Exception ex){

       }
        return "redirect:/student/attendance";
    }

}
