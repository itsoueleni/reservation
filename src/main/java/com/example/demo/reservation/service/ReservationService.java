package com.example.demo.reservation.service;

import com.example.demo.reservation.collection.Reservation;

import java.util.List;

public interface ReservationService {

    String create(Reservation reservation);

    List<Reservation> getReservationByGuestName(String name);

    String delete(String id);

    boolean updateReservationStatus(String reservationId);

    boolean rejectReservation(String id);

    boolean confirmReservation(String id);

    boolean placeReservation(String id);
}
