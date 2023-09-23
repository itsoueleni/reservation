package com.example.demo.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Document(collection = "reservation")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Reservation {
    @Id
    private String reservationId;
    private String guestName;
    private String email;
    private Host host;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Accommodation accommodation;
    private ReservationStatus status;


    public void checkAvailability() {
        if (accommodation != null && accommodation.isAvailable()) {
            status = ReservationStatus.PENDING;
        } else {
            status = ReservationStatus.CANCELED;
        }
    }

    public void reject() {
        if (status != ReservationStatus.CANCELED && accommodation.isAvailable() && status == ReservationStatus.PENDING){
            status = ReservationStatus.REJECTED;
        }


    }
}