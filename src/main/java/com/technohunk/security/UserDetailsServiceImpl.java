package com.technohunk.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technohunk.entity.Signup;
import com.technohunk.repository.SignupRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


	/**
	 * Who will call this methid ???? spring security
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SignupRepository signupRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Signup> optional=signupRepository.findByEmail(username);
		if (optional.isPresent()) {
			Signup signup=optional.get();
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(signup.getRole()));
			return new User(username,signup.getPassword(),authorities);
		} else {
			throw new UsernameNotFoundException("User Not Found with username: " + username);
		}
		// return UserDetailsImpl.build(signup);
	}

}