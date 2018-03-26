package com.developer.schoolms.dao;

import com.developer.schoolms.entity.ExamDate;
import com.developer.schoolms.utils.GenericDaoHibernateImpl;
import com.developer.schoolms.utils.SearchCriteria;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class ExamDateDao extends GenericDaoHibernateImpl<ExamDate, Serializable> {

    public List<ExamDate> getSearchedExamDate(SearchCriteria searchCriteria) {
        String hql = "from ExamDate examDate where 1=1";
        if (searchCriteria != null) {
            if (searchCriteria.getExamTypeId()!=null){
                hql+= " and examDate.examTypeId.id=:examtypeId";
            }
            if(searchCriteria.getId()!=null) {
                hql+= " and examDate.id=:examdateId";
            }
            if(searchCriteria.getDate()!=null) {
                hql+= " and examDate.startDate like :examdatedate";
            }
        }
        hql += " order by examDate.startDate";
        Query query = currentSession().createQuery(hql);
        if (searchCriteria != null) {
            if (searchCriteria.getExamTypeId()!=null){
                query.setParameter("examtypeId",searchCriteria.getExamTypeId());
            }
            if(searchCriteria.getId()!=null) {
                query.setParameter("examdateId", searchCriteria.getId());
            }
            if(searchCriteria.getDate()!=null) {
                query.setParameter("examdatedate", searchCriteria.getName() + "%");
            }
            if(searchCriteria.getDescription()!=null && !searchCriteria.getDescription().isEmpty()) {
                query.setParameter("couDesc", searchCriteria.getDescription() + "%");
            }
        }
        return query.getResultList();
    }
}

