package com.developer.schoolms.entity;

import com.developer.schoolms.utils.DemoConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "attendance")
public class Attendance implements Serializable{

    private static final long serialVersionUID = 201802250011035L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "datetime")
    private LocalDate dateTime;


    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "status")
    private String status;

    @Transient
    private List<String> statusType = Arrays.asList(DemoConstants.ATTENTANCE_TYPE);

    @Transient
    private List<Student> studentList = new ArrayList<>();

    public Attendance() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getStatusType() {
        return statusType;
    }

    public void setStatusType(List<String> statusType) {
        this.statusType = statusType;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
