package com.skilldistillery.restaurant.controllers.not_used;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skilldistillery.restaurant.data.not_used.ReservationDAO;
import com.skilldistillery.restaurant.entities.Reservation;

import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    @Autowired
    private ReservationDAO reservationDAO;

    // Endpoint to create a new reservation
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation createdReservation = reservationDAO.createReservation(reservation);
        if (createdReservation != null) {
            return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get details of a specific reservation
    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable int reservationId) {
        Reservation reservation = reservationDAO.getReservationById(reservationId);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to get all reservations
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationDAO.getAllReservations();
        if (reservations != null && !reservations.isEmpty()) {
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to update the status of a reservation
    @PatchMapping("/{reservationId}/status")
    public ResponseEntity<Reservation> updateReservationStatus(@PathVariable int reservationId, @RequestParam String status) {
        Reservation updatedReservation = reservationDAO.updateReservationStatus(reservationId, status);
        if (updatedReservation != null) {
            return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to cancel a reservation by ID
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> cancelReservationById(@PathVariable int reservationId) {
        boolean canceled = reservationDAO.deleteReservation(reservationId);
        if (canceled) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

