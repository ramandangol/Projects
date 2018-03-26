package com.developer.schoolms.services;


import com.developer.schoolms.dao.ExamTypeDao;
import com.developer.schoolms.entity.ExamType;
import com.developer.schoolms.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExamTypeService {

    @Autowired
    private ExamTypeDao examTypeDao;

    public List<ExamType> getSearchedExamType(SearchCriteria searchCriteria) {
        return examTypeDao.getSearchedExamType(searchCriteria);
    }

    @Transactional(readOnly = false)
    public void saveExamType(ExamType examType) {
        examTypeDao.save(examType);
    }

    @Transactional(readOnly = false)
    public void updateExamType(ExamType examType) {
        examTypeDao.update(examType);
    }

    @Transactional(readOnly = false)
    public void deleteExamType(Long id) {
        examTypeDao.delete(id);
    }
}