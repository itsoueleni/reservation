package com.example.demo.reservation.service.impl;

import com.example.demo.reservation.collection.Reservation;
import com.example.demo.reservation.collection.ReservationStatus;
import com.example.demo.reservation.repository.ReservationRepository;
import com.example.demo.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
@Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Reservation> getReservationByGuestName(String name) {
        return reservationRepository.getReservationByGuestName(name);
    }

    @Override
    public String delete(String id) {
        reservationRepository.deleteById(id);

        return id;
    }

    @Override
    public boolean updateReservationStatus(String reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation!= null) {
            reservation.checkAvailability();

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    @Override
    public boolean rejectReservation(String reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);

        if (reservation!=null && reservation.getStatus()== ReservationStatus.PENDING)  {
            reservation.reject();

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    @Override
    public boolean confirmReservation(String reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);

        if (reservation!=null && reservation.getStatus()== ReservationStatus.PENDING)  {
            reservation.confirm();

            reservationRepository.save(reservation);
            return true;
        }

        return false;
    }

    @Override
    public boolean placeReservation(String reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation!=null && reservation.getStatus()== ReservationStatus.CONFIRMED)  {
            reservation.placeReservation();

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }


    @Override
    public String create(Reservation reservation) {

        return reservationRepository.save(reservation).getReservationId();
    }



}
