package com.developer.schoolms.services;

import com.developer.schoolms.dao.AttendanceDao;
import com.developer.schoolms.entity.Attendance;
import com.developer.schoolms.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AttendanceService {

    @Autowired
    private AttendanceDao attendanceDao;

    public List<Attendance> getSearchedAttendance(SearchCriteria searchCriteria){
        return attendanceDao.getSearchedAttendance(searchCriteria);
    }

    @Transactional(readOnly = false)
    public void saveAttendance(Attendance attendance){
        attendanceDao.save(attendance);
    }

    @Transactional(readOnly = false)
    public void saveAllAttendance(List<Attendance> attendanceList){
        attendanceDao.saveAll(attendanceList);
    }

    @Transactional(readOnly = false)
    public void updateAttendance(Attendance attendance){
        attendanceDao.update(attendance);
    }

    @Transactional(readOnly = false)
    public void deleteAttendance(Long id){
        attendanceDao.delete(id);
    }

}
