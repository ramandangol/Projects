package com.developer.schoolms.dao;


import com.developer.schoolms.entity.Grade;
import com.developer.schoolms.utils.GenericDaoHibernateImpl;
import com.developer.schoolms.utils.SearchCriteria;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class GradeDao extends GenericDaoHibernateImpl<Grade, Serializable> {

    public List<Grade> getSearchedGrade(SearchCriteria searchCriteria) {
        String hql = "from Grade grade where 1=1";
        if (searchCriteria != null) {
            if (searchCriteria.getTeacherId()!=null){
                hql+= " and grade.teacher.id=:tchId";
            }
            if(searchCriteria.getId()!=null) {
                hql+= " and grade.id=:grdId";
            }
            if(searchCriteria.getName()!=null && !searchCriteria.getName().isEmpty()) {
                hql+= " and grade.gradeName like :grdName";
            }
            if(searchCriteria.getSection()!=null && !searchCriteria.getSection().isEmpty()) {
                hql+= " and grade.gradeSection like :grdSection";
            }
            if(searchCriteria.getDescription()!=null && !searchCriteria.getDescription().isEmpty()) {
                hql+= " and grade.gradeDescription like :grdDescription";
            }

        }
        hql += " order by grade.gradeName";
        Query query = currentSession().createQuery(hql);
        if (searchCriteria != null) {
            if (searchCriteria.getTeacherId()!=null){
                query.setParameter("tecId",searchCriteria.getTeacherId());
            }
            if(searchCriteria.getId()!=null) {
                query.setParameter("grdId", searchCriteria.getId());
            }
            if(searchCriteria.getName()!=null && !searchCriteria.getName().isEmpty()) {
                query.setParameter("grdName", searchCriteria.getName() + "%");
            }
            if(searchCriteria.getSection()!=null && !searchCriteria.getSection().isEmpty()) {
                query.setParameter("grdSection", searchCriteria.getSection() + "%");
            }
            if(searchCriteria.getDescription()!=null && !searchCriteria.getDescription().isEmpty()) {
                query.setParameter("grdDescription", searchCriteria.getDescription() + "%");
            }
        }
        return query.getResultList();
    }

}
