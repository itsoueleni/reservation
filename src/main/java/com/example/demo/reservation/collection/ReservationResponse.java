package com.example.demo.reservation.collection;

import lombok.Data;

@Data
public class ReservationResponse {
    private String id;

    public ReservationResponse(String id) {
        this.id = id;
    }
}
