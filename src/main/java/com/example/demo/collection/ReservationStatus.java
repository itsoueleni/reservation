package com.example.demo.collection;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ReservationStatus {
    CREATED("Created"),
    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    REJECTED("Rejected"),
    CANCELED("Canceled"),
    COMPLETED("Completed");
    private final String status;
    ReservationStatus(String status) {
        this.status= status;
    }
}
