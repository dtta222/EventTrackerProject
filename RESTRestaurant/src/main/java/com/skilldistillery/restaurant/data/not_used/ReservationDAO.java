package com.skilldistillery.restaurant.data.not_used;

import java.util.List;

import com.skilldistillery.restaurant.entities.Reservation;

public interface ReservationDAO {

	Reservation createReservation(Reservation reservation);
	//Reservation updateReservationStatus(Reservation reservation);
	Reservation updateReservationStatus(int id, String status);
	boolean deleteReservation(int reservationId);
	Reservation getReservationById(int reservationId);
	List<Reservation> getAllReservations();	
}
