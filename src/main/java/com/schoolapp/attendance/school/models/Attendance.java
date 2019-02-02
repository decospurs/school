package com.schoolapp.attendance.school.models;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public class Attendance {
    @Id
    private String id;

    private Date date;

    private List<Student> presentStudents;

    private List<Student> absentStudents;

    private int numberOfPresentStudents;

    private int numberOfAbsentStudents;

    public Attendance() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Student> getPresentStudents() {
        return presentStudents;
    }

    public void setPresentStudents(List<Student> presentStudents) {
        this.presentStudents = presentStudents;
    }

    public List<Student> getAbsentStudents() {
        return absentStudents;
    }

    public void setAbsentStudents(List<Student> absentStudents) {
        this.absentStudents = absentStudents;
    }

    public int getNumberOfPresentStudents() {
        return numberOfPresentStudents;
    }

    public void setNumberOfPresentStudents(int numberOfPresentStudents) {
        this.numberOfPresentStudents = numberOfPresentStudents;
    }

    public int getNumberOfAbsentStudents() {
        return numberOfAbsentStudents;
    }

    public void setNumberOfAbsentStudents(int numberOfAbsentStudents) {
        this.numberOfAbsentStudents = numberOfAbsentStudents;
    }
}
