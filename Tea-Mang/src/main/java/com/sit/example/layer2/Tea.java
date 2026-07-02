package com.sit.example.layer2;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;

@Component
@Entity
@Table(name="tea_info")
public class Tea {

	@Id
	@Column(name="tea_id")
	private int teaId;
	
	@Column(name="tea_type",length=20)
	private String teaType;
	
	@Column(name="cost")
	private float teaCost;
	
	public Tea() {
		System.out.println("Tea created.....");
	}

	public int getTeaId() {
		return teaId;
	}

	public void setTeaId(int teaId) {
		this.teaId = teaId;
	}

	public String getTeaType() {
		return teaType;
	}

	public void setTeaType(String teaType) {
		this.teaType = teaType;
	}

	public float getTeaCost() {
		return teaCost;
	}

	public void setTeaCost(float teaCost) {
		this.teaCost = teaCost;
	}

	

	
}