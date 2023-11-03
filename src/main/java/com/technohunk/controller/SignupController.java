package com.technohunk.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.technohunk.entity.LoginHistoryEntity;
import com.technohunk.entity.Signup;
import com.technohunk.repository.LoginHistoryRepository;
import com.technohunk.repository.SignupRepository;

@Controller
public class SignupController {
	
	@Autowired
	private SignupRepository signupRepository;
	
	@Autowired
	private LoginHistoryRepository loginHistoryRepository;
	
	
	
	@GetMapping({"/showSignup","/"})
	public String showSignupPage() {
		return "signup";
	}
	
	@GetMapping("/auth")
	public String showLogin(){
		return "login";
	}
	
	@PostMapping("/auth")
	public String validateUser(@ModelAttribute Signup signup , Model model) {
		Optional<Signup> optional=signupRepository.findByEmailAndPassword(signup.getEmail(), signup.getPassword());
		if(optional.isPresent()) {
			Signup dbSignup=optional.get();
			LoginHistoryEntity loginHistoryEntity=new LoginHistoryEntity();
			loginHistoryEntity.setLogin_time(new Timestamp(new Date().getTime()));
			//This line is important
			loginHistoryEntity.setSignup(dbSignup);
			loginHistoryRepository.save(loginHistoryEntity);
			
			List<LoginHistoryEntity> loginHistoryList=loginHistoryRepository.findAll();
			model.addAttribute("loginHistory",loginHistoryList);
			
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
