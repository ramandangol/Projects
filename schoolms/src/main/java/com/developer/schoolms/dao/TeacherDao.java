package com.developer.schoolms.dao;



import com.developer.schoolms.entity.Teacher;
import com.developer.schoolms.utils.GenericDaoHibernateImpl;
import com.developer.schoolms.utils.SearchCriteria;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class TeacherDao extends GenericDaoHibernateImpl<Teacher,Serializable> {

    public List<Teacher> getSearchedTeacher(SearchCriteria searchCriteria) {
        String hql = "from Teacher teacher where 1=1";
        if (searchCriteria != null) {
            if(searchCriteria.getId()!=null) {
                hql+= " and teacher.id=:tchId";
            }
            if(searchCriteria.getFirstName()!=null && !searchCriteria.getFirstName().isEmpty()) {
                hql+= " and teacher.firstName like :tchFirstName";
            }
            if(searchCriteria.getLastName()!=null && !searchCriteria.getLastName().isEmpty()) {
                hql+= " and teacher.lastName like :tchLastName";
            }
            if(searchCriteria.getAddress()!=null && !searchCriteria.getAddress().isEmpty()) {
                hql+= " and teacher.address like :tchAddress";
            }
        }
        hql += " order by teacher.firstName";
        Query query = currentSession().createQuery(hql);
        if (searchCriteria != null) {
            if(searchCriteria.getId()!=null) {
                query.setParameter("tchId", searchCriteria.getId());
            }
            if(searchCriteria.getFirstName()!=null && !searchCriteria.getFirstName().isEmpty()) {
                query.setParameter("tchFirstName", searchCriteria.getFirstName() + "%");
            }
            if(searchCriteria.getLastName()!=null && !searchCriteria.getLastName().isEmpty()) {
                query.setParameter("tchLastName", searchCriteria.getLastName() + "%");
            }
            if(searchCriteria.getLastName()!=null && !searchCriteria.getLastName().isEmpty()) {
                query.setParameter("tchAddress", searchCriteria.getAddress() + "%");
            }
        }
        return query.getResultList();
    }
}
