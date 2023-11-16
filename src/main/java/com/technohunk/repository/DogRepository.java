package com.technohunk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technohunk.entity.Dog;

/**
 * This is my repository layer
 * @author javahunk
 *
 */
public interface DogRepository extends JpaRepository<Dog, String> {
        
}
