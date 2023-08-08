package com.epam.gymapp.kafka;

import com.epam.gymapp.dto.report.NotificationDTO;
import com.epam.gymapp.model.StringConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationProducer {
    public static final String NOTIFICATION = "notification";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendCredentialsNotification(NotificationDTO notificationDTO) throws JsonProcessingException {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), "sendCredentialsNotification", this.getClass().getName(), notificationDTO);
        kafkaTemplate.send(NOTIFICATION, "credentials",new ObjectMapper().writeValueAsString(notificationDTO));
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), "sendCredentialsNotification");
    }

    public void sendUpdateNotification(NotificationDTO notificationDTO) throws JsonProcessingException {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), "sendUpdateNotification", this.getClass().getName(), notificationDTO);
        kafkaTemplate.send(NOTIFICATION,"update",new ObjectMapper().writeValueAsString(notificationDTO));
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), "sendUpdateNotification");
    }

    public void sendTrainingNotification(NotificationDTO notificationDTO) throws JsonProcessingException {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), "sendTrainingNotification", this.getClass().getName(), notificationDTO);
        kafkaTemplate.send(NOTIFICATION,"training",new ObjectMapper().writeValueAsString(notificationDTO));
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), "sendTrainingNotification");
    }
}
