package com.sit.example.layer2;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;
@Component
@Entity
@Table(name="juice_info")
public class Juice {

	@Id
	@Column(name="juice_id")
	private int juiceId;
	
	@Column(name="juice_type",length=20)
	private String juiceType;
	
	@Column(name="cost")
	private float juiceCost;
	
	public Juice() {
		System.out.println("Juice created.....");
	}

	public int getJuiceId() {
		return juiceId;
	}

	public void setJuiceId(int juiceId) {
		this.juiceId = juiceId;
	}

	public String getJuiceType() {
		return juiceType;
	}

	public void setJuiceType(String juiceType) {
		this.juiceType = juiceType;
	}

	public float getJuiceCost() {
		return juiceCost;
	}

	public void setJuiceCost(float juiceCost) {
		this.juiceCost = juiceCost;
	}

	
}