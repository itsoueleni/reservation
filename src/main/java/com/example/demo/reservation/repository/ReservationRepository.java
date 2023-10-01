package com.example.demo.reservation.repository;

import com.example.demo.reservation.collection.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation,String> {


    List<Reservation> getReservationByGuestName(String name);


    List<Reservation> getReservationById(String id);






}
