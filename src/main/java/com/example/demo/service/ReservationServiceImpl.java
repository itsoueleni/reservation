package com.example.demo.service;

import com.example.demo.collection.Reservation;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService{
@Autowired
    private ReservationRepository reservationRepository;
    @Override
    public String create(Reservation reservation) {
        return reservationRepository.save(reservation).getReservationId();
    }
}
