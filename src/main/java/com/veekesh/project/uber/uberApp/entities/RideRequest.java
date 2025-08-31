package com.veekesh.project.uber.uberApp.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.veekesh.project.uber.uberApp.enums.PaymentMethod;
import com.veekesh.project.uber.uberApp.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(indexes = {
        @Index(name = "idx_ride_request_rider", columnList = "rider_id")
})
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "geometry(Point,4326)")
    private Point pickupLocation;

    @Column(columnDefinition = "geometry(Point,4326)")
    private Point dropOffLocation;

    @CreationTimestamp
    private LocalDateTime requestedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rider_id")
    private Rider rider;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideRequestStatus rideRequestStatus;

    private Double fare;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Point getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Point pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Point getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(Point dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public LocalDateTime getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(LocalDateTime requestedTime) {
        this.requestedTime = requestedTime;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
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
