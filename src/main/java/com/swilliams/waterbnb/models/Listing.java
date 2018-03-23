package com.swilliams.waterbnb.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Listing {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1, max=255, message="Address is required")
	private String address;
	
	@Size(min=1, max=255, message="Description is required")
	private String description;
	
	@NotNull
	private double cost;
	
	@Size(min=1, max=255, message="Size is required")
	private String size;
	
	@Column(updatable=false)
   	@DateTimeFormat(pattern = "MMMM-dd-yyyy")
	private Date createdAt;
    
   	@DateTimeFormat(pattern = "MMMM-dd-yyyy")
	private Date updatedAt;

   	@ManyToOne(fetch=FetchType.LAZY)
   	@JoinColumn(name="user_id")
   	private User user;
   	
   	@OneToMany(mappedBy="listing",fetch=FetchType.LAZY)
   	private List<Review> reviews;
   	
	public Listing() {
		super();
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
   	   	
}
