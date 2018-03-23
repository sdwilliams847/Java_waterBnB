package com.swilliams.waterbnb.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.swilliams.waterbnb.models.Listing;
import com.swilliams.waterbnb.repositories.ListingRepository;

@Service
public class ListingService {
	private ListingRepository lR;
	
	public ListingService(ListingRepository lR) {
		this.lR = lR;
	}
	
	public void create(Listing listing) {
		lR.save(listing);
	}
	
	public Optional<Listing> find(Long id) {
		return lR.findById(id);
	}
	
	public ArrayList<Listing> all(){
		return (ArrayList<Listing>) lR.findAll();
	}
	
	public void destroy(Long id) {
		lR.deleteById(id);
	}
	
	public void update(Listing listing) {
		lR.save(listing);
	}
}
