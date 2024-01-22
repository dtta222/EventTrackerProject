package com.skilldistillery.restaurant.data.not_used;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.restaurant.entities.Seating;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SeatingDAOImpl implements SeatingDAO {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Seating createSeating(Seating seating) {
		em.persist(seating);
        return seating;
	}

	@Override
	public Seating updateSeating(Seating seating) {
		return em.merge(seating);
	}

	@Override
	public void deleteSeating(int seatingId) {
		Seating seating = em.find(Seating.class, seatingId);
        if (seating != null) {
            em.remove(seating);
        }
	}

	@Override
	public Seating getSeatingById(int seatingId) {
		return em.find(Seating.class, seatingId);
	}

	@Override
	public List<Seating> getAllSeating() {
		return em.createQuery("SELECT s FROM Seating s", Seating.class).getResultList();
	}

}
