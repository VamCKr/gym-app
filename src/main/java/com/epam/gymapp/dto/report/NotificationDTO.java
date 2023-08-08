package com.epam.gymapp.dto.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {


    private List<String> ccEmails;

    private List<String> toEmails;

    private Map<String, String> bodyParameters;

    private String emailType;

}
