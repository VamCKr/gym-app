package com.epam.gymapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineesTrainerUpdateRequest {

    private String traineeUserName;

    private List<String> trainersUserName;
}
