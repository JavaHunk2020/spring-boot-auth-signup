package com.technohunk.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technohunk.EmailCodeRepository;
import com.technohunk.dto.SignupDTO;
import com.technohunk.entity.EmailCodeEntity;
import com.technohunk.security.JwtUtils;
import com.technohunk.service.EmailService;
import com.technohunk.service.SignupService;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class AuthController {
	@Autowired
	private SignupService signupService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private EmailService emailService;
	
	
	@Autowired
    private EmailCodeRepository emailCodeRepository;

	/**
	 * @Override
	 * @Transactional public UserDetails loadUserByUsername(String username) throws
	 *                UsernameNotFoundException {
	 * @param signupRequest
	 * @return { "email":"jack@gmail.com", "password":"jill"
	 * 
	 *         }
	 * @RequestBody ->> it is reading data from request payload & converting data
	 *              into JavaObject using jackson mapper
	 */

	@DeleteMapping("/signups/{email}")
	public Map<String, Object> deleteSignup(@PathVariable String email) {
		signupService.deleteSignupByEmail(email);
		Map<String, Object> jwtReponse = new HashMap<>();
		jwtReponse.put("email", email);
		jwtReponse.put("message", "Record is deleted successfully!");
		return jwtReponse;
	}
	
	
	@GetMapping("/verify/email/code")
	public Map<String, Object> verifyEmailCode(@RequestParam String email,@RequestParam String code) {
		Map<String, Object> jwtReponse = new HashMap<>();
		if(emailCodeRepository.findByEmailAndCode(email,code).isPresent()) {
			jwtReponse.put("status", "success");	
		}else {
			jwtReponse.put("status", "false");
		}
		return jwtReponse;
	}
	
	@GetMapping("/verifyemail/{email}")
	public Map<String, Object> verifyemail(@PathVariable String email) {
		Map<String, Object> jwtReponse = new HashMap<>();
		boolean isExist = signupService.existsByEmail(email);
		if (isExist) {
			Random rand = new Random();
			String code = String.format("%04d", rand.nextInt(10000));
			System.out.println("Code - >>>>>>>>>>"+code);
			Optional<EmailCodeEntity> optional= emailCodeRepository.findByEmail(email);
			EmailCodeEntity codeEntity=null;
			if(optional.isPresent()) {
				codeEntity=optional.get();
				codeEntity.setCode(code);
			}else {
				codeEntity=new EmailCodeEntity();
				codeEntity.setCode(code);
				codeEntity.setEmail(email);
				codeEntity.setDoe(new Timestamp(new Date().getTime()));
			}
			emailCodeRepository.save(codeEntity);
			emailService.sendSimpleMessage(email, "Regarding your password reset code", "Your password reset code is = "+code);
			jwtReponse.put("status", "success");
		} else {
			jwtReponse.put("status", "fail");
		}
		return jwtReponse;
	}
	
	
	@GetMapping("/signups")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<SignupDTO> getSignups() {
		return signupService.findSignups();
	}

	@PostMapping("/signups")
	public ResponseEntity<Map<String, Object>> postSignup(@RequestBody SignupDTO signup) {
		boolean status=signupService.isAlreadySignup(signup.getEmail(), signup.getName());
		Map<String, Object> jwtReponse = new HashMap<>();
		if(!status) {
			signup.setDoe(new Timestamp(new Date().getTime()));
			signup.setRole("CUSTOMER");
			signupService.saveSigup(signup);
			jwtReponse.put("message", "Record is created successfully!");
			jwtReponse.put("email", signup.getEmail());
			return new ResponseEntity<Map<String,Object>>(jwtReponse,HttpStatus.OK);
		} else {
			jwtReponse.put("message", "User is already signed up!");
			return new ResponseEntity<Map<String,Object>>(jwtReponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/cauth")
	public Map<String, Object> postLogin(@RequestBody SignupDTO signupRequest) {
		// authentication has two things - username and role
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signupRequest.getEmail(), signupRequest.getPassword()));
		String jwt = jwtUtils.generateJwtToken(authentication);
		Map<String, Object> jwtReponse = new HashMap<>();
		jwtReponse.put("Authorization", jwt);
		jwtReponse.put("email", signupRequest.getEmail());
		return jwtReponse;
	}
}
