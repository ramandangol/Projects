package com.developer.schoolms.dao;


import com.developer.schoolms.entity.Student;
import com.developer.schoolms.utils.GenericDaoHibernateImpl;
import com.developer.schoolms.utils.SearchCriteria;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class StudentDao extends GenericDaoHibernateImpl<Student,Serializable> {

    public List<Student> getSearchedStudent(SearchCriteria searchCriteria) {
        String hql = "from Student student where 1=1";
        if (searchCriteria != null) {
            if (searchCriteria.getGradeId()!=null){
                hql+= " and student.grade.id=:grdId";
            }
            if(searchCriteria.getId()!=null) {
                hql+= " and student.id=:stdId";
            }
            if(searchCriteria.getBatchYear()!=null && !searchCriteria.getBatchYear().isEmpty()) {
                hql+= " and student.batchYear like :stdBatchYear";
            }
            if(searchCriteria.getFirstName()!=null && !searchCriteria.getFirstName().isEmpty()) {
                hql+= " and student.firstname like :stdFirstName";
            }
            if(searchCriteria.getLastName()!=null && !searchCriteria.getLastName().isEmpty()) {
                hql+= " and student.lastname like :stdLastName";
            }
            if(searchCriteria.getAddress()!=null && !searchCriteria.getAddress().isEmpty()) {
                hql+= " and student.address like :stdAddress";
            }
        }
        hql += " order by student.firstname";
        Query query = currentSession().createQuery(hql);
        if (searchCriteria != null) {
            if (searchCriteria.getGradeId()!=null){
                query.setParameter("grdId",searchCriteria.getGradeId());
            }
            if(searchCriteria.getId()!=null) {
                query.setParameter("stdId", searchCriteria.getId());
            }
            if(searchCriteria.getBatchYear()!=null && !searchCriteria.getBatchYear().isEmpty()) {
                query.setParameter("stdBatchYear", searchCriteria.getBatchYear() + "%");
            }
            if(searchCriteria.getFirstName()!=null && !searchCriteria.getFirstName().isEmpty()) {
                query.setParameter("stdFirstName", searchCriteria.getFirstName() + "%");
            }
            if(searchCriteria.getLastName()!=null && !searchCriteria.getLastName().isEmpty()) {
                query.setParameter("stdLastName", searchCriteria.getLastName() + "%");
            }
            if(searchCriteria.getLastName()!=null && !searchCriteria.getLastName().isEmpty()) {
                query.setParameter("stdAddress", searchCriteria.getAddress() + "%");
            }
        }
        return query.getResultList();
    }
}
