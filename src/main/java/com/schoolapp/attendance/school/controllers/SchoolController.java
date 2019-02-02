package com.schoolapp.attendance.school.controllers;

import com.schoolapp.attendance.school.dto.input.CreateStudentInputDTO;
import com.schoolapp.attendance.school.dto.input.MarkAttendanceInputDTO;
import com.schoolapp.attendance.school.dto.output.AttendanceResponseDTO;
import com.schoolapp.attendance.school.dto.output.StudentResponseDTO;
import com.schoolapp.attendance.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
class SchoolController extends Controller {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private SchoolService schoolService;

    @PostMapping("/student/create")
    public StudentResponseDTO createStudent(@RequestBody @Valid CreateStudentInputDTO createStudentInputDTO){
        StudentResponseDTO createStudent = schoolService.createStudent(createStudentInputDTO);
        updateHttpStatus(createStudent,response);
        return createStudent;
    }

    @PostMapping("attendance/create")
    public AttendanceResponseDTO markAttendance(@RequestBody @Valid MarkAttendanceInputDTO dto){
        AttendanceResponseDTO markAttendance = schoolService.markAttendance(dto);
        updateHttpStatus(markAttendance,response);
        return markAttendance;
    }


}
