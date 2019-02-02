package com.schoolapp.attendance.school.repositories;

import com.schoolapp.attendance.school.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,String> {

    Student findBy_id(String id);

}
