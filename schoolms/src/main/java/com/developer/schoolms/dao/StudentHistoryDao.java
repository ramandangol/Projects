package com.developer.schoolms.dao;


import com.developer.schoolms.entity.StudentHistory;
import com.developer.schoolms.utils.GenericDaoHibernateImpl;
import com.developer.schoolms.utils.SearchCriteria;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class StudentHistoryDao extends GenericDaoHibernateImpl<StudentHistory,Serializable> {

    public List<StudentHistory> getSearchedStudentHistory(SearchCriteria searchCriteria) {
        String hql = "from StudentHistory studentHistory where 1=1";
        if (searchCriteria != null) {
            if (searchCriteria.getStudentId()!=null){
                hql+= " and studentHistory.student.id=:stdId";
            }
            if(searchCriteria.getId()!=null) {
                hql+= " and studentHistory.id=:stdHId";
            }
            if(searchCriteria.getBatchYear()!=null && !searchCriteria.getBatchYear().isEmpty()) {
                hql+= " and studentHistory.batchYear like :stdHBatchYear";
            }
            if(searchCriteria.getGradeId()!=null) {
                hql+= " and studentHistory.gradeId like :stdHGradeId";
            }
        }
        hql += " order by student.firstname";
        Query query = currentSession().createQuery(hql);
        if (searchCriteria != null) {
            if (searchCriteria.getStudentId()!=null){
                query.setParameter("stdId",searchCriteria.getStudentId());
            }
            if(searchCriteria.getId()!=null) {
                query.setParameter("stdHId", searchCriteria.getId());
            }
            if(searchCriteria.getBatchYear()!=null && !searchCriteria.getBatchYear().isEmpty()) {
                query.setParameter("stdHBatchYear", searchCriteria.getBatchYear() + "%");
            }
            if(searchCriteria.getGradeId()!=null) {
                query.setParameter("stdHGradeId", searchCriteria.getGradeId() + "%");
            }
        }
        return query.getResultList();
    }
}
