package org.rdutta.hostel.clone;

import java.util.Date;
import java.util.UUID;

public class StudentDTO {
    private UUID studentId;
    private String firstName;
    private String lastName;
    private String email;
    private Date admissionAt;
    private boolean isActive;

    public StudentDTO() {}

    public StudentDTO(String firstName, String lastName, String email, Date admissionAt, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.admissionAt = admissionAt;
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

    public Date getAdmissionAt() {
        return admissionAt;
    }

    public void setAdmissionAt(Date admissionAt) {
        this.admissionAt = admissionAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
