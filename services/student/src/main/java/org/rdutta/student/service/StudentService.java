package org.rdutta.student.service;

import jakarta.persistence.EntityManager;
import org.rdutta.student.dto.StudentRequest;
import org.rdutta.student.entity.Student;
import org.rdutta.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService implements I_StudentService{
    private final StudentRepository repository;
    private final EntityManager em;

    @Autowired
    public StudentService(StudentRepository repository, EntityManager em) {
        this.repository = repository;
        this.em = em;
    }


    @Override
    public UUID saveStudent(StudentRequest request) {
        return null;
    }

    @Override
    public String updateStudent(StudentRequest request) {
        return "";
    }

    @Override
    public String deleteStudent(UUID studentId) {
        return "";
    }

    @Override
    public Student getStudent(UUID studentId) {
        return null;
    }

    @Override
    public List<Student> getStudents() {
        return List.of();
    }

    @Override
    public List<Student> getActiveStudents(boolean isActive) {
        return List.of();
    }
}
