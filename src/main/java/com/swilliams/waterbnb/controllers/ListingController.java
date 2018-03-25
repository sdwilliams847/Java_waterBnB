package com.swilliams.waterbnb.controllers;

import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.swilliams.waterbnb.models.Listing;
import com.swilliams.waterbnb.models.Review;
import com.swilliams.waterbnb.models.User;
import com.swilliams.waterbnb.services.ListingService;
import com.swilliams.waterbnb.services.ReviewService;
import com.swilliams.waterbnb.services.UserService;

@Controller
@RequestMapping("/listings")
public class ListingController {
	private ListingService lS;
	private UserService uS;
	private ReviewService rS;
	
	public ListingController(ListingService lS,UserService uS,ReviewService rS) {
		this.lS = lS;
		this.uS = uS;
		this.rS = rS;
	}
	
	@RequestMapping("")
	public String listings(HttpSession session,Model model) {
		if(session.getAttribute("id") != null) {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			User u = user.get();
			model.addAttribute("user",u);
		}
		
		model.addAttribute("listings",lS.all());
		return "guest.jsp";
	}
	
	@RequestMapping("/host")
	public String host(HttpSession session,Model model,@ModelAttribute("listing") Listing listing) {
		if(session.getAttribute("id") == null) {
			return "redirect:/users/new";
		}else {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			User u = user.get();
			if(u.isHost()) {
				model.addAttribute("user",u);
				return "host.jsp";
			}else {
				return "redirect:/users/new";
			}		
		}
	}
	
	@PostMapping("")
	public String create(@Valid @ModelAttribute("listing") Listing listing, HttpSession session, BindingResult res) {
		if(session.getAttribute("id")==null) {
			return "redirect:/users/new";
		}
		
		Optional<User> user = uS.find((Long)session.getAttribute("id"));
		User u = user.get();
		
		if(!u.isHost()) {
			return "redirect:/listings";
		}else {
			if(res.hasErrors()) {
				return "redirect:/listings/host";
			}else {
				listing.setUser(u);
				lS.create(listing);
				return "redirect:/listings/host";
			}
		}
	}
	
	@RequestMapping("/{id}")
	public String find(@PathVariable("id") Long id, Model model,HttpSession session) {
		if(session.getAttribute("id") != null) {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			model.addAttribute("user",user.get());
		}
		
		Optional<Listing> l = lS.find(id);
		model.addAttribute("listing", l.get());
		
		return "reviews.jsp";
	}
	
	@RequestMapping("/{id}/review")
	public String review(@PathVariable("id")Long id, HttpSession session, Model model,@ModelAttribute("review")Review review) {
		if(session.getAttribute("id") != null) {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			model.addAttribute("user",user.get());
			
			Optional<Listing> listing = lS.find(id);
			model.addAttribute("listing",listing.get());
			
			return "review.jsp";
		}else {
			return "redirect:/listings/"+id;
		}
	}
	
	@PostMapping("/{id}/review")
	public String createReview(@PathVariable("id")Long id, @ModelAttribute("review")Review review,BindingResult res,HttpSession session,Model model) {
		if(session.getAttribute("id") == null) {
			return "redirect:/users/new";
		}else {
			if(res.hasErrors()) {return "review.jsp";}
			
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			User u = user.get();
			
			if(u.isHost()) {return "/listings/"+id;}

			Optional<Listing> listing = lS.find(id);
			Listing l = listing.get();
			
			review.setUser(u);
			review.setListing(l);
			rS.create(review);
			
			return "redirect:/listings/"+id;
		}
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam("search")String search,HttpSession session, Model model) {
		search = search.toLowerCase();
		
		if(session.getAttribute("id") != null) {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			User u = user.get();
			model.addAttribute("user",u);
		}
		
		ArrayList<Listing> allListings = lS.all();
		ArrayList<Listing> listings = new ArrayList<Listing>();
		
		for(int i =0;i<allListings.size();i++) {
			Listing listing = allListings.get(i);
			CharSequence seq = search;
			
			if(listing.getAddress().contains(seq)) {
				listings.add(listing);
			}
		}
		model.addAttribute("listings", listings);
		return "guest.jsp";
	}
	
}
