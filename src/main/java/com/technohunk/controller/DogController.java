package com.technohunk.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technohunk.dto.DogDTO;


@RestController
@RequestMapping("/api")
public class DogController {
	
	//Passing value as a part of query parameter
	//http://localhost:444/api/dogs?name=Tommy
	//METHOD =GET
	@GetMapping(value="/dogs",params = {"name"})
	public DogDTO getDog(@RequestParam String name) {
		DogDTO dogDTO=new DogDTO();
		dogDTO.setColor("red");
		dogDTO.setName(name);
		dogDTO.setTail(1);
		return dogDTO;
	}
	
	//URI =Uniform Resource Identifier
	//URL =Uniform Resource Locator
	//https://mail.google.com/mail/u/5/#inbox
	//http://localhost:444/api/dogs/2
	//http://localhost:444/api/dogs/3
	//Passing value as a part of URI
	@GetMapping(value="/dogs/{tail}")
	public DogDTO getDogByTail(@PathVariable int tail) {
		DogDTO dogDTO=new DogDTO();
		dogDTO.setColor("red");
		dogDTO.setName("Tommy");
		dogDTO.setTail(tail);
		return dogDTO;
	}
	
	@GetMapping(value="/dogs")
	public List<DogDTO> getDogs() {
		DogDTO dogDTO=new DogDTO();
		dogDTO.setColor("red");
		dogDTO.setName("Tommy");
		dogDTO.setTail(1);
		
		DogDTO dogDTO1=new DogDTO();
		dogDTO1.setColor("yellow");
		dogDTO1.setName("Jacky");
		dogDTO1.setTail(2);
		
		DogDTO dogDTO2=new DogDTO();
		dogDTO2.setColor("blue");
		dogDTO2.setName("Packer");
		dogDTO2.setTail(3);
		
		return List.of(dogDTO,dogDTO1,dogDTO2);
	}

}
