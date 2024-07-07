package org.rdutta.student.mapper;

import org.rdutta.student.dto.StudentRequest;
import org.rdutta.student.entity.Student;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class StudentMapper {
    public Student toStudent(StudentRequest request) {
        Student student = new Student();
        student.setFirstName(request.firstname());
        student.setLastName(request.lastname());
        student.setEmail(request.email());
        student.setAdmissionDate(Date.from(Instant.now()));
        student.setActive(request.isActive());
        return student;
    }
}
