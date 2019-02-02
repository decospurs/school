package com.schoolapp.attendance.school.dto.output;

import com.schoolapp.attendance.school.dto.enums.Status;
import com.schoolapp.attendance.school.models.Student;

public class StudentResponseDTO extends StandardResponseDTO {
   private Student student;

    public StudentResponseDTO(Status status) {
        super(status);
    }

    public StudentResponseDTO(Status status, Student student) {
        super(status);
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
