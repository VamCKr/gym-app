package com.epam.gymapp.kafka;

import com.epam.gymapp.dto.report.GymReportDTO;
import com.epam.gymapp.model.StringConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReportProducer {

    private final KafkaTemplate<String, GymReportDTO> gymReportDTOKafkaTemplate;

    public void sendGymReport(GymReportDTO gymReportDTO) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), "sendGymReport", this.getClass().getName(), gymReportDTO);
        gymReportDTOKafkaTemplate.send("report",gymReportDTO);
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), "sendGymReport");
    }

}
