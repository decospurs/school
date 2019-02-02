package com.schoolapp.attendance.school.dto.output;

import com.schoolapp.attendance.school.dto.enums.Status;
import com.schoolapp.attendance.school.models.Student;

import java.util.List;

public class StudentListResponseDTO extends  StandardResponseDTO {
    List<Student> studentList;

    public StudentListResponseDTO(Status status, List<Student> studentList) {
        super(status);
        this.studentList = studentList;
    }

    public StudentListResponseDTO(Status status) {
        super(status);
    }

    public StudentListResponseDTO() {
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
