package com.sit.example.layer4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sit.example.layer2.Tea;
import com.sit.example.layer3.TeaRepository;

import jakarta.annotation.PostConstruct;

@Service
public class BreakfastService {

	@Autowired
	TeaRepository teaRepo;
	
	public BreakfastService() {
		System.out.println("BreakfastService()...");
	}
	
//	@PostConstruct
//	public void initData() {
//		Tea t1 = new Tea();
//		t1.setTeaCost(1);
//		t1.setTeaCost(20);
//		t1.setTeaType("Basundi Tea");
//		
//		Tea t2 = new Tea();
//		t2.setTeaCost(2);
//		t2.setTeaCost(30);
//		t2.setTeaType("Green Tea");
//	}
	
	public List<Tea> selectAllTeas() {
		List<Tea> teaList = new ArrayList<Tea>(); //empty
		
		Iterable<Tea> iterable = teaRepo.findAll(); //select * from tea_info;
		
		Iterator<Tea> iterator = iterable.iterator();
		while(iterator.hasNext()) {
			Tea theTea = iterator.next();
			teaList.add(theTea);
		}
		return teaList;
	}
	
	public Tea insertTea(Tea newTea) {
		Optional<Tea> teaFoundBox =
				teaRepo.findById(newTea.getTeaId());
		if(teaFoundBox.isPresent()) {
			throw new TeaAlreadyExistsException("Tea already present");
		}
		else {
			teaRepo.save(newTea);
		}
		return newTea;
	}
	
	public Tea updateTea(Tea existingTea) throws TeaNotFoundException {

	    Optional<Tea> teaFoundBox =
	            teaRepo.findById(existingTea.getTeaId());

	    if(!teaFoundBox.isPresent()) {
	        throw new TeaNotFoundException("Tea not found");
	    }

	    teaRepo.save(existingTea);

	    return existingTea;
	}
	
	public Tea deleteTea(int id) { // SERIVCE LAYER
		Optional<Tea> teaFoundBox =
				teaRepo.findById(id);
		
		if(teaFoundBox.isPresent()) {
			teaRepo.deleteById(id);
		}
		else {
			throw new TeaNotFoundException("Tea not found with this id "+id);
		}
		return teaFoundBox.get();
	}
	
	public Tea findATea(int teaId) { // SERIVCE LAYER
		Optional<Tea> teaFoundBox =
				teaRepo.findById(teaId);
		if(!teaFoundBox.isPresent()) {
			throw new TeaNotFoundException("Tea does not exists "+teaId);
		}
		return teaFoundBox.get();
	}
}





