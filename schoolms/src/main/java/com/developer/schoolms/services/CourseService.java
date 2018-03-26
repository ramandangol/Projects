package com.developer.schoolms.services;


import com.developer.schoolms.dao.CourseDao;
import com.developer.schoolms.entity.Course;
import com.developer.schoolms.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    public List<Course> getSearchedCourse(SearchCriteria searchCriteria) {
        return courseDao.getSearchedCourse(searchCriteria);
    }

    @Transactional(readOnly = false)
    public void saveCourse(Course course) {
        courseDao.save(course);
    }

    @Transactional(readOnly = false)
    public void updateCourse(Course course) {
        courseDao.update(course);
    }

    @Transactional(readOnly = false)
    public void deleteCourse(Long id) {
        courseDao.delete(id);
    }
}