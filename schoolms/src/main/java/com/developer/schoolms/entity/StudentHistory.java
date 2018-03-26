package com.developer.schoolms.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student_history")
public class StudentHistory implements Serializable{

    private static Long serialVersionUID = 201803200011329L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private Student student;

    @Column(name = "batch_year")
    private String batchYear;

    @Column(name = "garde_id")
    private Long gradeId;

    public StudentHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
}
