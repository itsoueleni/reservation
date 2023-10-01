package com.example.demo.reservation.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;

@Builder
@Data

@Document(collection = "reservation")

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Reservation {
    @ApiModelProperty(value = "Reservation ID")


    @Id
    private transient String id;

    private String guestName;

    private String email;

    private Host host;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Accommodation accommodation;

    private ReservationStatus status;


    public void checkAvailability() {
        if (accommodation != null && accommodation.isAvailable() && status == ReservationStatus.CREATED) {
            status = ReservationStatus.PENDING;
        } else {
            status = ReservationStatus.CANCELED;
        }
    }

    public void reject() {
        if (status != ReservationStatus.CANCELED && accommodation.isAvailable() && status == ReservationStatus.PENDING) {
            status = ReservationStatus.REJECTED;
        }
    }

    public void confirm() {
        if (status != ReservationStatus.CANCELED && accommodation.isAvailable() && status == ReservationStatus.PENDING) {
            status = ReservationStatus.CONFIRMED;
        }
    }

    public void placeReservation() {
        if (status != ReservationStatus.CANCELED && accommodation.isAvailable() && status == ReservationStatus.CONFIRMED) {
            status = ReservationStatus.COMPLETED;
        }
    }


    public String getId() {
        return id;
    }

}