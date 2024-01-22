package com.skilldistillery.restaurant.data.not_used;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.restaurant.entities.Reservation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReservationDAOImpl implements ReservationDAO {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Reservation createReservation(Reservation reservation) {
		em.persist(reservation);
        return reservation;
	}

	/*@Override
	public Reservation updateReservationStatus(Reservation reservation) {
		return em.merge(reservation);
	}*/
	@Override
    public Reservation updateReservationStatus(int reservationId, String status) {
        Reservation reservation = em.find(Reservation.class, reservationId);
        if (reservation != null) {
            reservation.setStatus(status);
            em.merge(reservation);
            return reservation;
        } else {
            return null;
        }
    }

	@Override
	public boolean deleteReservation(int reservationId) {
        boolean deleted = false;
        Reservation toDelete = em.find(Reservation.class, reservationId);
		if (toDelete != null) {
			em.remove(toDelete);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public Reservation getReservationById(int reservationId) {
		return em.find(Reservation.class, reservationId);
	}

	@Override
	public List<Reservation> getAllReservations() {
		return em.createQuery("SELECT r FROM Reservation r", Reservation.class).getResultList();
	}


}
