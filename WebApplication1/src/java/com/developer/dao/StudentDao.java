/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.developer.dao;

import com.developer.entity.Student;
import java.util.List;

/**
 *
 * @author Bladestorm
 */
public interface StudentDao {
    boolean StudentRegister(Student student);
    public List<Student> getAllStudent();
    Student getById(int id);
    public void delete(Student sid);
    boolean StudentUpdate(Student student);
}
