package com.technohunk.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.technohunk.entity.Signup;
import com.technohunk.repository.SignupRepository;

@Controller
public class SignupController {
	
	@Autowired
	private SignupRepository signupRepository;
	
	@GetMapping({"/showSignup","/"})
	public String showSignupPage() {
		return "signup";
	}
	
	
	@PostMapping("/auth")
	public String validateUser(@ModelAttribute Signup signup , Model model) {
		Optional<Signup> optional=signupRepository.findByEmailAndPassword(signup.getEmail(), signup.getPassword());
		if(optional.isPresent()) {
			return "home";	
		}else {
			model.addAttribute("message", "Hmmm username and password are not correct!");
			return "login";
		}
		
	}
	
	@PostMapping("/addSignup")
	public String postSignup(@ModelAttribute Signup signup) {
		signup.setDoe(new Timestamp(new Date().getTime()));
		signupRepository.save(signup);
		return "login";
	}
	

}
