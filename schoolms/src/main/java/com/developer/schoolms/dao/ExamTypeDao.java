package com.developer.schoolms.dao;

import com.developer.schoolms.entity.ExamType;
import com.developer.schoolms.utils.GenericDaoHibernateImpl;
import com.developer.schoolms.utils.SearchCriteria;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class ExamTypeDao extends GenericDaoHibernateImpl<ExamType, Serializable> {

    public List<ExamType> getSearchedExamType(SearchCriteria searchCriteria) {
        String hql = "from ExamType examType where 1=1";
        if (searchCriteria != null) {
            if(searchCriteria.getId()!=null) {
                hql+= " and examType.id=:examId";
            }
            if(searchCriteria.getName()!=null && !searchCriteria.getName().isEmpty()) {
                hql+= " and examType.examName like :examName";
            }
            if(searchCriteria.getDescription()!=null && !searchCriteria.getDescription().isEmpty()) {
                hql+= " and examType.description like :examDesc";
            }
        }
        hql += " order by examType.examName";
        Query query = currentSession().createQuery(hql);
        if (searchCriteria != null) {
            if(searchCriteria.getId()!=null) {
                query.setParameter("examId", searchCriteria.getId());
            }
            if(searchCriteria.getName()!=null && !searchCriteria.getName().isEmpty()) {
                query.setParameter("examName", searchCriteria.getName() + "%");
            }
            if(searchCriteria.getDescription()!=null && !searchCriteria.getDescription().isEmpty()) {
                query.setParameter("examDesc", searchCriteria.getDescription() + "%");
            }
        }
        return query.getResultList();
    }
}

