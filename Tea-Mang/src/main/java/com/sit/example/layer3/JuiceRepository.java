package com.sit.example.layer3;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sit.example.layer2.Juice;
@Repository
public interface JuiceRepository 
	extends CrudRepository<Juice, Integer> {

}