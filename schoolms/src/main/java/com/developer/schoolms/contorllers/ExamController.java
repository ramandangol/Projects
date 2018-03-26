package com.developer.schoolms.contorllers;

import com.developer.schoolms.dao.ExamResultDao;
import com.developer.schoolms.entity.*;
import com.developer.schoolms.services.*;
import com.developer.schoolms.utils.DemoLogger;
import com.developer.schoolms.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/exam")
public class ExamController {
   private ExamType examType;
   private ExamDate examDate;
   private SearchCriteria searchCriteria;
   private List<ExamType> examTypeList;
   private List<ExamDate> examDateList;
   private Student student;
   private Grade grade;
   private ExamResult examResult;


    @Autowired
    private ExamTypeService examTypeService;

    @Autowired
    private ExamDateService examDateService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExamResultService examResultService;

    @GetMapping(value = "/listexam")
    public String retriveAllExamType(Model model){
        this.examType= new ExamType();
        model.addAttribute("exams",examTypeService.getSearchedExamType(null));
        model.addAttribute("exam",this.examType);
        return "exam/examlist";
    }

    //Add or update Exam redirect to examlist
    @PostMapping(value = {"/examdetails"})
    public String addExamTypeDetails(ExamType examType, RedirectAttributes redirectAttributes){
        try {
            if(examType.getId() == null){
                examTypeService.saveExamType(examType);
                DemoLogger.info("Exam Name Successfully Added.");
                redirectAttributes.addFlashAttribute("message","Exam Name Successfully Added !!");
                redirectAttributes.addFlashAttribute("alertClass","alert-success");
            }else {
                examTypeService.updateExamType(examType);
                DemoLogger.info("Exam Name Successfully Updated.");
                redirectAttributes.addFlashAttribute("message","Exam Name Successfully Update !!");
                redirectAttributes.addFlashAttribute("alertClass","alert-success");
            }

        }catch (Exception ex){
            DemoLogger.error("Exception in Exam Name Add : " + ex.getMessage());
            ex.printStackTrace();
        }
        return "redirect:/exam/listexam";
    }

    @GetMapping(value = "/examdetails/edit/{id}")
    public String editExamTypeForm(@PathVariable("id") Long id,Model model){
        try {
         this.searchCriteria =new SearchCriteria();
         searchCriteria.setId(id);
         examTypeList = examTypeService.getSearchedExamType(searchCriteria);
         if (examTypeList !=null && !examTypeList.isEmpty()){
             this.examType = examTypeList.get(0);
             model.addAttribute("exam",examType);
             model.addAttribute("exams",examTypeService.getSearchedExamType(null));
         }
        }catch (Exception ex){
            DemoLogger.error("Exception in Exam Type Edit :" + ex.getMessage());
        }
        return "exam/examlist";
    }

    @GetMapping(value = "/examdetails/delete/{id}")
    public String deleteExamType(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        try {
            examTypeService.deleteExamType(id);
            DemoLogger.info("Exam Name successfully Deleted !!");
            redirectAttributes.addFlashAttribute("message","Exam Name Successfully Deleted !!");
            redirectAttributes.addFlashAttribute("alertClass","alert-danger");
        }catch (Exception ex){
            DemoLogger.error("Exception in Exam Name Delete : " + ex.getMessage());
        }
        return "redirect:/exam/listexam";
    }

    @GetMapping(value = "/listexamdate")
    public String retriveAllExamDate(Model model){
        this.examDate= new ExamDate();
        model.addAttribute("examdates",examDateService.getSearchedExamDate(null));
        model.addAttribute("exams",examTypeService.getSearchedExamType(null));
        model.addAttribute("examdate",this.examDate);
        return "exam/examdate";
    }


    //Add or update Exam  date redirect to examdatelist
    @PostMapping(value = {"/examdatedetails"})
    public String addExamDateDetails(ExamDate examDate, RedirectAttributes redirectAttributes){
        try {
            LocalDate localDate = LocalDate.parse(examDate.getDate());
            examDate.setStartDate(localDate);
            if(examDate.getId() == null){
                examDateService.saveExamDate(examDate);
                DemoLogger.info("Exam Date Successfully Added.");
                redirectAttributes.addFlashAttribute("message","Exam Date Successfully Added !!");
                redirectAttributes.addFlashAttribute("alertClass","alert-success");
            }else {
                examDateService.updateExamDate(examDate);
                DemoLogger.info("Exam Date Successfully Updated.");
                redirectAttributes.addFlashAttribute("message","Exam Date Successfully Update !!");
                redirectAttributes.addFlashAttribute("alertClass","alert-success");
            }

        }catch (Exception ex){
            DemoLogger.error("Exception in Exam Date Add : " + ex.getMessage());
            ex.printStackTrace();
        }
        return "redirect:/exam/listexamdate";
    }



    @GetMapping(value = "/examdatedetails/edit/{id}")
    public String editExamDateForm(@PathVariable("id") Long id,Model model){
        try {
            this.searchCriteria =new SearchCriteria();
            searchCriteria.setId(id);
            examDateList = examDateService.getSearchedExamDate(searchCriteria);
            if (examDateList !=null && !examDateList.isEmpty()){
                this.examDate = examDateList.get(0);
                model.addAttribute("examdate",examDate);
                model.addAttribute("examdates",examDateService.getSearchedExamDate(null));
                model.addAttribute("exams",examTypeService.getSearchedExamType(null));
            }
        }catch (Exception ex){
            DemoLogger.error("Exception in Exam Date Edit :" + ex.getMessage());
        }
        return "exam/examdate";
    }


    @GetMapping(value = "/examdatedetails/delete/{id}")
    public String deleteExamDate(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        try {
            examDateService.deleteExamDate(id);
            DemoLogger.info("Exam Date successfully Deleted !!");
            redirectAttributes.addFlashAttribute("message","Exam date Successfully Deleted !!");
            redirectAttributes.addFlashAttribute("alertClass","alert-danger");
        }catch (Exception ex){
            DemoLogger.error("Exception in date Name Delete : " + ex.getMessage());
        }
        return "redirect:/exam/listexamdate";
    }


    @GetMapping(value = "/marks")
    public String manageMarksPage(Model model){
        this.searchCriteria = new SearchCriteria();
        this.student = new Student();
        this.examResult = new ExamResult();
        model.addAttribute("student", this.student);
        model.addAttribute("grades",this.gradeService.getSearchedGrade(null));
        model.addAttribute("exammarks",this.examResult);
        return "exam/managemarks";
    }

    @PostMapping(value = "/marks")
    public String filterByBatchGradeMarks(Model model,Student std){
        searchCriteria = new SearchCriteria();
        searchCriteria.setBatchYear(std.getBatchYear());
        searchCriteria.setGradeId(std.getGrade().getId());
        this.student = new Student();
        this.examResult = new ExamResult();
        this.examResult.setStudentList(studentService.getSearchedStudent(searchCriteria));
        model.addAttribute("student",this.student);
        model.addAttribute("grades",this.gradeService.getSearchedGrade(null));
        model.addAttribute("exammarks",this.examResult);
        return "exam/managemarks";
    }


}
