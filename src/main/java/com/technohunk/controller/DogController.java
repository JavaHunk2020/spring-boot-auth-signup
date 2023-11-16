package com.technohunk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technohunk.dto.DogDTO;
import com.technohunk.entity.Dog;
import com.technohunk.repository.DogRepository;

//GET - fetching the resources  - IDEMPOTENT
//POST -Creating a new resource - Non IDEMPOTENT
//PUT - updating an existing resource // IDEMPOTENT
//DELETE - deleting an existing resource /IDEMPOTENT
//PATCH - updating an existing resource partially - Non IDEMPOTENT
@RestController
@RequestMapping("/api")
public class DogController {
	
	@Autowired
	private DogRepository dogRepository;
	
	//localhost:444/api/dogs/jacky
	@DeleteMapping(value="/dogs/{name}")
	public Map<String,Object> deleteDogByName(@PathVariable String name) {
		dogRepository.deleteById(name);
		Map<String,Object> response=new HashMap<>();
		response.put("message", "Created deleted");
		return response;
	}
	
	//   {
    //"name": "Pappy",
    //"color": "pink",
   // "tail": 4
//}
	@PostMapping("/dogs")
	public Map<String,Object> createDog(@RequestBody DogDTO dog){
		Dog entity=new  Dog();
		BeanUtils.copyProperties(dog, entity);
		dogRepository.save(entity);
		Map<String,Object> response=new HashMap<>();
		response.put("status", "success");
		response.put("message", "Created successfully");
		return response;
	}
	
	//Passing value as a part of query parameter
	//http://localhost:444/api/dogs?name=Tommy
	//METHOD =GET
	@GetMapping(value="/dogs",params = {"name"})
	public Dog getDog(@RequestParam String name) {
		return dogRepository.findById(name).get();
	}
	
	//URI =Uniform Resource Identifier
	//URL =Uniform Resource Locator
	//https://mail.google.com/mail/u/5/#inbox
	//http://localhost:444/api/dogs/2
	//http://localhost:444/api/dogs/3
	//Passing value as a part of URI
	@GetMapping(value="/dogs/{name}")
	public Dog getDogByTail(@PathVariable String name) {
		return dogRepository.findById(name).get();
	}
	
	@GetMapping(value="/dogs")
	public List<Dog> getDogs() {
		List<Dog> dogs=dogRepository.findAll();
		return dogs;
	}

}
