package com.developer.schoolms.services;

import com.developer.schoolms.dao.GradeDao;
import com.developer.schoolms.entity.Grade;
import com.developer.schoolms.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GradeService {

    @Autowired
    private GradeDao gradeDao;

    public List<Grade> getSearchedGrade(SearchCriteria searchCriteria){
        return gradeDao.getSearchedGrade(searchCriteria);
    }

    @Transactional(readOnly = false)
    public void saveGradeDetail(Grade grade){
        gradeDao.save(grade);
    }

    @Transactional(readOnly = false)
    public void updateGradeDetail(Grade grade){
        gradeDao.update(grade);
    }

    @Transactional(readOnly = false)
    public void deleteGradeDetail(Long id){
        gradeDao.delete(id);
    }

}
