package com.example.demo.reservation.controller;


import com.example.demo.reservation.models.Reservation;
import com.example.demo.reservation.service.ReservationService;


import io.swagger.annotations.*;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Data
@RestController
@RequestMapping("/reservation")
@Api(tags = "Reservation API")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;


    //Create a reservation endpoint
    @ApiOperation(
            value = "Create a new reservation",
            notes = "This endpoint allows guests to create a new reservation."
    )

    @ApiResponses({
            @ApiResponse(
                    code = 201,
                    message = "Reservation created successfully"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Bad request"

            ),
            @ApiResponse(
                    code = 500,
                    message = "An internal server error occurred"

            )
    })
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation savedReservation = reservationService.save(reservation);
        //reservation/{id}, reservation.getId
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedReservation.getId())
                .toUri();
        // Return the  URI location
        return ResponseEntity.created(location).build();
    }

    //Create a reservation by name endpoint
@ApiOperation(
            value = "Get reservation details by guest name",
            notes = "This endpoint allows users to retrieve a list of reservations by specifying a guest's name as a query parameter.")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Successfully retrieved reservation details",
                    response = Reservation.class
            ),
            @ApiResponse(
                    code = 404,
                    message = "Reservation not found"
            )
    })
    @GetMapping
    public List<Reservation> getReservationByGuestName(
            @ApiParam(
                    name = "name",
                    value = "Guest's full name",
                    required = true,
                    allowableValues = "John Doe, Jane Smith, Alice Johnson",// Define allowable values
                    access = "public"

            ) @RequestParam("name") String name) {
        return reservationService.getReservationByGuestName(name);
    }

//Delete a reservation endpoint
    @ApiOperation(
            value = "Delete a reservation by ID",
            notes = "This endpoint allows users to delete a reservation by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 204,
                    message = "Reservation successfully deleted"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Reservation not found"

            )
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {


        String deletionSuccessful = reservationService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Reservation deleted successfully");
    }



    //Update a reservation status endpoint
    @ApiOperation(
            value = "Update reservation status by ID",
            notes = "This endpoint allows users to update the status of a reservation by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Reservation status was updated successfully"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Unable to update the reservation"

            ),
            @ApiResponse(
                    code = 500,
                    message = "An internal server error occurred"

            )
    })

    @PutMapping("/{id}/updateReservationStatus")
    public ResponseEntity<String> updateReservationStatus(
            @ApiParam(
                    name = "id",
                    value = "Reservation ID",
                    required = true
            ) @PathVariable String id) {
        boolean statusUpdated = reservationService.updateReservationStatus(id);
        try {
            if (statusUpdated) {
                return ResponseEntity.ok("Reservation status was updated");
            } else {
                return ResponseEntity.badRequest().body("Unable to update the reservation");
            }
        } catch (Exception e) {
            // Handle the exception and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }

    }



    //Reject a reservation  endpoint
    @ApiOperation(
            value = "Reject a reservation by ID",
            notes = "This endpoint allows hosts to reject a reservation by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Reservation was rejected successfully"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Unable to reject the reservation"

            ),
            @ApiResponse(
                    code = 500,
                    message = "An internal server error occurred"

            )
    })
    @PutMapping("/{id}/rejectReservation")
    public ResponseEntity<String> rejectReservation(
            @ApiParam(
                    name = "id",
                    value = "Reservation ID",
                    required = true)
            @PathVariable String id) {
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



    //Confirm a reservation endpoint
    @ApiOperation(
            value = "Confirm a reservation by ID",
            notes = "This endpoint allows hosts to confirm a reservation by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Reservation is confirmed successfully"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Unable to confirm the reservation"

            ),
            @ApiResponse(
                    code = 500,
                    message = "An internal server error occurred"

            )
    })


    @PutMapping("/{id}/acceptReservation")
    public ResponseEntity<String> confirmReservation(
            @ApiParam(
                    name = "id",
                    value = "Reservation ID",
                    required = true)
            @PathVariable String id) {
        boolean confirmReservation = reservationService.confirmReservation(id);
        try {
            if (confirmReservation) {
                return ResponseEntity.ok("Reservation is confirmed");
            } else {
                return ResponseEntity.badRequest().body("Unable to confirm the reservation");
            }
        } catch (Exception e) {
            // Handle the exception and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }



    //Place a reservation endpoint
    @ApiOperation(
            value = "Place a reservation by ID",
            notes = "This endpoint allows users to place a reservation by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 201,
                    message = "Reservation was placed successfully in the system"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Unable to place the reservation in the system"

            ),
            @ApiResponse(
                    code = 500,
                    message = "An internal server error occurred"

            )
    })




    @PostMapping("/{id}/placeReservation")
    public ResponseEntity<String> placeReservation(
            @ApiParam(
                    name = "id",
                    value = "Reservation ID",
                    required = true
            ) @PathVariable String id) {
        boolean placeReservation = reservationService.placeReservation(id);
        try {
            if (placeReservation) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Reservation was placed successfully in the system");
            } else {
                return ResponseEntity.badRequest().body("Unable to place the reservation in the system");
            }
        } catch (Exception e) {
            // Handle the exception and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }

    }



    //Get a reservation by id endpoint
    @ApiOperation(
            value = "Get reservation details by ID",
            notes = "This endpoint allows users to retrieve reservation details by specifying a reservation ID."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Successfully retrieved reservation details",
                    response = Reservation.class
            ),
            @ApiResponse(
                    code = 404,
                    message = "Reservation not found"
            )
    })
    @GetMapping("/{id}")
    public List<Reservation> getReservationById(
            @ApiParam(
                    name = "id",
                    value = "Reservation ID",
                    required = true)
            @PathVariable String id) {
        return reservationService.getReservationById(id);
    }
}
