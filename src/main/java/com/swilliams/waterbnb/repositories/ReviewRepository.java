package com.swilliams.waterbnb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swilliams.waterbnb.models.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review,Long> {

}
