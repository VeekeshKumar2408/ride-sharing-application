package com.veekesh.project.uber.uberApp.dto;

import com.veekesh.project.uber.uberApp.entities.Rider;
import com.veekesh.project.uber.uberApp.enums.PaymentMethod;
import com.veekesh.project.uber.uberApp.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideRequestDto {
    private Long id;
    private PointDto pickupLocation;
    private PointDto dropOffLocation;
    private LocalDateTime requestedTime;
    private RiderDto rider;
    private PaymentMethod paymentMethod;
    private RideRequestStatus rideRequestStatus;
    private Double fare;

    public RideRequestDto() {
    }

    public RideRequestDto(Long id, PointDto pickupLocation, PointDto dropOffLocation, LocalDateTime requestedTime, RiderDto rider, PaymentMethod paymentMethod, RideRequestStatus rideRequestStatus) {
        this.id = id;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.requestedTime = requestedTime;
        this.rider = rider;
        this.paymentMethod = paymentMethod;
        this.rideRequestStatus = rideRequestStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PointDto getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(PointDto pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public PointDto getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(PointDto dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public LocalDateTime getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(LocalDateTime requestedTime) {
        this.requestedTime = requestedTime;
    }

    public RiderDto getRider() {
        return rider;
    }

    public void setRider(RiderDto rider) {
        this.rider = rider;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public RideRequestStatus getRideRequestStatus() {
        return rideRequestStatus;
    }

    public void setRideRequestStatus(RideRequestStatus rideRequestStatus) {
        this.rideRequestStatus = rideRequestStatus;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }
}
