package com.example.demo.controller;

import com.example.demo.collection.Reservation;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if (statusUpdated) {
            return ResponseEntity.ok("Reservation status updated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Unable to update reservation status.");
        }
    }
   }
