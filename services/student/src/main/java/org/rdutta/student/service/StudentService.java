package org.rdutta.student.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.rdutta.student.client.HostelClient;
import org.rdutta.student.dto.RoomBedRequest;
import org.rdutta.student.dto.StudentRequest;
import org.rdutta.student.dto.StudentResponse;
import org.rdutta.student.entity.Student;
import org.rdutta.student.exception.HostelWorkflowException;
import org.rdutta.student.exception.StudentNotFound;
import org.rdutta.student.kafka_messages.StudentNotificationRequest;
import org.rdutta.student.kafka_messages.StudentProducer;
import org.rdutta.student.mapper.StudentMapper;
import org.rdutta.student.repository.StudentRepository;
import org.rdutta.student.utility.StudentSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;


@Service
public class StudentService implements I_StudentService{
    private final Logger log = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository repository;
    private final EntityManager em;
    private final StudentMapper mapper;
    private final StudentProducer studentProducer;
    private final HostelClient hostelClient;
    @Autowired
    public StudentService(StudentRepository repository, EntityManager em, StudentMapper mapper, StudentProducer studentProducer, HostelClient hostelClient) {

        this.repository = repository;
        this.em = em;
        this.mapper = mapper;
        this.studentProducer = studentProducer;
        this.hostelClient = hostelClient;
    }


    @Transactional
    @Override
    public UUID saveStudent(StudentRequest request) {
        Student student = mapper.toStudent(request);
        repository.save(student);
        studentProducer.sendNotification(
                new StudentNotificationRequest(
                        request.firstname(),
                        request.lastname(),
                        request.email(),
                        request.admissionAt(),
                        request.isActive()
                )
        );
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
            StudentResponse response = mapper.toStudentResponse(student);
            responses.add(response);
        }

        responses.sort(new StudentSort());
        return responses;
    }

    @Cacheable(value = "student", key = "#isActive")
    @Override
    public List<Student> getActiveStudents(boolean isActive) {
        TypedQuery<Student> query = em.createQuery("FROM Student s WHERE s.isActive = :isActive ORDER BY s.firstName ASC", Student.class);
        query.setParameter("isActive", isActive);
        List<Student> students = query.getResultList();
        return students;
    }

    @Override
    @Transactional
    public String hostelWorkflow(UUID room_id, UUID student_id, int leftBed, int rightBed) {
        try {
            // Validate bed options
            Assert.isTrue(leftBed == 1 || rightBed == 1, "Either leftBed or rightBed must be chosen.");

            // Choose bed for the student
            var roomBedRequest = new RoomBedRequest(leftBed, rightBed);
            hostelClient.chooseBed(room_id, roomBedRequest);

            // Retrieve student information
            Student student = repository.findById(student_id)
                    .orElseThrow(() -> new StudentNotFound("Student not found"));

            // Send notification
            sendStudentNotification(student);

            return "Successfully hostel workflow raised";
        } catch (Exception e) {
            // Handle specific exceptions or log general errors
            log.error("Error processing hostel workflow: {}", e.getMessage());
            throw new HostelWorkflowException("Failed to process hostel workflow", e);
        }
    }

    private void sendStudentNotification(Student student) {
        studentProducer.sendNotification(
                new StudentNotificationRequest(
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getAdmissionDate(),
                        student.isActive()
                )
        );
    }
}

