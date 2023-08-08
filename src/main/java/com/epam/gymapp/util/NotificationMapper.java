package com.epam.gymapp.util;

import com.epam.gymapp.dto.CredentialsDetails;
import com.epam.gymapp.dto.report.NotificationDTO;
import com.epam.gymapp.dto.report.TrainingMailDTO;
import com.epam.gymapp.dto.response.TraineeDetails;
import com.epam.gymapp.dto.response.TrainerDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationMapper {

    private static final String USERNAME = "Username";
    private NotificationMapper(){}
        public static NotificationDTO getNotificationDTO(CredentialsDetails credentialsDetails, String fromEmail){
            Map<String, String> bodyMap = new HashMap<>();
            bodyMap.put(USERNAME, credentialsDetails.getUserName());
            bodyMap.put("Password", credentialsDetails.getPassword());

            return NotificationDTO.builder()
                    .emailType("register")
                    .toEmails(List.of(fromEmail))
                    .ccEmails(new ArrayList<>())
                    .bodyParameters(bodyMap)
                    .build();
        }

    public static NotificationDTO getNotificationDTO(TraineeDetails traineeDetails,String fromEmail){
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put(USERNAME, traineeDetails.getUserName());
        bodyMap.put("FirstName", traineeDetails.getFirstName());
        bodyMap.put("LastName", traineeDetails.getLastName());
        bodyMap.put("Address", traineeDetails.getAddress());

        return NotificationDTO.builder()
                .emailType("update")
                .toEmails(List.of(fromEmail))
                .ccEmails(new ArrayList<>())
                .bodyParameters(bodyMap)
                .build();
    }

    public static NotificationDTO getNotificationDTO(TrainerDetails trainerDetails, String fromEmail){
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put(USERNAME, trainerDetails.getUserName());
        bodyMap.put("FirstName", trainerDetails.getFirstName());
        bodyMap.put("LastName", trainerDetails.getLastName());

        return NotificationDTO.builder()
                .emailType("update")
                .toEmails(List.of(fromEmail))
                .ccEmails(new ArrayList<>())
                .bodyParameters(bodyMap)
                .build();
    }

    public static NotificationDTO getNotificationDTO(TrainingMailDTO trainingMailDTO){
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("TrainingName", trainingMailDTO.getTrainingName());
        bodyMap.put("TrainerName", trainingMailDTO.getTrainerName());
        bodyMap.put("TraineeName", trainingMailDTO.getTraineeName());

        return NotificationDTO.builder()
                .emailType("training")
                .toEmails(List.of(trainingMailDTO.getTraineeEmail(), trainingMailDTO.getTrainerEmail()))
                .ccEmails(new ArrayList<>())
                .bodyParameters(bodyMap)
                .build();
    }


}
