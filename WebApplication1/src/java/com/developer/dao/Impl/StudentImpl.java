/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.developer.dao.Impl;

import com.developer.dao.StudentDao;
import com.developer.entity.Student;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bladestorm
 */
@Repository("studentDao")
public class StudentImpl implements StudentDao {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public boolean StudentRegister(Student student) {
        boolean check = false;
        try {
            sessionFactory.getCurrentSession().save(student);
            check = true;
        } catch (Exception e) {
        }
        return check;
    }

    @Transactional
    public List<Student> getAllStudent() {
        return sessionFactory.getCurrentSession().createQuery("from Student")
                .list();
    }

    @Transactional
    public Student getById(int id) {
        Student student = null;
        try {
            student = (Student) sessionFactory.getCurrentSession().createQuery("from Student where id=:id")
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception e) {
        }

        return student;
    }

    @Transactional
    public void delete(Student sid) {
    sessionFactory.getCurrentSession().delete(sid);
    }

    @Transactional
    public boolean StudentUpdate(Student student) {
        boolean check = false;
        try {
             sessionFactory.getCurrentSession().createQuery("update Student set firstname=:firstname,lastname=:lastname,address=:address,city=:city,state=:state,zip=:zip,gender=:gender,day=:day,month=:month,year=:year,grade=:grade,parentname=:parentname,phone=:phone,email=:email,password=:password where id=:id")
                .setParameter("firstname", student.getFirstname())
                     .setParameter("lastname", student.getLastname())
                     .setParameter("address", student.getAddress())
                     .setParameter("city", student.getCity())
                     .setParameter("state", student.getState())
                     .setParameter("zip", student.getZip())
                     .setParameter("gender", student.getGender())
                     .setParameter("day", student.getDay())
                     .setParameter("month", student.getMonth())
                     .setParameter("year", student.getYear())
                     .setParameter("grade", student.getGrade())
                     .setParameter("parentname", student.getParentname())
                     .setParameter("phone", student.getPhone())
                     .setParameter("email", student.getEmail())
                     .setParameter("password", student.getPassword())
                     .setParameter("id", student.getId())
                     
                .executeUpdate();
             check=true;
        } catch (Exception e) {
        }
        return check;
    }

  

}
