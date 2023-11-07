package com.technohunk.controller;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
			
	@GetMapping("deleteHistory")
	public String deleteHistory(@RequestParam int dbid,Model model) {
		loginHistoryRepository.deleteById(dbid);
		return "redirect:/showHistory";
	}
	

	@GetMapping("history/sort")
	public String showHistory(String sortOrder,Model model,HttpSession session) {
		String username=(String)session.getAttribute("username");
		List<LoginHistoryEntity> loginHistoryList=loginHistoryRepository.findByEmail(username);
		if("asc".equalsIgnoreCase(sortOrder)) {
			Collections.sort(loginHistoryList,(c1,c2)->(int)(c1.getDuration()-c2.getDuration()));
		}else {
			Collections.sort(loginHistoryList,(c1,c2)->(int)(c2.getDuration()-c1.getDuration()));
		}
		model.addAttribute("loginHistory",loginHistoryList);
		return "home";
	}		
			
	
	@GetMapping("showHistory")
	public String showHistory(Model model,HttpSession session) {
		String username=(String)session.getAttribute("username");
		List<LoginHistoryEntity> loginHistoryList=loginHistoryRepository.findByEmail(username);
		model.addAttribute("loginHistory",loginHistoryList);
		return "home";
	}
	
	
	@GetMapping({"/showSignup","/"})
	public String showSignupPage() {
		return "signup";
	}
	
	@GetMapping("/logout")
	public String logout(Model model,HttpSession session){
		Integer dbid=(Integer)session.getAttribute("loginHistoryDbId");
		LoginHistoryEntity historyEntity=loginHistoryRepository.findById(dbid).get();
		historyEntity.setLogout_time(new Timestamp(new Date().getTime()));
		long duration=historyEntity.getLogout_time().getTime()-historyEntity.getLogin_time().getTime();
		historyEntity.setDuration(duration);
		loginHistoryRepository.save(historyEntity);
		
		session.invalidate();
		model.addAttribute("message","You have been logout successfully.");
		return "login";
	}
	
	@GetMapping("/auth")
	public String showLogin(){
		return "login";
	}
	
	@PostMapping("/auth")
	public String validateUser(@ModelAttribute Signup signup ,HttpSession session, Model model) {
		Optional<Signup> optional=signupRepository.findByEmailAndPassword(signup.getEmail(), signup.getPassword());
		if(optional.isPresent()) {
			Signup dbSignup=optional.get();
			LoginHistoryEntity loginHistoryEntity=new LoginHistoryEntity();
			loginHistoryEntity.setLogin_time(new Timestamp(new Date().getTime()));
			//This line is important
			loginHistoryEntity.setSignup(dbSignup);
			LoginHistoryEntity dbEntity=loginHistoryRepository.save(loginHistoryEntity);
			
			List<LoginHistoryEntity> loginHistoryList=loginHistoryRepository.findByEmail(signup.getEmail());
			model.addAttribute("loginHistory",loginHistoryList);
			//session.setMaxInactiveInterval(30);
			//in session we are adding username/email
			session.setAttribute("username", signup.getEmail());
			session.setAttribute("loginHistoryDbId", dbEntity.getLhid());
			
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
