package com.skilldistillery.restaurant.data.not_used;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.restaurant.entities.Review;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReviewDAOImpl implements ReviewDAO {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Review createReview(Review review) {
		em.persist(review);
        return review;
	}

	@Override
	public Review updateReview(Review review) {
		return em.merge(review);
	}

	@Override
	public void deleteReview(int reviewId) {
		Review review = em.find(Review.class, reviewId);
        if (review != null) {
            em.remove(review);
        }
	}

	@Override
	public Review getReviewById(int reviewId) {
		return em.find(Review.class, reviewId);
	}

	@Override
	public List<Review> getAllReviews() {
		return em.createQuery("SELECT r FROM Review r", Review.class).getResultList();
	}
}
