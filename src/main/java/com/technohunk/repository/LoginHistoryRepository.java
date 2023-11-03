package com.technohunk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technohunk.entity.LoginHistoryEntity;

public interface LoginHistoryRepository extends JpaRepository<LoginHistoryEntity, Integer> {

}
