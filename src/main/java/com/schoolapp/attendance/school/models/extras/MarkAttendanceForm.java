package com.schoolapp.attendance.school.models.extras;

public class MarkAttendanceForm {
    private String studentId;

    private boolean attendanceStatus;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public boolean isPresent() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(boolean attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
