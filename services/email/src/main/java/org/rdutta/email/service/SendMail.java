package org.rdutta.email.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.rdutta.email.exception.EmailException;
import org.rdutta.email.model.EmailTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class SendMail implements MsGraphService {
    private final RestTemplate restTemplate;
    private final Logger log = LoggerFactory.getLogger(SendMail.class);
    private final String graphApiUrl = "https://graph.microsoft.com/v1.0/me/sendMail";

    @Autowired
    public SendMail(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "msgraph-email", fallbackMethod = "methodFallback")
    @Override
    public Object sendMail(EmailTemplate message) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer EwBwA8l6BAAUbDba3x2OMJElkF7gJ4z/VbCPEz0AAaFefAugqL6VVfWoS3wZFTyy5HXCHag4psK4CiRgMHDpodiB+Bcdd546qYYsMfgTF+5Rq/eF1JCKNbPHMXqtCbQLa0QBPq9cRmssBFdX5s9xJqfN7u8xebZTR7otA/a/aUvJ7830Zn3M+CVgE0XYcR+nFFDLH7hs0HMgPzS8e+NAfREGoCrlgsEz7+znV3pAZF784CTavJLHUlv0sZz2YK7TPsc9Y5x4kqta1CQz+/pZq+0WYEEBfQLQ92bJigSyNzjzFSsTzKSc90nOTmWV7hz1xfaM6gpGaePUoeXPHtw8woazBRuhA0DbMgiHVrGNsUy/5mgOaDL0R141PcD8TWIDZgAACMB2JiIoVds/QAI3setDjpo3eqNvsmaeHOzJNr1hciM/YkNKza29XNVRCZrZFHSaJmmCXiLZ+OZX5eXeKLb9mll41Ea6PHHzRzlq8gOaAu2sAEDMwg8k0o0j2Kd6MIb4531Cb50iCecyPmDNR4y3NZZJyEFkGifcntIntlm1TXJWqCc80he7vrP93bvebC9wKg7g7kcbeuMn1258xJ3CDdyx4NNDcjcomq7xHNovczhlJc5aB8dJToZdPcnSN57GRFBoksFphxOt1cGzzM4N42Nz6n6w8d+tIMYnE+pf/KUMY0e0nw/+8mAP0fNJ4ik70AJslOGXpw48EzBUFohGaGyVjedIOJ2w09lTMklT+02e14UZiYHK4QeE+zTG0JXoiDWsPirVC+8VENqO/lGjP4GWU2FVKJoihDbvKQzIKURAe9KDgAjl5MEmZUvW7T0kYUk314oBXH5WLeenUqpELv1zUk/ozHqf7ujWa2hEW1ltyMk2R1aGEXa25JAk3m16jV/1V0KU5iRtQD1GNdQ51XFcx2nHL7lObNZYhh8k/h55rRRjj4ILt/Hh/C/FZyWTUA0Vpw9Bkdhy5CvCVvkL3fLfsKVJrQjbEgVB1K7OrtwXgremc832Y3jfFH4E4PxBTo90u0kwN76kQEz4jBSXiPAds/cZX1S9Xj3kQHmlItm2FKBrOPIZmo3V9o5AgwVlWRW6asOpC9b1PpX72Z+CnHTkk5yzWGMBaWrUIavBd+MBpf2klocpYI70JaGylHOvThAxtU/VE6ILAbZ6Ag==");

        ObjectMapper mapper = new ObjectMapper();
        String emailBody = mapper.writeValueAsString(message);

        HttpEntity<String> entity = new HttpEntity<>(emailBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                graphApiUrl,
                HttpMethod.POST,
                entity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.ACCEPTED) {
            System.out.println("Email sent successfully!");
        } else {
            System.err.println("Failed to send email. Status code: " + response.getStatusCode());
        }

        return "success";
    }


    public String methodFallback(EmailException e){
        log.warn("Email MSGraph Token Expire"+e.getMessage());
        return "Email MSGraph Token Expire";
    }
}
