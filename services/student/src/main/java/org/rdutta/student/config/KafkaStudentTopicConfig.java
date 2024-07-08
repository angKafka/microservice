package org.rdutta.student.config;



import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaStudentTopicConfig {

    @Bean
    public NewTopic studentTopic() {
        return TopicBuilder.name("student-topic")
                .build();
    }
}
