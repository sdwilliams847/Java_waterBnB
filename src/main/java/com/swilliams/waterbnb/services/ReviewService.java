package com.swilliams.waterbnb.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.swilliams.waterbnb.models.Review;
import com.swilliams.waterbnb.repositories.ReviewRepository;

@Service
public class ReviewService {
	public ReviewRepository rR;
	
	public ReviewService(ReviewRepository rR) {
		this.rR = rR;
	}
	
	public void create(Review review) {
		rR.save(review);
	}
	
	public Optional<Review> find(Long id) {
		return rR.findById(id);
	}
	
	public ArrayList<Review> all(){
		return (ArrayList<Review>) rR.findAll();
	}
	
	public void destroy(Long id) {
		rR.deleteById(id);
	}
	
	public void update(Review review) {
		rR.save(review);
	}
}
