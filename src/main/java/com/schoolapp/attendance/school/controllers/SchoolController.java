package com.schoolapp.attendance.school.controllers;

import com.schoolapp.attendance.school.dto.input.CreateStudentInputDTO;
import com.schoolapp.attendance.school.dto.input.MarkAttendanceInputDTO;
import com.schoolapp.attendance.school.dto.output.AttendanceResponseDTO;
import com.schoolapp.attendance.school.dto.output.StudentListResponseDTO;
import com.schoolapp.attendance.school.dto.output.StudentResponseDTO;
import com.schoolapp.attendance.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

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

    @PostMapping("/attendance/create")
    public AttendanceResponseDTO markAttendance(@RequestBody @Valid MarkAttendanceInputDTO dto){
        AttendanceResponseDTO markAttendance = schoolService.markAttendance(dto);
        updateHttpStatus(markAttendance,response);
        return markAttendance;
    }

    @GetMapping("/students")
    public StudentListResponseDTO fetchStudents(@PageableDefault(value = 20) Pageable pageable){
        StudentListResponseDTO fetchStudents = schoolService.fetchStudents(pageable);
        updateHttpStatus(fetchStudents,response);
        return fetchStudents;
    }

    @GetMapping("/attendance/{date}")
    public AttendanceResponseDTO fetchAttendance(@PathVariable(name= "date")Date attendanceDate){
        AttendanceResponseDTO attendanceResponseDTO = schoolService.fetchAttendance(attendanceDate);
        updateHttpStatus(attendanceResponseDTO,response);
        return attendanceResponseDTO;
    }


}
