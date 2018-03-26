package com.developer.schoolms.services;


import com.developer.schoolms.dao.ExamDateDao;
import com.developer.schoolms.entity.ExamDate;
import com.developer.schoolms.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExamDateService {

    @Autowired
    private ExamDateDao examDateDao;

    public List<ExamDate> getSearchedExamDate(SearchCriteria searchCriteria) {
        return examDateDao.getSearchedExamDate(searchCriteria);
    }

    @Transactional(readOnly = false)
    public void saveExamDate(ExamDate examDate) {
        examDateDao.save(examDate);
    }

    @Transactional(readOnly = false)
    public void updateExamDate(ExamDate examDate) {
        examDateDao.update(examDate);
    }

    @Transactional(readOnly = false)
    public void deleteExamDate(Long id) {
        examDateDao.delete(id);
    }
}