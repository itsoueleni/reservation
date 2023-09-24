package com.example.demo.repository;

import com.example.demo.collection.Reservation;
import com.example.demo.collection.ReservationStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation,String> {


    List<Reservation> getReservationByGuestName(String name);
}
