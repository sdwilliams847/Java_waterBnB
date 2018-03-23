package com.swilliams.waterbnb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swilliams.waterbnb.models.Listing;

@Repository
public interface ListingRepository extends CrudRepository<Listing,Long>{

}
