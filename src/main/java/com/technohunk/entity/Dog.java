package com.technohunk.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dogs_tbl")
public class Dog {
	
	@Id
	private String name;
	private String color;
	private int tail;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getTail() {
		return tail;
	}
	public void setTail(int tail) {
		this.tail = tail;
	}
	
}
