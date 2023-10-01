package com.example.demo.reservation.collection;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data

public class ReservationDTO {
    private String guestName;
    private String email;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
