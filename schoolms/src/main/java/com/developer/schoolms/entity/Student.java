package com.developer.schoolms.entity;



import com.developer.schoolms.utils.DemoConstants;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "student",uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Student implements Serializable{

    private static final long serialVersionUID = 201802060011746L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "batch_year")
    private String batchYear;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;

    @Column(name = "parent_name")
    private String parentName;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "image")
    private String imageUrl;

    @Column(name = "date_of_join")
    private LocalDateTime joinDate;

    @Column(name = "active")
    private boolean active = true;

    @Transient
    private String dob;

    @Transient
    private MultipartFile multipartFile;

    @Transient
    private List<String> batchYears = Arrays.asList(DemoConstants.YEAR_SESSION);

    @Transient
    private String[] newPassword =new String[2];

    @Transient
    private List<Attendance> tempAttendanceList = new ArrayList<>();

    @Transient
    private List<ExamResult> tempExamResultList = new ArrayList<>();

    @Transient
    private String attendanceStatus;

    public Student() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBatchYear() {
        return batchYear;
    }

    public void setBatchYear(String batchYear) {
        this.batchYear = batchYear;
    }

    public List<String> getBatchYears() {
        return batchYears;
    }

    public void setBatchYears(List<String> batchYears) {
        this.batchYears = batchYears;
    }

    public String[] getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String[] newPassword) {
        this.newPassword = newPassword;
    }

    public List<Attendance> getTempAttendanceList() {
        return tempAttendanceList;
    }

    public void setTempAttendanceList(List<Attendance> tempAttendanceList) {
        this.tempAttendanceList = tempAttendanceList;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public List<ExamResult> getTempExamResultList() {
        return tempExamResultList;
    }

    public void setTempExamResultList(List<ExamResult> tempExamResultList) {
        this.tempExamResultList = tempExamResultList;
    }
}
