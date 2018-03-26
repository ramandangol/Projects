package com.developer.schoolms.dao;


import com.developer.schoolms.entity.Attendance;
import com.developer.schoolms.utils.GenericDaoHibernateImpl;
import com.developer.schoolms.utils.SearchCriteria;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class AttendanceDao extends GenericDaoHibernateImpl<Attendance, Serializable> {

    public List<Attendance> getSearchedAttendance(SearchCriteria searchCriteria) {
        String hql = "from Attendance attendance where 1=1";
        if (searchCriteria != null) {
            if (searchCriteria.getStudentId()!=null){
                hql+= " and attendance.student.id=:stdId";
            }
            if(searchCriteria.getId()!=null) {
                hql+= " and attendance.id=:attenId";
            }
            if(searchCriteria.getName()!=null && !searchCriteria.getName().isEmpty()) {
                hql+= " and attendance.status like :attenName";
            }
        }
//        hql += " order by attendance.gradeName";
        Query query = currentSession().createQuery(hql);
        if (searchCriteria != null) {
            if (searchCriteria.getStudentId()!=null){
                query.setParameter("stdId",searchCriteria.getStudentId());
            }
            if(searchCriteria.getId()!=null) {
                query.setParameter("attenId", searchCriteria.getId());
            }
            if(searchCriteria.getName()!=null && !searchCriteria.getName().isEmpty()) {
                query.setParameter("attenName", searchCriteria.getName() + "%");
            }
        }
        return query.getResultList();
    }


}

