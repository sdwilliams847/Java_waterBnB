package com.swilliams.waterbnb.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Email
    @Size(min=1, max=255, message="Email cannot be blank")
    private String email;


	@Size(min=1, max=255, message="First Name cannot be blank.")
	private String firstName;
    
    @Size(min=1, max=255, message="Last Name cannot be blank.")
	private String lastName;
    
    @Size(min=1, max=255, message="Password cannot be blank.")
    private String password;
	
    private boolean host;
    
    @Column(updatable=false)
   	@DateTimeFormat(pattern = "MMMM-dd-yyyy")
	private Date createdAt;
    
   	@DateTimeFormat(pattern = "MMMM-dd-yyyy")
	private Date updatedAt;

   	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
   	private List<Listing> listings;
   	
   	
   	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
   	private List<Review> reviews;
   	
	public User() {
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

	public List<Listing> getListings() {
		return listings;
	}

	public void setListings(List<Listing> listings) {
		this.listings = listings;
	}
	public boolean isHost() {
		return host;
	}

	public void setHost(boolean host) {
		this.host = host;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
