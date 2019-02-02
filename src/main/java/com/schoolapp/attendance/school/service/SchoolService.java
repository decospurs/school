package com.schoolapp.attendance.school.service;

import com.schoolapp.attendance.school.dto.input.CreateStudentInputDTO;
import com.schoolapp.attendance.school.dto.input.MarkAttendanceInputDTO;
import com.schoolapp.attendance.school.dto.output.AttendanceResponseDTO;
import com.schoolapp.attendance.school.dto.output.StudentResponseDTO;

/**
 *
 * @author Ernest David
 */

public interface SchoolService {

    StudentResponseDTO createStudent(CreateStudentInputDTO createStudentInputDTO);

    AttendanceResponseDTO markAttendance(MarkAttendanceInputDTO dto);
}
