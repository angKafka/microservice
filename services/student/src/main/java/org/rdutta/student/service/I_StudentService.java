package org.rdutta.student.service;

import org.rdutta.student.dto.StudentDTO;
import org.rdutta.student.dto.StudentRequest;
import org.rdutta.student.dto.StudentResponse;
import org.rdutta.student.entity.Student;

import java.util.List;
import java.util.UUID;

public interface I_StudentService {
    UUID saveStudent(StudentRequest request);
    String updateStudent(UUID studentId, StudentRequest request);
    String deleteStudent(UUID studentId);
    Student getStudent(UUID studentId);
    List<StudentResponse> getStudents();
    List<Student> getActiveStudents(boolean isActive);
}
