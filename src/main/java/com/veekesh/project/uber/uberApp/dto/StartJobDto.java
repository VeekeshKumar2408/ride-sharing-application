package com.veekesh.project.uber.uberApp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StartJobDto {

    private LocalDateTime punchInTime;
    private PointDto currentLocation;
}
