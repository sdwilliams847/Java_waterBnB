package com.swilliams.waterbnb.services;

import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.swilliams.waterbnb.models.User;
import com.swilliams.waterbnb.repositories.UserRepository;

@Service
public class UserService {
	private UserRepository uR;
	private BCryptPasswordEncoder bcrypt;
	
	public UserService(UserRepository uR) {
		this.uR = uR;
		this.bcrypt=encoder();
	}
	
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	public boolean isMatch(String password, String dbPassword) {
		if(bcrypt.matches(password,dbPassword)) {
			return true;
		}else {
			return false;
		}
	}
	
	public void login(HttpSession s, Long id) {s.setAttribute("id",id);}
	
	public void logout(HttpSession s) {s.setAttribute("id", null);}
	
	public String redirect() {return "redirect:/users/new";}
	
	public boolean isValid(HttpSession session) {
		if(session.getAttribute("id") == null) {return false;}
		else {return true;}
	}
	
	public User create(User user) {
		user.setPassword(bcrypt.encode(user.getPassword()));
		return uR.save(user);
	}
	
	 public ArrayList<User> all(){
    	return (ArrayList<User>)uR.findAll();
    }
	 
	public Optional<User> find(Long id) {
		return uR.findById(id);
	}
	
	public User findByEmail(String email) {
		return uR.findByEmail(email);
	}
	
	public void update(User user) {
		uR.save(user);
	}
	
	public void destroy(Long id) {
		uR.deleteById(id);
	}
	

}
