package com.example.demo.service;

import com.example.demo.collection.Reservation;

import java.util.List;

public interface ReservationService {

    String create(Reservation reservation);

    List<Reservation> getReservationByGuestName(String name);

    void delete(String id);

    boolean updateReservationStatus(String reservationId);

    boolean rejectReservation(String id);
}
