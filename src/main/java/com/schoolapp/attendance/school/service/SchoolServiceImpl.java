package com.schoolapp.attendance.school.service;

import com.schoolapp.attendance.school.dto.enums.Status;
import com.schoolapp.attendance.school.dto.input.CreateStudentInputDTO;
import com.schoolapp.attendance.school.dto.input.MarkAttendanceInputDTO;
import com.schoolapp.attendance.school.dto.output.AttendanceResponseDTO;
import com.schoolapp.attendance.school.dto.output.StudentListResponseDTO;
import com.schoolapp.attendance.school.dto.output.StudentResponseDTO;
import com.schoolapp.attendance.school.models.Attendance;
import com.schoolapp.attendance.school.models.Student;
import com.schoolapp.attendance.school.models.extras.MarkAttendanceForm;
import com.schoolapp.attendance.school.repositories.AttendanceRepository;
import com.schoolapp.attendance.school.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("schoolService")
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    private static final String SERVICE_NAME = "SchoolService";


    void logError(String serviceName, String methodName, Exception ex) {
        ex.printStackTrace(); //fluentd, logstash
    }

    @Override
    public StudentResponseDTO createStudent(CreateStudentInputDTO dto) {
        try{
            Student newStudent = createNewStudent(dto);
            studentRepository.save(newStudent);

            return  new StudentResponseDTO(Status.SUCCESS,newStudent);
        } catch (Exception e){
            logError(SERVICE_NAME, "createStudent", e);
            return new StudentResponseDTO(Status.INTERNAL_ERROR);
        }
    }

    @Override
    public AttendanceResponseDTO markAttendance(MarkAttendanceInputDTO dto) {
        try{
            Attendance attendance = new Attendance();
            attendance.setDate(new Date());

            List<MarkAttendanceForm> attendanceForms = dto.getAttendanceForms();
            for (MarkAttendanceForm attendanceForm : attendanceForms){
                //todo : implement student validation
                if (attendanceForm.isPresent()){
                    String studentId = attendanceForm.getStudentId();
                    Student student = studentRepository.findBy_id(studentId);
                    List<Student> presentStudents = getExisting(attendance.getPresentStudents());
                    presentStudents.add(student);
                    attendance.setPresentStudents(presentStudents);
                    int numberOfPresentStudent = attendance.getPresentStudents().size();
                    attendance.setNumberOfPresentStudents(numberOfPresentStudent);
                    attendanceRepository.save(attendance);
                }else if (isAbsent(attendanceForm)){
                    String studentId = attendanceForm.getStudentId();
                    Student student = studentRepository.findBy_id(studentId);
                    List<Student> absentStudents = getExisting(attendance.getAbsentStudents());
                    absentStudents.add(student);
                    attendance.setAbsentStudents(absentStudents);
                    int numberOfAbsentStudents = attendance.getAbsentStudents().size();
                    attendance.setNumberOfAbsentStudents(numberOfAbsentStudents);
                    attendanceRepository.save(attendance);
                }
            }

            return new AttendanceResponseDTO(Status.SUCCESS,attendance);

        } catch (Exception e){
            logError(SERVICE_NAME, "markAttendance", e);
            return new AttendanceResponseDTO(Status.INTERNAL_ERROR);
        }
    }

    private boolean isAbsent(MarkAttendanceForm form){
        return form.isPresent() == false;
    }

    @Override
    public StudentListResponseDTO fetchStudents(Pageable pageable) {
        List<Student> studentList = (List<Student>) studentRepository.findAll();
        return new StudentListResponseDTO(Status.SUCCESS,studentList);
    }

    @Override
    public AttendanceResponseDTO fetchAttendance(Date attendanceDate) {
        Attendance attendance = attendanceRepository.findByDate(attendanceDate);
        if (attendance == null){
            return new AttendanceResponseDTO(Status.NOT_FOUND);
        }
        return  new AttendanceResponseDTO(Status.SUCCESS,attendance);

    }

    private <T> List<T> getExisting(List<T> t) {
        return t == null ? new ArrayList() : t;
    }

    private Student createNewStudent(CreateStudentInputDTO dto) {
        return new Student(dto.getFirstName(),dto.getLastName());
    }
}
