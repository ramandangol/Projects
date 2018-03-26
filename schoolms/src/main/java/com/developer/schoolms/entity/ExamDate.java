package com.developer.schoolms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "exam_date")
public class ExamDate implements Serializable{

    private static final long serialVersionUID = 201803220011408L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @ManyToOne
    @JoinColumn(name = "exam_typeid")
    private ExamType examTypeId;

    @Transient
    private String date;

    public ExamDate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public ExamType getExamTypeId() {
        return examTypeId;
    }

    public void setExamTypeId(ExamType examTypeId) {
        this.examTypeId = examTypeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
