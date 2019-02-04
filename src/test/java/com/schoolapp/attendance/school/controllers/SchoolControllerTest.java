package com.schoolapp.attendance.school.controllers;


import com.google.gson.Gson;
import com.schoolapp.attendance.school.dto.enums.Status;
import com.schoolapp.attendance.school.dto.input.CreateStudentInputDTO;
import com.schoolapp.attendance.school.dto.input.FetchAttendanceInputDTO;
import com.schoolapp.attendance.school.dto.input.MarkAttendanceInputDTO;
import com.schoolapp.attendance.school.dto.output.AttendanceResponseDTO;
import com.schoolapp.attendance.school.dto.output.StudentResponseDTO;
import com.schoolapp.attendance.school.models.extras.MarkAttendanceForm;
import com.schoolapp.attendance.school.repositories.AttendanceRepository;
import com.schoolapp.attendance.school.repositories.StudentRepository;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SchoolControllerTest {

    protected static TestRestTemplate restTemplate = new TestRestTemplate();

    protected static HttpHeaders headers;

    private CreateStudentInputDTO createStudentInputDTO;

    private MarkAttendanceInputDTO markAttendanceInputDTO;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @BeforeClass
    public static void configuration() {
        System.setProperty("mongodb.host", "localhost");
        System.setProperty("mongodb.port", "27017");
    }

    private StudentResponseDTO CreateStudent(String firstName,String lastName){
        CreateStudentInputDTO createStudentInputDTO = new CreateStudentInputDTO();
        createStudentInputDTO.setFirstName(firstName);
        createStudentInputDTO.setLastName(lastName);
        HttpEntity entity = new HttpEntity<>(createStudentInputDTO);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/v1/student/create",entity,String.class);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());

        StudentResponseDTO createStudentResponse = new Gson().fromJson(responseEntity.getBody(),StudentResponseDTO.class);

        return  createStudentResponse;
    }

    private void assertNotNullForStudentFields(StudentResponseDTO student) {
        assertNotNull(student.getStudent());
        assertNotNull(student.getStudent().get_id());
        assertNotNull(student.getStudent().getFirstName());
        assertNotNull(student.getStudent().getLastName());
    }


    @Test
    public void whenCreateStudentReturnsSuccessful(){
       StudentResponseDTO student1 = CreateStudent("David","Ernest");

        assertNotNullForStudentFields(student1);

        assertEquals("David",student1.getStudent().getFirstName());
        assertEquals("Ernest",student1.getStudent().getLastName());
    }




    @Test
    public void whenMarkAttendanceReturnsSuccessFul(){
       AttendanceResponseDTO attendance = markAttendance();
        assertEquals(Status.SUCCESS,attendance.getStatus());
        assertNotNull(attendance.getAttendance());
        assertNotNull(attendance.getAttendance().getId());
        assertNotNull(attendance.getAttendance().getDate());
        assertNotNull(attendance.getAttendance().getAbsentStudents());
//        assertNotNull(attendance.getAttendance().getPresentStudents());
//        assertEquals(attendance.getAttendance().getPresentStudents().size(),attendance.getAttendance().getNumberOfPresentStudents());
//        assertEquals(attendance.getAttendance().getPresentStudents().size(),attendance.getAttendance().getNumberOfAbsentStudents());

  //todo : rest template returning only false. figure that out

    }

//    @Test
//    public void whenFetchAttendanceByDateReturnsSuccess(){
//        AttendanceResponseDTO attendance = markAttendance();
//        Date date = attendance.getAttendance().getDate();
//        FetchAttendanceInputDTO fetchAttendanceInputDTO = new FetchAttendanceInputDTO();
//        fetchAttendanceInputDTO.setDate(date);
//        HttpEntity entity = new HttpEntity<>(fetchAttendanceInputDTO);
//        ResponseEntity<Date> responseEntity =  restTemplate.exchange("http://localhost:8080/v1/attendance", HttpMethod.GET, entity ,Date.class);
//        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
//
//    }

    //todo : find how to parse a date to the gregorian format #cdate . thats what is making this last test to fail the db is returning (Mon Feb 04 10:25:13 WAT 2019) but i need (2019-02-04T10:25:13.347+0100)
    private AttendanceResponseDTO markAttendance(){
        StudentResponseDTO student1 = CreateStudent("David","Ernest");
        assertNotNullForStudentFields(student1);
        assertEquals("David",student1.getStudent().getFirstName());
        assertEquals("Ernest",student1.getStudent().getLastName());

        StudentResponseDTO student2 = CreateStudent("David","Cameron");
        assertNotNullForStudentFields(student2);
        assertEquals("David",student2.getStudent().getFirstName());
        assertEquals("Cameron",student2.getStudent().getLastName());

        StudentResponseDTO student3 = CreateStudent("Justin","Trudeau");
        assertNotNullForStudentFields(student3);
        assertEquals("Justin",student3.getStudent().getFirstName());
        assertEquals("Trudeau",student3.getStudent().getLastName());

        StudentResponseDTO student4 = CreateStudent("Emmanuel","Macron");
        assertNotNullForStudentFields(student4);
        assertEquals("Emmanuel",student4.getStudent().getFirstName());
        assertEquals("Macron",student4.getStudent().getLastName());

        String student1Id = student1.getStudent().get_id();
        String student2Id = student2.getStudent().get_id();
        String student3Id = student3.getStudent().get_id();
        String student4Id = student4.getStudent().get_id();

        MarkAttendanceForm student1AttendanceForm = new MarkAttendanceForm();
        student1AttendanceForm.setStudentId(student1Id);
        student1AttendanceForm.setAttendanceStatus(isPresent());

        MarkAttendanceForm student2AttendanceForm = new MarkAttendanceForm();
        student2AttendanceForm.setStudentId(student2Id);
        student2AttendanceForm.setAttendanceStatus(isAbsent());

        MarkAttendanceForm student3AttendanceForm = new MarkAttendanceForm();
        student3AttendanceForm.setStudentId(student3Id);
        student3AttendanceForm.setAttendanceStatus(isPresent());

        MarkAttendanceForm student4AttendanceForm = new MarkAttendanceForm();
        student4AttendanceForm.setStudentId(student4Id);
        student4AttendanceForm.setAttendanceStatus(isAbsent());



        List<MarkAttendanceForm> attendanceForms = new ArrayList<MarkAttendanceForm>();
        attendanceForms.add(student1AttendanceForm);
        attendanceForms.add(student2AttendanceForm);
        attendanceForms.add(student3AttendanceForm);
        attendanceForms.add(student4AttendanceForm);

        MarkAttendanceInputDTO markAttendanceInputDTO = new MarkAttendanceInputDTO();
        markAttendanceInputDTO.setAttendanceForms(attendanceForms);

        HttpEntity entity = new HttpEntity<>(markAttendanceInputDTO);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/v1/attendance/create",entity,String.class);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());

        AttendanceResponseDTO attendance = new Gson().fromJson(responseEntity.getBody(),AttendanceResponseDTO.class);
        return attendance;
    }

    private boolean isPresent() {
        return true;
    }

    private boolean isAbsent() {
        return false;
    }

    @After
   public void cleanUp(){
       attendanceRepository.deleteAll();
       studentRepository.deleteAll();
   }


}
