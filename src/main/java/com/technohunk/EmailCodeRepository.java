package com.technohunk;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technohunk.entity.EmailCodeEntity;

public interface EmailCodeRepository extends JpaRepository<EmailCodeEntity, Integer> {
	
	public Optional<EmailCodeEntity> findByEmail(String email);
	public Optional<EmailCodeEntity> findByEmailAndCode(String email,String code);

}
