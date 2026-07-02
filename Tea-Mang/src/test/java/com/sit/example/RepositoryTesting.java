package com.sit.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sit.example.layer2.Tea;
import com.sit.example.layer3.TeaRepository;

@SpringBootTest
public class RepositoryTesting {
	@Autowired
	TeaRepository teaRepo;
	

	public void addTeaTest() {
		Tea tea = new Tea();
		tea.setTeaId(7);
		tea.setTeaCost(20);
		tea.setTeaType("Cutting Tea");
		teaRepo.save(tea);
		
		System.out.println("Tea Added...");
	}
	
	
	public void updateTeaTest() {

	    Tea tea = teaRepo.findById(1).orElse(null);

	    if(tea != null) {

	        tea.setTeaType("Masala Tea");
	        tea.setTeaCost(35);

	        teaRepo.save(tea);

	        System.out.println("Tea Updated Successfully");
	    }
	    else {
	        System.out.println("Tea Not Found");
	    }
	}
	
	
	public void deleteTeaTest() {

	    int teaId = 7;

	    if(teaRepo.existsById(teaId)) {

	        teaRepo.deleteById(teaId);

	        System.out.println("Tea Deleted Successfully");
	    }
	    else {

	        System.out.println("Tea Not Found");
	    }
	}
	
	

	public void getTeaByIdTest() {

	    int teaId = 1;

	    Tea tea = teaRepo.findById(teaId).orElse(null);

	    if(tea != null) {

	        System.out.println("Tea Id   : " + tea.getTeaId());
	        System.out.println("Tea Type : " + tea.getTeaType());
	        System.out.println("Tea Cost : " + tea.getTeaCost());
	    }
	    else {

	        System.out.println("Tea Not Found");
	    }
	}
	
	@Test
	public void getAllTeaTest() {

	    Iterable<Tea> allTeas = teaRepo.findAll();

	    allTeas.forEach(tea -> {

	        System.out.println(
	                tea.getTeaId() + " | " +
	                tea.getTeaType() + " | " +
	                tea.getTeaCost()
	        );
	    });
	}
	
}
