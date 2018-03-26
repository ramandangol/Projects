package com.developer.schoolms.services;

import com.developer.schoolms.dao.StudentDao;
import com.developer.schoolms.entity.Student;
import com.developer.schoolms.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public List<Student> getSearchedStudent(SearchCriteria searchCriteria){
        return studentDao.getSearchedStudent(searchCriteria);
    }

    @Transactional(readOnly = false)
    public void saveStudentDetail(Student student){
        studentDao.save(student);
    }

    @Transactional(readOnly = false)
    public void updateStudentDetail(Student student){
        studentDao.update(student);
    }

    @Transactional(readOnly = false)
    public void deleteStudentDetail(Long id){
        studentDao.delete(id);
    }

}
