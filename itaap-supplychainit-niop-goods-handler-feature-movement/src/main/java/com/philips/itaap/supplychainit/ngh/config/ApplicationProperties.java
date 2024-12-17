package com.philips.itaap.supplychainit.ngh.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@SuppressWarnings("PMD.TooManyFields")
public class ApplicationProperties {

    @Value("${kafka.topic.read}")
    private String topicRead;

    @Value("${webclient.retry.maxretry}")
    private long maxRetry;

    @Value("${webclient.retry.delay}")
    private int delay;

    @Value("${webclient.response.timeout}")
    private int timeout;

    @Value("${webclient.memory-buffer-size}")
    private Integer memoryBufferSize;

    @Value("${email.url}")
    private String emailURL;

    @Value("${email.username}")
    private String emailUsername;

    @Value("${email.password}")
    private String emailPassword;

    @Value("${email.to}")
    private String emailTo;

    @Value("${email.cc}")
    private String emailCC;

    @Value("${email.subject}")
    private String emailSubject;

    @Value("${email.body}")
    private String emailBody;

    @Value("${spring.kafka.listener.ack-mode}")
    private ContainerProperties.AckMode kafkaAckMode;

    @Value("${niop.token.url}")
    private String tokenUrl;

    @Value("${niop.token.creds.username}")
    private String username;

    @Value("${niop.token.creds.password}")
    private String password;

    @Value("${niop.reciept-url}")
    private String niopRecieptUrl;

    @Value("${good-movement.token.url}")
    private String movementTokenUrl;

    @Value("${good-movement.token.creds.grant-type}")
    private String movementGrantType;

    @Value("${good-movement.token.creds.client-id}")
    private String movementClientId;

    @Value("${good-movement.token.creds.client-secret}")
    private String movementClientSecret;

    @Value("${good-movement.token.creds.scope}")
    private String movementScope;

    @Value("${good-movement.movement-url}")
    private String movementUrl;

}
