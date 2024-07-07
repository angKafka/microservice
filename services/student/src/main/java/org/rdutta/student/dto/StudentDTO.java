package org.rdutta.student.dto;

import java.util.Date;
import java.util.UUID;

public class StudentDTO {
    private UUID studentId;
    private String firstName;
    private String lastName;
    private String email;
    private Date admissionDate;
    private boolean isActive;

    public StudentDTO() {}

    public StudentDTO(String firstName, String lastName, String email, Date admissionDate, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.admissionDate = admissionDate;
        this.isActive = isActive;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
