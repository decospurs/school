package com.schoolapp.attendance.school.dto.input;

import com.schoolapp.attendance.school.models.extras.MarkAttendanceForm;

import java.util.List;

public class MarkAttendanceInputDTO {
    List<MarkAttendanceForm> attendanceForms;

    public List<MarkAttendanceForm> getAttendanceForms() {
        return attendanceForms;
    }

    public void setAttendanceForms(List<MarkAttendanceForm> attendanceForms) {
        this.attendanceForms = attendanceForms;
    }
}

