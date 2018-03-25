package com.swilliams.waterbnb.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swilliams.waterbnb.models.User;
import com.swilliams.waterbnb.services.UserService;

@Controller
@RequestMapping("/**")
public class RouteController {
	private UserService uS;
	
	public RouteController(UserService uS) {
		this.uS = uS;
	}
	
	@RequestMapping("")
	public String index(HttpServletRequest req,HttpSession s) {
		if(!uS.isValid(s)) {
			return "redirect:/users/new";
		}else {
			Optional<User> user = uS.find((Long) s.getAttribute("id"));
			User u = user.get();
			if(u.isHost()) {
				return "redirect:/listings/host";
			}else {
				return "redirect:/listings";
			}
		}
	}
}
