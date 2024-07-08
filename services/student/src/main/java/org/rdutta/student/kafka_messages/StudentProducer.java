package org.rdutta.student.kafka_messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentProducer {
    private final KafkaTemplate<String, StudentNotificationRequest> kafkaTemplate;

    public void sendNotification(StudentNotificationRequest notification) {
        log.info("sending notification to student {}", notification);
        Message<StudentNotificationRequest> message = MessageBuilder.withPayload(notification)
                .setHeader(KafkaHeaders.TOPIC, "student-topic")
                .build();

        kafkaTemplate.send(message);
    }
}
