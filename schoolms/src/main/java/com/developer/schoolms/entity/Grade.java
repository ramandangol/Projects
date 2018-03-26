package com.developer.schoolms.entity;



import com.developer.schoolms.utils.DemoConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "grade")
public class Grade implements Serializable {

    private static final long serialVersionUID = 201802140011008L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grade_name")
    private String gradeName;

    @Column(name = "grade_section")
    private String gradeSection;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "grade_description")
    private String gradeDescription;

    @Transient
    private List<String> gradeSections = Arrays.asList(DemoConstants.SECTION_TYPE);



    @ManyToMany(targetEntity = Course.class, fetch = FetchType.EAGER)
    @JoinTable(name = "grade_course", joinColumns =
    @JoinColumn(name = "grade_id"),inverseJoinColumns =
    @JoinColumn(name = "course_id"))
    private Set<Course> gradeCourse= new HashSet<>();

    @Transient
    private List<Course> courseList = new ArrayList<>();

    @Transient
    private List<Long> selectedCourse;

    public Grade() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGradeDescription() {
        return gradeDescription;
    }

    public void setGradeDescription(String gradeDescription) {
        this.gradeDescription = gradeDescription;
    }


    public String getGradeSection() {
        return gradeSection;
    }

    public void setGradeSection(String gradeSection) {
        this.gradeSection = gradeSection;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }


    public List<String> getGradeSections() {
        return gradeSections;
    }

    public void setGradeSections(List<String> gradeSections) {
        this.gradeSections = gradeSections;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


    public Set<Course> getGradeCourse() {
        return gradeCourse;
    }

    public void setGradeCourse(Set<Course> gradeCourse) {
        this.gradeCourse = gradeCourse;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Long> getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(List<Long> selectedCourse) {
        this.selectedCourse = selectedCourse;
    }
}
