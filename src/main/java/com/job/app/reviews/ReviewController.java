package com.job.app.reviews;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> getAllReviews(@PathVariable("companyId") Long companyId) {
		List<Review> reviews = reviewService.getAllReviews(companyId);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@PostMapping("/reviews")
	public ResponseEntity<String> addReview(@PathVariable("companyId") Long companyId, @RequestBody Review review) {
		boolean isReviewSaved = reviewService.addReview(companyId, review);
		if (isReviewSaved) {
			return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Review Not Saved", HttpStatus.OK);
	}

	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<Review> getReview(@PathVariable("companyId") Long companyId,
			@PathVariable("reviewId") Long reviewId) {
		Review review = reviewService.getReview(companyId, reviewId);
		return new ResponseEntity<>(review, HttpStatus.OK);
	}

	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<String> updateReview(@PathVariable("reviewId") Long reviewId,
			@PathVariable("companyId") Long companyId, @RequestBody Review review) {

		boolean isReviewUpdated = reviewService.updateReview(companyId, reviewId, review);
		if (isReviewUpdated) {
			return new ResponseEntity<>("Review Updated Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Review Not Updated", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable("companyId") Long companyId,
			@PathVariable("reviewId") Long reviewId) {

		boolean isReviewDeleted = reviewService.deleteReview(companyId, reviewId);

		if (isReviewDeleted) {
			return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>("Review Not Deleted", HttpStatus.NOT_FOUND);
	}
}
