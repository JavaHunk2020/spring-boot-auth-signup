package com.technohunk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.technohunk.entity.LoginHistoryEntity;

public interface LoginHistoryRepository extends JpaRepository<LoginHistoryEntity, Integer> {

	//HQL - >> Since we are writing it  on the basis of 
	//class name and attribute of instead of using table name and column of it
	@Query("from LoginHistoryEntity le where le.signup.email = ?1")
	public List<LoginHistoryEntity> findByEmail(String email);
	
}
