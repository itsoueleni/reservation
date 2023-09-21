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
@Document(collection ="reservation")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Reservation {
    @Id
    private String reservationId;
    private String guestName;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private ReservationStatus status;
    private List<Accommodation> accommodation;
}
