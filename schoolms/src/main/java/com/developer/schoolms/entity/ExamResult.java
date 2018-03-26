package com.developer.schoolms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exam_result")
public class ExamResult implements Serializable{

    private static final long serialVersionUID = 201803230012244L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student studentId;

    @ManyToOne
    @JoinColumn(name = "examdate_id")
    private ExamDate examDateId;

    @Column(name = "batch_year")
    private String batchYear;

    @Column(name = "grade_id")
    private Long gradeId;

    @Transient
    private List<Student> studentList = new ArrayList<>();

    public ExamResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public ExamDate getExamDateId() {
        return examDateId;
    }

    public void setExamDateId(ExamDate examDateId) {
        this.examDateId = examDateId;
    }

    public String getBatchYear() {
        return batchYear;
    }

    public void setBatchYear(String batchYear) {
        this.batchYear = batchYear;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
