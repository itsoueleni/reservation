package com.example.demo.controller;

import com.example.demo.collection.Reservation;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Reservation reservation) {
        String createdReservation = reservationService.create(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reservation created successfully");
    }

    @GetMapping
    public List<Reservation> getReservationByGuestName(@RequestParam("name") String name) {
        return reservationService.getReservationByGuestName(name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {


        String deletionSuccessful = reservationService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Reservation deleted successfully");
    }


    @PutMapping("/{id}/updateReservationStatus")
    public ResponseEntity<String> updateReservationStatus(@PathVariable String id) {
        boolean statusUpdated = reservationService.updateReservationStatus(id);
        try {
            if (statusUpdated) {
                return ResponseEntity.ok("Reservation was updated");
            } else {
                return ResponseEntity.badRequest().body("Unable to update the reservation");
            }
        } catch (Exception e) {
            // Handle the exception and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }

    }


    @PutMapping("/{id}/rejectReservation")
    public ResponseEntity<String> rejectReservation(@PathVariable String id) {
        boolean reservationRejected = reservationService.rejectReservation(id);
        try {
            if (reservationRejected) {
                return ResponseEntity.ok("Reservation was rejected");
            } else {
                return ResponseEntity.badRequest().body("Unable to reject the reservation");
            }
        } catch (Exception e) {
            // Handle the exception and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PutMapping("/{id}/acceptReservation")
    public ResponseEntity<String> confirmReservation(@PathVariable String id) {
        boolean confirmReservation = reservationService.confirmReservation(id);
        try {
            if (confirmReservation) {
                return ResponseEntity.ok("Reservation was accepted");
            } else {
                return ResponseEntity.badRequest().body("Unable to accept the reservation");
            }
        } catch (Exception e) {
            // Handle the exception and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

}
