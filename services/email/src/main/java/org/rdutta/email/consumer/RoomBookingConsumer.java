package org.rdutta.email.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rdutta.email.dto.StudentNotificationRequest;
import org.rdutta.email.model.*;
import org.rdutta.email.service.SendMail;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableKafka
@RequiredArgsConstructor
@Slf4j
public class RoomBookingConsumer {

    private final SendMail sendMail;
    private final TemplateEngine templateEngine;
    private final ObjectMapper objectMapper; // Inject ObjectMapper

    @KafkaListener(topics = "student-topic", groupId = "console-consumer-49264")
    public void consumeBookingNotification(@Payload String payload) {
        log.info("Received booking notification payload: {}", payload);

        try {
            // Deserialize JSON payload into StudentNotificationRequest
            StudentNotificationRequest request = objectMapper.readValue(payload, StudentNotificationRequest.class);

            // Create Thymeleaf context and add variables for template processing
            Context context = new Context();
            context.setVariable("firstName", request.firstName());
            context.setVariable("lastName", request.lastName());
            context.setVariable("email", request.email());

            // Process Thymeleaf template
            String contentValue = templateEngine.process("student-booking-message", context);

            // Prepare email message
            EmailTemplate emailTemplate = createEmailTemplate(request, contentValue);

            // Send email
            sendMail.sendMail(emailTemplate);

            log.info("Email sent successfully to {}", request.email());
        } catch (IOException e) {
            log.error("Error deserializing JSON or processing Thymeleaf template: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Error sending email notification: {}", e.getMessage());
            // Implement error handling, such as retry logic or sending notifications about failure.
        }
    }

    private EmailTemplate createEmailTemplate(StudentNotificationRequest request, String contentValue) {
        // Create recipients list
        EmailAddress emailAddress = new EmailAddress(request.email());
        ToRecipients recipient = new ToRecipients(emailAddress);
        List<ToRecipients> toRecipientsList = Collections.singletonList(recipient);

        // Create email message
        Message message = new Message(
                "Received booking notification request",
                new Body("Html", contentValue),
                toRecipientsList
        );

        return new EmailTemplate(message, "false"); // Adjust as per your EmailTemplate structure
    }
}
