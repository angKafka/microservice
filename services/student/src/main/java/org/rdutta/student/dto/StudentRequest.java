package org.rdutta.student.dto;

import java.util.Date;

public record StudentRequest(
        String firstname,
        String lastname,
        String email,
        Date admissionAt,
        boolean isActive
) {
}
