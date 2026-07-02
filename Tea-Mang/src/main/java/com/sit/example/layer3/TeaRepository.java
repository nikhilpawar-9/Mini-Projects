package com.sit.example.layer3;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sit.example.layer2.Tea;

@Repository
public interface TeaRepository 
	extends CrudRepository<Tea, Integer> {

}