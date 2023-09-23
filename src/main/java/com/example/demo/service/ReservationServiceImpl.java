package com.example.demo.service;

import com.example.demo.collection.Reservation;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{
@Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Reservation> getReservationByGuestName(String name) {
        return reservationRepository.getReservationByGuestName(name);
    }

    @Override
    public void delete(String id) {
        reservationRepository.deleteById(id);

    }

    @Override
    public boolean updateReservationStatus(String reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null) {
            reservation.checkAvailability();

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
