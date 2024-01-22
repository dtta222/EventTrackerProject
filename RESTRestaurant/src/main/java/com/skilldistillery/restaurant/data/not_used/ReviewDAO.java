package com.skilldistillery.restaurant.data.not_used;

import java.util.List;

import com.skilldistillery.restaurant.entities.Review;

public interface ReviewDAO {
	Review createReview(Review review);
	Review updateReview(Review review);
	void deleteReview(int reviewId);
	Review getReviewById(int reviewId);
	List<Review> getAllReviews();
	
}
