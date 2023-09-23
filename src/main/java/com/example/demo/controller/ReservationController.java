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
    public String save(@RequestBody Reservation reservation) {

        return reservationService.create(reservation);
    }

    @GetMapping
    public List<Reservation> getReservationByGuestName(@RequestParam("name") String name) {
        return reservationService.getReservationByGuestName(name);
    }

    @DeleteMapping ("/{id}")
    public void delete(@PathVariable String id){
        reservationService.delete(id);

    }


    @PutMapping("/{id}/updateStatus")
    public ResponseEntity<String> updateReservationStatus(@PathVariable String id) {
        boolean statusUpdated = reservationService.updateReservationStatus(id);

            return ResponseEntity.ok(statusUpdated + " Reservation status updated successfully.");
    }


    @PutMapping("/{id}/rejectReservation")
    public ResponseEntity<String> rejectReservation(@PathVariable String id) {
        boolean reservationRejected =reservationService.rejectReservation(id);
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

}
