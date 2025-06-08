package com.veekesh.project.uber.uberApp.dto;

import com.veekesh.project.uber.uberApp.entities.Driver;
import com.veekesh.project.uber.uberApp.entities.Rider;
import com.veekesh.project.uber.uberApp.enums.PaymentMethod;
import com.veekesh.project.uber.uberApp.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {
    private Long id;
    private Point pickupLocation;
    private Point dropOffLocation;
    private LocalDateTime createdTime;
    private RiderDto rider;
    private DriverDto driver;
    private String otp;
    private PaymentMethod paymentMethod;
    private RideStatus rideStatus;
    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
