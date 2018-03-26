package com.developer.schoolms.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "exam_type")
public class ExamType implements Serializable{

    private static final Long serialVersionUID =201803220011402L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "exam_name")
    private String examName;

    @Column(name = "description")
    private String description;

    public ExamType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
