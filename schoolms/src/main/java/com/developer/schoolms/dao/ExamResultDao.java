package com.developer.schoolms.dao;

import com.developer.schoolms.entity.ExamResult;
import com.developer.schoolms.utils.GenericDaoHibernateImpl;
import com.developer.schoolms.utils.SearchCriteria;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class ExamResultDao extends GenericDaoHibernateImpl<ExamResult, Serializable> {

    public List<ExamResult> getSearchedExamResult(SearchCriteria searchCriteria) {
        String hql = "from ExamResult examResult where 1=1";
        if (searchCriteria != null) {
            if (searchCriteria.getStudentId()!=null){
                hql+= " and examResult.studentId.id=:examResultStdId";
            }
            if(searchCriteria.getId()!=null) {
                hql+= " and examResult.id=:examResultId";
            }
            if(searchCriteria.getBatchYear()!=null && !searchCriteria.getBatchYear().isEmpty()) {
                hql+= " and examResult.batchYear like :examResultBatch";
            }
            if(searchCriteria.getGradeId()!=null) {
                hql+= " and examResult.gradeId like :examResultGrade";
            }
        }
        hql += " order by ExamResult.startDate";
        Query query = currentSession().createQuery(hql);
        if (searchCriteria != null) {
            if (searchCriteria.getStudentId()!=null){
                query.setParameter("examResultStdId",searchCriteria.getStudentId());
            }
            if(searchCriteria.getId()!=null) {
                query.setParameter("examResultId", searchCriteria.getId());
            }
            if(searchCriteria.getBatchYear()!=null && !searchCriteria.getBatchYear().isEmpty()) {
                query.setParameter("examResultBatch", searchCriteria.getBatchYear() + "%");
            }
            if(searchCriteria.getGradeId()!=null) {
                query.setParameter("examResultGrade", searchCriteria.getGradeId() + "%");
            }
        }
        return query.getResultList();
    }
}

