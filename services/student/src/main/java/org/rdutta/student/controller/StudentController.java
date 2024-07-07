package org.rdutta.student.controller;
import org.rdutta.student.dto.StudentRequest;
import org.rdutta.student.dto.StudentResponse;
import org.rdutta.student.entity.Student;
import org.rdutta.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<UUID> saveStudent(@RequestBody StudentRequest request) {
        return ResponseEntity.ok(studentService.saveStudent(request));
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable UUID studentId, @RequestBody StudentRequest request) {
        return ResponseEntity.ok(studentService.updateStudent(studentId, request));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(studentService.deleteStudent(studentId));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(studentService.getStudent(studentId));
    }

    @GetMapping("/")
    public ResponseEntity<List<StudentResponse>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @GetMapping("active/{isActive}")
    public ResponseEntity<List<Student>> getActiveStudents(@PathVariable("isActive") boolean isActive) {
        return ResponseEntity.ok(studentService.getActiveStudents(isActive));
    }
}
