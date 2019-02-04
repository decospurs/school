package com.schoolapp.attendance.school.repositories;

import com.schoolapp.attendance.school.models.Attendance;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface AttendanceRepository extends CrudRepository<Attendance,String> {
    Attendance findByDate(Date date);
}
