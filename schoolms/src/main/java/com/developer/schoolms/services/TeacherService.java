package com.developer.schoolms.services;


import com.developer.schoolms.dao.TeacherDao;
import com.developer.schoolms.entity.Teacher;
import com.developer.schoolms.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    public List<Teacher> getSearchedteacher(SearchCriteria searchCriteria){
        return teacherDao.getSearchedTeacher(searchCriteria);
    }

    @Transactional(readOnly = false)
    public void saveTeacherDetail(Teacher teacher){
        teacherDao.save(teacher);
    }

    @Transactional(readOnly = false)
    public void updateTeacherDetail(Teacher teacher){
        teacherDao.update(teacher);
    }

    @Transactional(readOnly = false)
    public void deleteTeacherDetail(Long id){
        teacherDao.delete(id);
    }


}
