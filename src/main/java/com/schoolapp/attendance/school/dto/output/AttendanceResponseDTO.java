package com.schoolapp.attendance.school.dto.output;

import com.schoolapp.attendance.school.dto.enums.Status;
import com.schoolapp.attendance.school.models.Attendance;
import com.schoolapp.attendance.school.models.Student;

import java.util.List;

public class AttendanceResponseDTO extends StandardResponseDTO {
    Attendance attendance;

    public AttendanceResponseDTO() {
    }

    public AttendanceResponseDTO(Status status) {
        super(status);
    }

    public AttendanceResponseDTO(Status status, Attendance attendance) {
        super(status);
        this.attendance = attendance;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }


}
