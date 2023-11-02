package com.technohunk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technohunk.entity.Signup;

/**
 * This is my repository layer
 * @author javahunk
 *
 */
public interface SignupRepository extends JpaRepository<Signup, Integer> {
        
	public Optional<Signup>  findByEmailAndPassword(String email,String password);
}
