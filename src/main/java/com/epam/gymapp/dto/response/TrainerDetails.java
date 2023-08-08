package com.epam.gymapp.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TrainerDetails {

    private String userName;
    private String firstName;
    private String lastName;
    private String specialization;
    private boolean isActive;
    private List<TraineeBasicDetails> traineeDetailsList;
}
