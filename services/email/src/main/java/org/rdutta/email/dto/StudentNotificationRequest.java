package org.rdutta.email.dto;

import java.util.Date;

public record StudentNotificationRequest(
        String firstName,
        String lastName,
        String email,
        Date admissionAt,
        boolean isActive
) {
}
