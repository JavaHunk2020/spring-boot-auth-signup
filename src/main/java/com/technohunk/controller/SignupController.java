package com.technohunk.controller;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.technohunk.dto.LoginHistoryDTO;
import com.technohunk.dto.SignupDTO;
import com.technohunk.entity.Signup;
import com.technohunk.service.SignupService;

@Controller
@RequestMapping("/ui")
public class SignupController {
	
	
	@Autowired
	private SignupService signupService;
	
			
	@GetMapping("deleteHistory")
	public String deleteHistory(@RequestParam int dbid,Model model) {
		signupService.deleteLoginHistoryById(dbid);
		return "redirect:/showHistory";
	}
	

	@GetMapping("history/sort")
	public String showHistory(String sortOrder,Model model,HttpSession session) {
		String username=(String)session.getAttribute("username");
		List<LoginHistoryDTO> loginHistoryList=signupService.findByEmail(username);
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
		List<LoginHistoryDTO> loginHistoryList=signupService.findByEmail(username);
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
		LoginHistoryDTO loginHistoryDTO=signupService.findLoginHistoryByDbId(dbid);
		loginHistoryDTO.setLogout_time(new Timestamp(new Date().getTime()));
		long duration=loginHistoryDTO.getLogout_time().getTime()-loginHistoryDTO.getLogin_time().getTime();
		loginHistoryDTO.setDuration(duration);
		signupService.saveHistoryEntity(loginHistoryDTO);
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
		boolean optional=signupService.findByEmailAndPassword(signup.getEmail(), signup.getPassword());
		if(optional) {
			LoginHistoryDTO loginHistoryDTO=new LoginHistoryDTO();
			loginHistoryDTO.setLogin_time(new Timestamp(new Date().getTime()));
			//This line is important
			LoginHistoryDTO historyDTO=signupService.createHistoryEntity(loginHistoryDTO,signup.getEmail());
			
			List<LoginHistoryDTO> loginHistoryList=signupService.findByEmail(signup.getEmail());
			model.addAttribute("loginHistory",loginHistoryList);
			//session.setMaxInactiveInterval(30);
			//in session we are adding username/email
			session.setAttribute("username", signup.getEmail());
			session.setAttribute("loginHistoryDbId", historyDTO.getLhid());
			
			return "home";	
		}else {
			model.addAttribute("message", "Hmmm username and password are not correct!");
			return "login";
		}
		
	}
	
	@PostMapping("/addSignup")
	public String postSignup(@ModelAttribute SignupDTO signup) {
		signup.setDoe(new Timestamp(new Date().getTime()));
		signup.setRole("CUSTOMER");
		signupService.saveSigup(signup);
		return "login";
	}
	

}
