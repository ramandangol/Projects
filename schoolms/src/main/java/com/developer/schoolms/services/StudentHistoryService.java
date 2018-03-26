package com.developer.schoolms.services;

import com.developer.schoolms.dao.StudentHistoryDao;
import com.developer.schoolms.entity.StudentHistory;
import com.developer.schoolms.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StudentHistoryService {

    @Autowired
    private StudentHistoryDao studentHistoryDao;

    public List<StudentHistory> getSearchedStudentHistory(SearchCriteria searchCriteria){
        return studentHistoryDao.getSearchedStudentHistory(searchCriteria);
    }

    @Transactional(readOnly = false)
    public void saveStudentHistoryDetail(StudentHistory studentHistory){
        studentHistoryDao.save(studentHistory);
    }

    @Transactional(readOnly = false)
    public void updateStudentHistoryDetail(StudentHistory studentHistory){
        studentHistoryDao.update(studentHistory);
    }

    @Transactional(readOnly = false)
    public void deleteStudentHistoryDetail(Long id){
        studentHistoryDao.delete(id);
    }

}