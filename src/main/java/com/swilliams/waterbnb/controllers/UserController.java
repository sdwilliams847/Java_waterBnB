package com.swilliams.waterbnb.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.swilliams.waterbnb.models.User;
import com.swilliams.waterbnb.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	private UserService uS;
	
	public UserController(UserService uS) {
		this.uS = uS;
	}
	
	@RequestMapping("")
	public String dashboard(Model model, HttpSession session) {
		if(!uS.isValid(session)) return uS.redirect();
		model.addAttribute("users",this.uS.all());
		return "dashboard.jsp";
	}
	
	@RequestMapping("/new")
	public String newUser(@ModelAttribute("user") User user,HttpSession session) {
		uS.logout(session);
		return "newUser.jsp";
	}
	
	@PostMapping("/new")
	public String create(@Valid @ModelAttribute("user") User user, BindingResult res, RedirectAttributes flash,HttpSession session) {
		if(res.hasErrors()) {
			flash.addFlashAttribute("errors", res.getAllErrors());
			return "redirect:/users/new";
		}else {
			User exists = uS.findByEmail(user.getEmail());
			
			if(exists == null) {
				User u = uS.create(user);
				uS.login(session, u.getId());
				return "redirec:/users";
			}else {
				flash.addFlashAttribute("error","A user with this email already exists.");
				return "redirect:/users/new";
			}
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email")String email,@RequestParam("password")String password,HttpSession session,RedirectAttributes flash) {
		if(email.length()<1) {
			flash.addFlashAttribute("error","Email cannot be blank. ");
			return "redirect:/users/new";
		}
		
		User user = uS.findByEmail(email);
		
		if(user == null) {
			flash.addFlashAttribute("error","No user with this email was found.");
			return "redirect:/users/new";
		}else {
			if(uS.isMatch(password,user.getPassword())) {
				uS.login(session, user.getId());
				return "redirect:/users";
			}
			flash.addFlashAttribute("error", "Invalid Credentials");
			return "redirect:/users/new";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession s) {
		return uS.redirect();
	}
}
