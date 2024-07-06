package org.rdutta.student.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.rdutta.student.dto.StudentRequest;
import org.rdutta.student.dto.StudentResponse;
import org.rdutta.student.entity.Student;
import org.rdutta.student.exception.StudentNotFound;
import org.rdutta.student.mapper.StudentMapper;
import org.rdutta.student.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class StudentService implements I_StudentService{
    private final Logger log = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository repository;
    private final EntityManager em;
    private final StudentMapper mapper;

    @Autowired
    public StudentService(StudentRepository repository, EntityManager em, StudentMapper mapper) {
        this.repository = repository;
        this.em = em;
        this.mapper = mapper;
    }


    @Transactional
    @Override
    public UUID saveStudent(StudentRequest request) {
        Student student = mapper.toStudent(request);
        repository.save(student);
        return student.getStudentId();
    }

    @Transactional
    @Override
    public String updateStudent(UUID studentId, StudentRequest request) {
        if(repository.existsById(studentId)) {
            Student student = mapper.toStudent(request);
            student.setFirstName(request.firstname());
            student.setLastName(request.lastname());
            student.setEmail(request.email());
            student.setActive(request.isActive());
            repository.save(student);
            log.info("Student {} updated", studentId);
        }

        return "Successfully updated student";
    }

    @Caching(evict = {@CacheEvict(value = "student", allEntries = true),
    @CacheEvict(value = "student", key = "#studentId")})
    @Transactional
    @Override
    public String deleteStudent(UUID studentId) {
        if(repository.existsById(studentId)) {
            repository.deleteById(studentId);
        }
        return "Successfully deleted student";
    }

    @Cacheable(value = "student", key = "#studentId")
    @Override
    public Student getStudent(UUID studentId) {
        Student student = repository.findById(studentId).orElseThrow(()-> new StudentNotFound("Student not found"));
        return student;
    }

    @Cacheable(value = "student")
    @Override
    public List<StudentResponse> getStudents() {
        List<Student> students = repository.findAll();
        List<StudentResponse> responses = new ArrayList<>();
        for (Student student : students) {
            responses.add(mapper.toStudentResponse(student));
        }
        return responses;
    }

    @Cacheable(value = "student", key = "#isActive")
    @Override
    public List<Student> getActiveStudents(boolean isActive) {
        TypedQuery<Student> query = em.createQuery("FROM Student s WHERE s.isActive = :isActive", Student.class);
        query.setParameter("isActive", isActive);
        List<Student> students = query.getResultList();
        return students;
    }
}
