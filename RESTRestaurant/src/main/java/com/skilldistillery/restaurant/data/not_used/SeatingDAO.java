package com.skilldistillery.restaurant.data.not_used;

import java.util.List;

import com.skilldistillery.restaurant.entities.Seating;

public interface SeatingDAO {

	Seating createSeating(Seating seating);
	Seating updateSeating(Seating seating);
	void deleteSeating(int seatingId);
	Seating getSeatingById(int seatingId);
	List<Seating> getAllSeating();
	
}
