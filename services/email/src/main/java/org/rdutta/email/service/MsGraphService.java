package org.rdutta.email.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.rdutta.email.model.EmailTemplate;
import org.rdutta.email.model.Message;

@FunctionalInterface
public interface MsGraphService {
    Object sendMail(EmailTemplate message) throws JsonProcessingException;
}
