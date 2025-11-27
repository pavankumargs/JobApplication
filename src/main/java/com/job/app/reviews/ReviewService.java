package com.job.app.reviews;

import java.util.List;

public interface ReviewService {

	public List<Review> getAllReviews(Long companyId);

	public boolean addReview(Long companyId, Review review);

	public Review getReview(Long companyId, Long reviewId);
	
	public boolean updateReview(Long companyId, Long reviewId, Review review);
	
	public boolean deleteReview(Long companyId, Long reviewId);
}
