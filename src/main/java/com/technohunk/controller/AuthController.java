package com.technohunk.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technohunk.dto.SignupDTO;
import com.technohunk.security.JwtUtils;

@RestController
@RequestMapping("/v1")
public class AuthController {

	
	@Autowired
	  AuthenticationManager authenticationManager;


	  @Autowired
	  JwtUtils jwtUtils;
	
	
	 /**
	  * 	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  * @param signupRequest
	  * @return
	  */
	@PostMapping("/cauth")
	public Map<String,Object>  postLogin(@RequestBody SignupDTO signupRequest) {
		//authentication has two things - username and role
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signupRequest.getEmail(), signupRequest.getPassword()));
		String jwt = jwtUtils.generateJwtToken(authentication);
		Map<String,Object> jwtReponse = new HashMap<>();
		jwtReponse.put("Authorization", jwt);
		return jwtReponse;
	}
}
