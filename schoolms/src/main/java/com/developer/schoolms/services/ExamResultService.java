package com.developer.schoolms.services;


import com.developer.schoolms.dao.ExamResultDao;
import com.developer.schoolms.entity.ExamResult;
import com.developer.schoolms.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExamResultService {

    @Autowired
    private ExamResultDao examResultDao;

    public List<ExamResult> getSearchedExamResult(SearchCriteria searchCriteria) {
        return examResultDao.getSearchedExamResult(searchCriteria);
    }

    @Transactional(readOnly = false)
    public void saveExamResult(ExamResult examResult) {
        examResultDao.save(examResult);
    }

    @Transactional(readOnly = false)
    public void updateExamResult(ExamResult examResult) {
        examResultDao.update(examResult);
    }

    @Transactional(readOnly = false)
    public void deleteExamResult(Long id) {
        examResultDao.delete(id);
    }
}