package com.developer.schoolms.dao;

import com.developer.schoolms.entity.Course;
import com.developer.schoolms.utils.GenericDaoHibernateImpl;
import com.developer.schoolms.utils.SearchCriteria;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class CourseDao extends GenericDaoHibernateImpl<Course, Serializable> {

    public List<Course> getSearchedCourse(SearchCriteria searchCriteria) {
        String hql = "from Course course where 1=1";
        if (searchCriteria != null) {
            if (searchCriteria.getTeacherId()!=null){
                hql+= " and course.teacher.id=:tchId";
            }
            if(searchCriteria.getId()!=null) {
                hql+= " and course.id=:couId";
            }
            if(searchCriteria.getName()!=null && !searchCriteria.getName().isEmpty()) {
                hql+= " and course.courseName like :couName";
            }
            if(searchCriteria.getDescription()!=null && !searchCriteria.getDescription().isEmpty()) {
                hql+= " and course.courseDescription like :couDesc";
            }
        }
        hql += " order by course.courseName";
        Query query = currentSession().createQuery(hql);
        if (searchCriteria != null) {
            if (searchCriteria.getTeacherId()!=null){
                query.setParameter("tecId",searchCriteria.getTeacherId());
            }
            if(searchCriteria.getId()!=null) {
                query.setParameter("couId", searchCriteria.getId());
            }
            if(searchCriteria.getName()!=null && !searchCriteria.getName().isEmpty()) {
                query.setParameter("couName", searchCriteria.getName() + "%");
            }
            if(searchCriteria.getDescription()!=null && !searchCriteria.getDescription().isEmpty()) {
                query.setParameter("couDesc", searchCriteria.getDescription() + "%");
            }
        }
        return query.getResultList();
    }
}

